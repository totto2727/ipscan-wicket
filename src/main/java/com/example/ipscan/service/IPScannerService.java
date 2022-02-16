package com.example.ipscan.service;

import com.example.ipscan.dao.IIPInfoDAO;
import com.example.ipscan.util.IPScanner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IPScannerService {
    private final IIPInfoDAO ipInfoDAO;

    @Value("${ipscan.bases}")
    private final List<String> bases;

    public IPScannerService(IIPInfoDAO IPInfoDAO, List<String> bases) {
        this.ipInfoDAO = IPInfoDAO;
        this.bases = bases;
    }

    @Scheduled(initialDelay = 5000, fixedDelay = 60000)
    void updateIpInfo() {
        System.out.println(bases.parallelStream()
                .flatMap(v -> IPScanner.scanAll(v, 255).stream().parallel())
                .peek(System.out::println)
                .mapToInt(ipInfoDAO::merge)
                .sum());
    }
}
