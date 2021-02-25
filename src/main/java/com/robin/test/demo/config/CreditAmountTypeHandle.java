package com.robin.test.demo.config;

import com.robin.test.demo.entiry.TypeEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedJdbcTypes({JdbcType.INTEGER})
@MappedTypes(TypeEnum.class)
public class CreditAmountTypeHandle extends BaseTypeHandler<TypeEnum> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, TypeEnum typeEnum, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i,typeEnum.getLevel());
    }

    @Override
    public TypeEnum getNullableResult(ResultSet resultSet, String s) throws SQLException {
        int level = resultSet.getInt(s);
        return level == 0 && resultSet.wasNull()?null:TypeEnum.getByLevel(level);

    }

    @Override
    public TypeEnum getNullableResult(ResultSet resultSet, int i) throws SQLException {
        int level = resultSet.getInt(i);
        return level == 0 && resultSet.wasNull()?null:TypeEnum.getByLevel(level);
    }

    @Override
    public TypeEnum getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        int level = callableStatement.getInt(i);
        return level == 0 && callableStatement.wasNull()?null:TypeEnum.getByLevel(level);
    }
}
