package com.bbc.mybatis.framework.resultset;

import com.bbc.mybatis.framework.MappedStatement;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fitbbc
 * @date 2019/05/15
 */
public class DefaultResultSetHandle implements ResultSetHandle {
    private MappedStatement mappedStatement;

    public DefaultResultSetHandle(MappedStatement mappedStatement) {
        this.mappedStatement = mappedStatement;
    }

    @Override
    public List<Object> handleResultSets(Statement statement) {
        List<Object> results = new ArrayList();
        try{
            Class<?> resultTypeClass = mappedStatement.getResultTypeClass();
            ResultSet resultSet = statement.getResultSet();
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
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return results;
    }
}
