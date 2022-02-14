package com.example.ipscan.service;

import com.example.ipscan.dao.IPInfoDAO;
import com.example.ipscan.model.IPInfoValue;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IPInfoService implements IIPInfoService {
    private final IPInfoDAO IPInfoDAO;

    public IPInfoService(IPInfoDAO IPInfoDAO) {
        this.IPInfoDAO = IPInfoDAO;
    }

    @Override
    public List<IPInfoValue> findAll() {
        return IPInfoDAO.findAll();
    }
}
