package com.example.ipscan.service;

import com.example.ipscan.dao.IIPInfoDAO;
import com.example.ipscan.model.IPInfoValue;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IPInfoService implements IIPInfoService {
    private final IIPInfoDAO IPInfoDAO;

    public IPInfoService(IIPInfoDAO ipInfoDAO) {
        this.IPInfoDAO = ipInfoDAO;
    }

    @Override
    public List<IPInfoValue> findAll() {
        return IPInfoDAO.findAll();
    }
}
