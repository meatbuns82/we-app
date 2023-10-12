package com.luwh.we.app.core.handler;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lu.wh
 * @date 2023/10/12 10/38/23
 * @description
 */
@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes({List.class})
@Component
public class MyTypeHandler extends BaseTypeHandler<List> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List parameter, JdbcType jdbcType) throws SQLException {

    }

    @Override
    public List getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String string = rs.getString(columnName);
        if(StringUtils.hasText(string)){
            return JSONObject.parseObject(string, new TypeReference<List>(){});
        }else {
            return new ArrayList();
        }
    }

    @Override
    public List getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public List getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return null;
    }
}
