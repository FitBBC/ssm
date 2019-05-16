package com.bbc.mybatis;

import com.bbc.mybatis.mappers.UserMapper;
import com.bbc.mybatis.po.User;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.sql.SQLOutput;

/**
 * @author fitbbc
 * @date 2019/05/16
 */
public class mybatisTest {

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void init(){
        String resource = "SqlMapConfig.xml";
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void test(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            PageHelper.startPage(1,1);
            User user = userMapper.selectByPrimaryKey(1);
            System.out.println(user.toString());
        }catch (Exception e){
            System.out.println(e.getStackTrace());
        }finally {
            sqlSession.close();
        }
    }
}
