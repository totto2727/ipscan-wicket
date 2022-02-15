package com.example.ipscan.service;

import com.example.ipscan.model.IPInfoValue;

import java.util.List;

public interface IIPInfoService {
    List<IPInfoValue> findAll();
}
