package com.bbc.mybatis.framework.sqlsource;

/**
 *
 * @auther: fitbbc
 * @date: 2019/05/09
 */
public class DefaultSqlSource implements SqlSource{

    private String sqlText;

    private Class<?> parameterTypeClass;

    public DefaultSqlSource(String sqlText, Class<?> parameterTypeClass) {
        this.sqlText = sqlText;
        this.parameterTypeClass = parameterTypeClass;
    }

    @Override
    public BoundSql getBoundSql() {
        ParameterMappingTokenHandler parameterMappingTokenHandler = new ParameterMappingTokenHandler(parameterTypeClass);

        GenericTokenParser parser = new GenericTokenParser("#{", "}", parameterMappingTokenHandler);

        String sql = parser.parse(sqlText);
        return new BoundSql(sql, parameterMappingTokenHandler.getParameterMappings());
    }
}
