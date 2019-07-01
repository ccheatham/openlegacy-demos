package com.mssql_sp_sdk.openlegacy;

import java.math.BigInteger;
import java.math.BigDecimal;
import java.util.List;
import java.util.Date;
import org.openlegacy.core.annotations.common.*;
import org.openlegacy.core.annotations.rpc.*;
import org.openlegacy.core.FieldType.*;
import org.openlegacy.core.rpc.actions.RpcActions.EXECUTE;
import org.openlegacy.core.db.DBConst;
import org.openlegacy.core.storedproc.RefCursorDescriptor;
import java.sql.Timestamp;


@RpcEntity(name="GetCustomer", language=Languages.TSQL)
@RpcActions(actions = {
        @Action(action = EXECUTE.class, path = "openlegacy.dbo.getCustomer", displayName = "Execute", alias = "execute", actionProperties = {
                @ActionProperty(name = "db_callable_type", value = "P" )
     }
 )            })
public class GetCustomer {

    @FieldAttributes(attributes = {
            @Attribute(key = DBConst.TYPE, value = "4")
    })
    @RpcField(length = 4, direction = Direction.INPUT, originalName = "ID", legacyType = "int")
    @RpcNumericField()
    private Integer id;

    @FieldAttributes(attributes = {
            @Attribute(key = DBConst.TYPE, value = "2002"),
            @Attribute(key = DBConst.RESULT_SET, value = "db.resultSet")
    })
    @RpcField(direction = Direction.OUTPUT, originalName = "ResultSet", legacyType = "")
    @RpcList(count = 0)
    private List<ResultSet> resultSet;

    @RpcPart(name = "ResultSet", originalName = "ResultSet")
    public static class ResultSet {

        @FieldAttributes(attributes = {
                @Attribute(key = DBConst.TYPE, value = "4")
        })
        @RpcField(length = 4, direction = Direction.OUTPUT, originalName = "ID", legacyType = "int")
        @RpcNumericField()
        private Integer id;

        @FieldAttributes(attributes = {
                @Attribute(key = DBConst.TYPE, value = "12")
        })
        @RpcField(length = 100, direction = Direction.OUTPUT, originalName = "NAME", legacyType = "varchar")
        private String naME;

        @FieldAttributes(attributes = {
                @Attribute(key = DBConst.TYPE, value = "12")
        })
        @RpcField(length = 100, direction = Direction.OUTPUT, originalName = "BANKCODE", legacyType = "varchar")
        private String baNKCODE;

        @FieldAttributes(attributes = {
                @Attribute(key = DBConst.TYPE, value = "12")
        })
        @RpcField(length = 100, direction = Direction.OUTPUT, originalName = "EMAIL", legacyType = "varchar")
        private String emAIL;
    }
}

