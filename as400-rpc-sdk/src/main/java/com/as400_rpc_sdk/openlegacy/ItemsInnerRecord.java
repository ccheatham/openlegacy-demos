package com.as400_rpc_sdk.openlegacy;

import org.openlegacy.core.annotations.rpc.RpcField;
import org.openlegacy.core.annotations.rpc.RpcNumericField;
import org.openlegacy.core.annotations.rpc.RpcPart;

@RpcPart(name = "ItemsInnerRecord")
public class ItemsInnerRecord {

    @RpcField(length = 4, originalName = "ITEM-NUMBER", legacyType = "zoned")
    @RpcNumericField(minimumValue = 0, maximumValue = 9999, decimalPlaces = 0)
    private Integer itemNumber;

    @RpcField(length = 16, originalName = "ITEM-NAME", legacyType = "char")
    private String itemName;

    @RpcField(length = 28, originalName = "DESCRIPTION", legacyType = "char")
    private String description;
}

