package com.bbc.mybatis.framework.parameter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

/**
 * @author fitbbc
 * @date 2019/05/14
 */
public interface ParameterHandle {

    public void setParameters(PreparedStatement preparedStatement);
}
