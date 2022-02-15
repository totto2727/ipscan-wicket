package com.example.ipscan.model;

import java.io.Serializable;

public record IPInfoValue(String ipAddress, String hostName, boolean isUse) implements Serializable {
}
