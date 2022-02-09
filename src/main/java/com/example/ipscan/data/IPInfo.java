package com.example.ipscan.data;

import com.example.ipscan.model.IPInfoModel;

import java.net.InetAddress;

public record IPInfo(InetAddress ipAddress, String hostName, boolean isUse) {
    public IPInfoModel toModel() {
        return new IPInfoModel(ipAddress().getAddress().toString(), hostName, isUse);
    }
}
