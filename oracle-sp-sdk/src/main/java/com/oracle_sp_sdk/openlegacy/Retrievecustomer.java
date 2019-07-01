package com.oracle_sp_sdk.openlegacy;

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


@RpcEntity(name="Retrievecustomer", language=Languages.PLSQL)
@RpcActions(actions = {
        @Action(action = EXECUTE.class, path = "SYSTEM.RETRIEVECUSTOMER", displayName = "Execute", alias = "execute" )            })
public class Retrievecustomer {

    @FieldAttributes(attributes = {
            @Attribute(key = DBConst.TYPE, value = "2")
    })
    @RpcField(length = 22, direction = Direction.INPUT, originalName = "CUSTID_IN", legacyType = "NUMBER")
    @RpcNumericField()
    private BigDecimal custidIn;

    @FieldAttributes(attributes = {
            @Attribute(key = DBConst.TYPE, value = "12"),
            @Attribute(key = DBConst.REGISTER_OUTPUT_PARAMETER, value = "true")
    })
    @RpcField(direction = Direction.OUTPUT, originalName = "EMAIL_OUT", legacyType = "VARCHAR2")
    private String emailOut;

    @FieldAttributes(attributes = {
            @Attribute(key = DBConst.TYPE, value = "12"),
            @Attribute(key = DBConst.REGISTER_OUTPUT_PARAMETER, value = "true")
    })
    @RpcField(direction = Direction.OUTPUT, originalName = "NAME_OUT", legacyType = "VARCHAR2")
    private String nameOut;

    @FieldAttributes(attributes = {
            @Attribute(key = DBConst.TYPE, value = "2"),
            @Attribute(key = DBConst.REGISTER_OUTPUT_PARAMETER, value = "true")
    })
    @RpcField(length = 22, direction = Direction.OUTPUT, originalName = "BANKCODE_OUT", legacyType = "NUMBER")
    @RpcNumericField()
    private BigDecimal bankcodeOut;
}

