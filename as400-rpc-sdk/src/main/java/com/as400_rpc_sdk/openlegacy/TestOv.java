package com.as400_rpc_sdk.openlegacy;

import java.math.BigDecimal;

import org.openlegacy.core.annotations.rpc.Action;
import org.openlegacy.core.annotations.rpc.Direction;
import org.openlegacy.core.annotations.rpc.Languages;
import org.openlegacy.core.annotations.rpc.RpcActions;
import org.openlegacy.core.annotations.rpc.RpcEntity;
import org.openlegacy.core.annotations.rpc.RpcField;
import org.openlegacy.core.annotations.rpc.RpcNavigation;
import org.openlegacy.core.annotations.rpc.RpcNumericField;
import org.openlegacy.core.rpc.actions.RpcActions.EXECUTE;

@RpcNavigation(category = "")

@RpcEntity(name="TestOv", language=Languages.PCML)
@RpcActions(actions = {
        @Action(action = EXECUTE.class, path = "/QSYS.LIB/OPENLEGA11.LIB/TEST_OV.PGM", displayName = "Execute", alias = "execute" )            })
public class TestOv {

    @RpcField(direction = Direction.INPUT_OUTPUT, originalName = "ENT")
    private TestOvEnt ent;

    @RpcField(direction = Direction.INPUT_OUTPUT, originalName = "NAME")
    private TestOvName name;

    @RpcField(direction = Direction.INPUT_OUTPUT, originalName = "HNAME")
    private TestOvHname hname;

    @RpcField(length = 30, direction = Direction.INPUT_OUTPUT, originalName = "V_ALFA", legacyType = "char")
    private String valfa;

    @RpcField(length = 2, direction = Direction.INPUT_OUTPUT, originalName = "V_BINARY", legacyType = "int2")
    @RpcNumericField(minimumValue = -32768, maximumValue = 32767, intPrecision = 15, decimalPlaces = 0)
    private Short vbinary;

    @RpcField(length = 4, direction = Direction.INPUT_OUTPUT, originalName = "V_SIGNINT", legacyType = "int4")
    @RpcNumericField(minimumValue = -2147483648, maximumValue = 2147483647, intPrecision = 31, decimalPlaces = 0)
    private Integer vsignint;

    @RpcField(length = 4, direction = Direction.INPUT_OUTPUT, originalName = "V_UNSIGNINT", legacyType = "u_int4")
    @RpcNumericField(minimumValue = 0L, maximumValue = 4294967295L, intPrecision = 32, decimalPlaces = 0)
    private Long vunsignint;

    @RpcField(length = 4, direction = Direction.INPUT_OUTPUT, originalName = "V_PACK", legacyType = "packed")
    @RpcNumericField(minimumValue = 0D, maximumValue = 999.9D, decimalPlaces = 1)
    private Double vpack;

    @RpcField(length = 4, direction = Direction.INPUT_OUTPUT, originalName = "V_ZONED", legacyType = "zoned")
    @RpcNumericField(minimumValue = 0D, maximumValue = 999.9D, decimalPlaces = 1)
    private Double vzoned;

    @RpcField(length = 8, direction = Direction.INPUT_OUTPUT, originalName = "V_FLOAT", legacyType = "float8")
    @RpcNumericField(minimumValue = -9223372036854776000D, maximumValue = 9223372036854776000D, decimalPlaces = 0)
    private Double vfloat;

    @RpcField(length = 9, direction = Direction.INPUT_OUTPUT, originalName = "V_ZONED9", legacyType = "zoned")
    @RpcNumericField(minimumValue = 0, maximumValue = 999999999, decimalPlaces = 0)
    private Integer vzoned9;

    @RpcField(length = 18, direction = Direction.INPUT_OUTPUT, originalName = "V_ZONED18", legacyType = "zoned")
    @RpcNumericField(minimumValue = 0L, maximumValue = 1000000000000000000L, decimalPlaces = 0)
    private Long vzoned18;

    @RpcField(length = 18, direction = Direction.INPUT_OUTPUT, originalName = "V_ZONED18_2", legacyType = "zoned")
    @RpcNumericField(decimalPlaces = 2)
    private BigDecimal vzoned182;

    @RpcField(length = 20, direction = Direction.INPUT_OUTPUT, originalName = "V_ZONED20", legacyType = "zoned")
    @RpcNumericField(decimalPlaces = 0)
    private BigDecimal vzoned20;

    @RpcField(length = 20, direction = Direction.INPUT_OUTPUT, originalName = "V_ZONED20_3", legacyType = "zoned")
    @RpcNumericField(decimalPlaces = 3)
    private BigDecimal vzoned203;



}

