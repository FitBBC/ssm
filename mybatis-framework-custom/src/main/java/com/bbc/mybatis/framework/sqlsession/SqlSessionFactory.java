package com.bbc.mybatis.framework.sqlsession;

/**
 * @author fitbbc
 * @date 2019/05/13
 */
public interface SqlSessionFactory {

    public SqlSession openSession();
}
