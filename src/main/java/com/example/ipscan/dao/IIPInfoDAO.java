package com.example.ipscan.dao;

import com.example.ipscan.model.IPInfoValue;

import java.util.List;

public interface IIPInfoDAO {
    List<IPInfoValue> findAll();

    List<IPInfoValue> findFilterIsUse(boolean isUse);

    int merge(IPInfoValue ipInfoValue);
}
