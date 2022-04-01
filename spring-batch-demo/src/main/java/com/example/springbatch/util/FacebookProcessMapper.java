package com.example.springbatch.util;

import com.example.springbatch.model.Facebookprocess;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class FacebookProcessMapper implements RowMapper<Facebookprocess> {

    public Facebookprocess mapRow(ResultSet rs, int rowNumm) throws SQLException{
        Facebookprocess fbproccess = new Facebookprocess();
        fbproccess.setId(rs.getInt("id"));
        fbproccess.setAd_name_lpid(rs.getString("namelpid"));
        fbproccess.setSumcost(rs.getInt("sumcost"));
        fbproccess.setSumclick(rs.getInt("sumclick"));
        fbproccess.setSumimpression(rs.getInt("sumimpression"));
        fbproccess.setDate_fb(rs.getString("date_fb"));
        return fbproccess;
    }

}
