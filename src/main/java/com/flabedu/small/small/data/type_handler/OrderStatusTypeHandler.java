package com.flabedu.small.small.data.type_handler;

import com.flabedu.small.small.data.Orders;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedJdbcTypes(JdbcType.VARCHAR)
public class OrderStatusTypeHandler extends BaseTypeHandler<Orders.OrderStatus> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Orders.OrderStatus parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getStatus());
    }

    @Override
    public Orders.OrderStatus getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return Orders.OrderStatus.findOrderStatus(rs.getString(columnName));
    }

    @Override
    public Orders.OrderStatus getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return Orders.OrderStatus.findOrderStatus(rs.getString(columnIndex));
    }

    @Override
    public Orders.OrderStatus getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return Orders.OrderStatus.findOrderStatus(cs.getString(columnIndex));
    }
}
