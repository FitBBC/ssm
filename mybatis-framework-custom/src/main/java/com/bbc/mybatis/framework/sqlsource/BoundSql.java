package com.bbc.mybatis.framework.sqlsource;

import com.bbc.mybatis.framework.ParameterMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther: fitbbc
 * @date: 2019/05/13
 */
public class BoundSql {

    private String sql;

    private List<ParameterMapping> parameterMappingList = new ArrayList();

    public BoundSql(String sql, List<ParameterMapping> parameterMappingList) {
        this.sql = sql;
        this.parameterMappingList = parameterMappingList;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public List<ParameterMapping> getParameterMappingList() {
        return parameterMappingList;
    }

    public void addParameterMappingList(ParameterMapping parameterMapping) {
        this.parameterMappingList.add(parameterMapping);
    }
}
