package com.example.springbatch.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.springbatch.model.Facebook;

import org.springframework.jdbc.core.RowMapper;

public class FacebookRowMapper implements RowMapper<Facebook> {

    @Override
    public Facebook mapRow(ResultSet rs, int rowNum) throws SQLException {
        Facebook facebook = new Facebook();
        facebook.setDate(rs.getString("date"));
        facebook.setMedia(rs.getString("media"));
        facebook.setNamelpid(rs.getString("namelpid"));
        facebook.setCost(rs.getDouble("cost"));
        facebook.setImpression(rs.getInt("impression"));
        facebook.setClick(rs.getInt("click"));
        facebook.setCv(rs.getInt("cv"));
        return facebook;
    }
}
