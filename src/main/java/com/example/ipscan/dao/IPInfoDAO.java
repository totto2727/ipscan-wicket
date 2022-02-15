package com.example.ipscan.dao;

import com.example.ipscan.model.IPInfoValue;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IPInfoDAO implements IIPInfoDAO {
    private final JdbcTemplate jdbcTemplate;

    public IPInfoDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<IPInfoValue> findAll() {
        var sql = "select IP_ADDRESS,HOST_NAME,IS_USE from IP_INFO";
        return jdbcTemplate.query(sql, DataClassRowMapper.newInstance(IPInfoValue.class));
    }

    @Override
    public int merge(IPInfoValue ipInfoValue) {
        var sql = "merge into IP_INFO values ( ?,?,? )";
        return jdbcTemplate.update(sql, ipInfoValue.ipAddress(), ipInfoValue.hostName(), ipInfoValue.isUse());
    }
}
