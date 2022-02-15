package com.example.ipscan.util;

import com.example.ipscan.model.IPInfoValue;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

public record IPScanner(String firstIpInTheNetwork) {
    public static void main(String[] args) {
        var networkIps = IPScanner.scanAll("192.168.11.0",256);
        System.out.println("Devices connected to the network:");
        if (networkIps != null) {
            networkIps.forEach(System.out::println);
        }
    }

    public List<IPInfoValue> scanAll(int numOfIps) {
        ForkJoinPool pool = new ForkJoinPool(100);
        var host = firstIpInTheNetwork.substring(0, firstIpInTheNetwork.length() - 1);
        try {
            return pool.submit(() -> IntStream
                            .rangeClosed(0, numOfIps<256?numOfIps:255)
                            .parallel()
                            .mapToObj(
                                    v -> host + v
                            )
                            .map(this::scan)
                            .filter(Objects::nonNull)
                            //                .sorted()
                            .toList())
                    .get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public IPInfoValue scan(String ipAddress) {
        try {
            var inetAddress = InetAddress.getByName(ipAddress);
            try {
                return new IPInfoValue(inetAddress.getHostAddress(), inetAddress.getHostName(), inetAddress.isReachable(500));
            } catch (IOException e) {
                e.printStackTrace();
                return new IPInfoValue(inetAddress.getHostAddress(), inetAddress.toString(), false);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return null;
        }
    }

    static public List<IPInfoValue> scanAll(String firstIpInTheNetwork, int numOpIps) {
        return new IPScanner(firstIpInTheNetwork).scanAll(numOpIps);
    }
}
