package com.bbc.mybatis;

import com.bbc.mybatis.mappers.UserMapper;
import com.bbc.mybatis.po.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author fitbbc
 * @date 2019/05/16
 */
public class mybatisTest {

    private Logger logger = Logger.getLogger(mybatisTest.class);
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void init()throws IOException {
        String resource = "SqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    /**
     * 测试分页
     */
    @Test
    public void test(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            PageHelper.startPage(1,1);

            List<User> userList = userMapper.selectList(Arrays.asList(1,2,3));
            PageInfo<User> userPageInfo = new PageInfo<>(userList);
            logger.info(userPageInfo.toString());
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }

    /**
     * 测试关联查询
     */
    @Test
    public void test2(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.selectByPrimaryKeyJoinOrder(1);
            System.out.println(user.toString());
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }

    /**
     * 测试缓存
     */
    @Test
    public void test3(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.selectByPrimaryKey(1);

            User user1 = new User();
            user1.setUsername("chenqi");
            user1.setBirthday(new Date());
            user1.setSex("male");
            user1.setAddress("zhengzhou");
            userMapper.insertUser(user1);

            User user2 = userMapper.selectByPrimaryKey(1);
            System.out.println(user);
            System.out.println(user2);
            System.out.println(user1.getUid());
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }

    /**
     * 测试懒加载和n+1问题
     */
    @Test
    public void test4(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            List<User> userList = userMapper.selectList(Arrays.asList(1,2,3));
            for (int i = 0; i < userList.size(); i++) {
                //System.out.println(userList.get(i).getOrders().size());
            }
            System.out.println(userList.get(0).getOrders().size());

            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }
}
