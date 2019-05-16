package com.bbc.mybatis.framework.sqlsession;

import com.bbc.mybatis.framework.Configuration;

import java.util.List;

/**
 * SqlSession
 *
 * @author fitbbc
 * @date 2019/05/13
 */
public interface SqlSession {

    public <T> T selectOne(String statementId, Object parameterObject) throws Exception;

    public <T> List<T> selectList(String statementId, Object parameterObject);

    public <T> T insert(String statementId, Object parameterObject);

    public <T> T update(String statementId, Object parameterObject);

    public Integer delete(String statementId, Object parameterObject);
}
