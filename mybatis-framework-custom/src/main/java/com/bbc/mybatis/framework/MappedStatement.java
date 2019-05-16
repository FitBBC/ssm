package com.bbc.mybatis.framework;

import com.bbc.mybatis.framework.sqlsource.DefaultSqlSource;

/**
 * @auther: fitbbc
 * @date: 2019/05/09
 */
public class MappedStatement {

    private String id;

    private Class<?> parameterTypeClass;

    private Class<?> resultTypeClass;

    private String statementType;

    private DefaultSqlSource sqlSource;

    public MappedStatement(String id, Class<?> parameterTypeClass, Class<?> resultTypeClass, String statementType, DefaultSqlSource sqlSource) {
        this.id = id;
        this.parameterTypeClass = parameterTypeClass;
        this.resultTypeClass = resultTypeClass;
        this.statementType = statementType;
        this.sqlSource = sqlSource;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Class<?> getParameterTypeClass() {
        return parameterTypeClass;
    }

    public void setParameterTypeClass(Class<?> parameterTypeClass) {
        this.parameterTypeClass = parameterTypeClass;
    }

    public Class<?> getResultTypeClass() {
        return resultTypeClass;
    }

    public void setResultTypeClass(Class<?> resultTypeClass) {
        this.resultTypeClass = resultTypeClass;
    }

    public String getStatementType() {
        return statementType;
    }

    public void setStatementType(String statementType) {
        this.statementType = statementType;
    }

    public DefaultSqlSource getSqlSource() {
        return sqlSource;
    }

    public void setSqlSource(DefaultSqlSource sqlSource) {
        this.sqlSource = sqlSource;
    }
}
