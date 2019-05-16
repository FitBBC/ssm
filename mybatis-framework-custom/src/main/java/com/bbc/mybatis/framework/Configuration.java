package com.bbc.mybatis.framework;

import javax.sql.DataSource;
import java.util.HashMap;

/**
 * Description:
 * User: fitbbc
 * Date: 2019-05-08
 * Time: 18:21
 */
public class Configuration {
    private DataSource dataSource;

    private HashMap<String, MappedStatement> mappedStatementMap = new HashMap<>();

    public MappedStatement getMappedStatement(String mappedStatementId) {
        return mappedStatementMap.get(mappedStatementId);
    }

    public void addMappedStatement(String mappedStatementId, MappedStatement mappedStatement) {
        this.mappedStatementMap.put(mappedStatementId, mappedStatement);
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
