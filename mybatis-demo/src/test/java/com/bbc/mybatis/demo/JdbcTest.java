package com.bbc.mybatis.demo;

import com.bbc.mybatis.demo.po.User;
import com.bbc.mybatis.framework.Configuration;
import com.bbc.mybatis.framework.MappedStatement;
import com.bbc.mybatis.framework.builder.XmlConfigBuilder;
import com.bbc.mybatis.framework.sqlsession.SqlSession;
import com.bbc.mybatis.framework.sqlsession.SqlSessionFactory;
import com.bbc.mybatis.framework.sqlsession.SqlSessionFactoryBuilder;
import com.bbc.mybatis.framework.sqlsource.BoundSql;
import com.bbc.mybatis.framework.ParameterMapping;
import com.bbc.mybatis.framework.sqlsource.SqlSource;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther: fitbbc
 * @date: 2019/05/13
 */
public class JdbcTest {
    @Test
    public void test(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ssm?characterEncoding=utf-8", "root", "123asd!@#QWE");

            String sql = "select * from user where username = ?";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, "zhangsan");

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                System.out.println(resultSet.getString("id") + " " + resultSet.getString("username"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    @Test
    public void test2(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //Class.forName("com.mysql.jdbc.Driver");

            //connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ssm?characterEncoding=utf-8", "root", "123asd!@#QWE");
            //根据配置文件路径,读取配置文件
            String resource = "SqlMapConfig.xml";

            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(resource);

            XmlConfigBuilder xmlConfigBuilder = new XmlConfigBuilder();

            Configuration configuration = xmlConfigBuilder.parse(inputStream);
            //获取数据源
            DataSource dataSource = configuration.getDataSource();
            //创建数据库连接
            connection = dataSource.getConnection();

            Integer id = 1;
            String username = "zhangsan";
            User user = new User();
            user.setUsername("zhangsan");
            //String sql = "select * from user where username = ?";
            //准备sql语句

            //获取statement对象
            MappedStatement mappedStatement = configuration.getMappedStatement("test.findUserById");
            //获取sqlsource,里面封装了未解析的sql语句(包含#{})
            SqlSource sqlSource = mappedStatement.getSqlSource();
            //获取boundsql, 里面封装解析过的sql语句(将#{} 替换成 ?)和参数信息集合(参数名称和参数类型class)
            BoundSql boundSql = sqlSource.getBoundSql();

            String sql = boundSql.getSql();

            String statementType = mappedStatement.getStatementType();
            //根据不同的statement类型执行不同的处理
            if ("prepared".equals(statementType)){
                //预编译
                preparedStatement = connection.prepareStatement(sql);
                //设置参数,获取解析的参数集合,遍历
                //preparedStatement.setString(1, "zhangsan");
                List<ParameterMapping> parameterMappingList = boundSql.getParameterMappingList();
                for(int i=0; i<parameterMappingList.size(); i++){
                    ParameterMapping parameterMapping = parameterMappingList.get(i);
                    //获取参数类型
                    Class<?> parameterTypeClass = parameterMapping.getParameterTypeClass();
                    //获取参数名称
                    String parameterName = parameterMapping.getParameterName();

                    //根据参数类型判断设置参数(8种基本类型,String, map, 集合和数组, pojo)
                    if (parameterTypeClass == Integer.class){
                        Integer value = id;
                        preparedStatement.setObject(i+1, value);
                    }else if (parameterTypeClass == String.class){
                        String value = username;
                        preparedStatement.setObject(i+1, value);
                    }else{
                        //pojo,放在最后判断
                        //根据参数名称通过反射获取对象里的属性
                        Field field = parameterTypeClass.getDeclaredField(parameterName);
                        //暴力破解,允许设置属性值
                        field.setAccessible(true);
                        Object o = field.get(user);
                        preparedStatement.setObject(i+1, o);
                    }

                }
                //执行sql查询
                resultSet = preparedStatement.executeQuery();

                Class<?> resultTypeClass = mappedStatement.getResultTypeClass();
                List<Object> results = new ArrayList();

                while (resultSet.next()){
                    //System.out.println(resultSet.getString("id") + " " + resultSet.getString("username"));
                    //获取返回值类型对象实例
                    Object resultObject = resultTypeClass.newInstance();
                    //获取返回值元数据,遍历反射给实例赋值
                    ResultSetMetaData metaData = resultSet.getMetaData();
                    int columnCount = metaData.getColumnCount();
                    for (int i =1; i<=columnCount; i++){
                        String columnName = metaData.getColumnName(i);

                        //通过反射根据列名获取对象的属性值
                        Field field = resultTypeClass.getDeclaredField(columnName);
                        //暴力访问
                        field.setAccessible(true);
                        //System.out.println(resultSet.getObject(i).getClass());
                        //给对象属性赋值,根据下标获取返回值,从1开始
                        field.set(resultObject, resultSet.getObject(i));

                    }
                    results.add(resultObject);
                }
                for (int i = 0; i < results.size(); i++) {
                    User resultUser = (User)results.get(i);
                    System.out.println(resultUser.toString());
                }
            }else{

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void test3() throws Exception {
        String resource = "SqlMapConfig.xml";
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(resource);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = factory.openSession();
        User user = session.selectOne("test.findUserById", 1);
<<<<<<< HEAD
=======
        System.out.println(user.toString());
>>>>>>> 修改结构目录
    }
}
