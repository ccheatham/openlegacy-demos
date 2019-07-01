package com.tux_sdk.openlegacy.entities;

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

@RpcEntity(name="Getuserdetails", displayName="Getuserdetails", language=Languages.JOLT)
@RpcActions(actions = {
        @Action(action = EXECUTE.class, path = "", displayName = "Execute", alias = "execute" )            })
public class Getuserdetails {

    @RpcField(direction = Direction.INPUT_OUTPUT, originalName = "ID3", legacyType = "INT")
    @RpcNumericField(minimumValue = -2147483648, maximumValue = 2147483647, decimalPlaces = 0)
    private Integer id3;

    @RpcField(direction = Direction.OUTPUT, originalName = "ADDRESS3", legacyType = "STRING")
    private String address3;

    @RpcField(direction = Direction.OUTPUT, originalName = "AGE3", legacyType = "FLOAT")
    @RpcNumericField(minimumValue = -340282346638528860000000000000000000000f, maximumValue = 340282346638528860000000000000000000000f, decimalPlaces = 0)
    private Float age3;

    @RpcField(direction = Direction.OUTPUT, originalName = "CURRENCY3", legacyType = "DOUBLE")
    @RpcNumericField(minimumValue = -340282346638528860000000000000000000000D, maximumValue = 340282346638528860000000000000000000000D, decimalPlaces = 0)
    private Double currency3;

    @RpcField(direction = Direction.OUTPUT, originalName = "YEAR3", legacyType = "SHORT")
    @RpcNumericField(minimumValue = -32768, maximumValue = 32767, decimalPlaces = 0)
    private Short year3;

    @RpcField(direction = Direction.OUTPUT, originalName = "CHILDREN3", legacyType = "BYTE")
    @RpcNumericField(minimumValue = -128, maximumValue = 127, decimalPlaces = 0)
    private Byte children3;
}

