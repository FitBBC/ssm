package com.bbc.mybatis.framework.parameter;

import com.bbc.mybatis.framework.MappedStatement;
import com.bbc.mybatis.framework.ParameterMapping;
import com.bbc.mybatis.framework.sqlsource.BoundSql;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @author fitbbc
 * @date 2019/05/14
 */
public class DefaultParameterHandle implements ParameterHandle {

    private Object parameterObject;

    private MappedStatement mappedStatement;

    private BoundSql boundSql;

    public DefaultParameterHandle(Object parameterObject, MappedStatement mappedStatement, BoundSql boundSql) {
        this.parameterObject = parameterObject;
        this.mappedStatement = mappedStatement;
        this.boundSql = boundSql;
    }

    @Override
    public void setParameters(PreparedStatement preparedStatement) {
        try {
            //设置参数,获取解析的参数集合,遍历
            //preparedStatement.setString(1, "zhangsan");
            List<ParameterMapping> parameterMappingList = boundSql.getParameterMappingList();
            for (int i = 0; i < parameterMappingList.size(); i++) {
                ParameterMapping parameterMapping = parameterMappingList.get(i);
                //获取参数类型
                Class<?> parameterTypeClass = parameterMapping.getParameterTypeClass();
                //获取参数名称
                String parameterName = parameterMapping.getParameterName();

                //根据参数类型判断设置参数(8种基本类型,String, map, 集合和数组, pojo)
                if (parameterTypeClass == Integer.class) {
                    Integer value = (Integer) parameterObject;
                    preparedStatement.setObject(i + 1, value);
                } else if (parameterTypeClass == String.class) {
                    String value = (String) parameterObject;
                    preparedStatement.setObject(i + 1, value);
                } else {
                    //pojo,放在最后判断
                    //根据参数名称通过反射获取对象里的属性
                    Field field = parameterTypeClass.getDeclaredField(parameterName);
                    //暴力破解,允许设置属性值
                    field.setAccessible(true);
                    Object o = field.get(parameterObject);
                    preparedStatement.setObject(i + 1, o);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
