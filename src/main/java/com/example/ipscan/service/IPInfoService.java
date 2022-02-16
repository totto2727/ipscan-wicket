package com.example.ipscan.service;

import com.example.ipscan.dao.IIPInfoDAO;
import com.example.ipscan.model.IPInfoValue;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IPInfoService implements IIPInfoService {
    private final IIPInfoDAO iPInfoDAO;

    public IPInfoService(IIPInfoDAO ipInfoDAO) {
        this.iPInfoDAO = ipInfoDAO;
    }

    @Override
    public List<IPInfoValue> findAll() {
        return iPInfoDAO.findAll();
    }

    @Override
    public List<IPInfoValue> findFilterIsUse(boolean isUse) {
        return iPInfoDAO.findFilterIsUse(isUse);
    }
}
