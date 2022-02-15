package com.example.ipscan.service;

import com.example.ipscan.dao.IIPInfoDAO;
import com.example.ipscan.util.IPScanner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class IPScannerService {
    private final IIPInfoDAO ipInfoDAO;

    public IPScannerService(IIPInfoDAO IPInfoDAO) {
        this.ipInfoDAO = IPInfoDAO;
    }

    @Scheduled(initialDelay = 5000, fixedDelay = 60000)
    void updateIpInfo() {
        var ipInfoValueList = IPScanner.scanAll("192.168.11.0", 255);
        System.out.println(ipInfoValueList.stream()
                .peek(System.out::println)
                .mapToInt(ipInfoDAO::merge)
                .sum());
    }
}
