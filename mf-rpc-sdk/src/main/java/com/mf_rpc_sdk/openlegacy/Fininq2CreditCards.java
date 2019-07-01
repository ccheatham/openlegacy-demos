package com.mf_rpc_sdk.openlegacy;

import org.openlegacy.core.annotations.rpc.RpcField;
import org.openlegacy.core.annotations.rpc.RpcNumericField;
import org.openlegacy.core.annotations.rpc.RpcPart;

@RpcPart(name = "Fininq2CreditCards")
public class Fininq2CreditCards {

    @RpcField(length = 16, originalName = "CARD-NUMBER", legacyType = "Char")
    private String cardNumber;

    @RpcField(length = 16, originalName = "CARD-TYPE", legacyType = "Char")
    private String cardType;

    @RpcField(length = 2, originalName = "CARD-LIMIT", legacyType = "Binary Integer")
    @RpcNumericField(minimumValue = -9999, maximumValue = 9999, decimalPlaces = 0)
    private Integer cardLimit;

    @RpcField(length = 2, originalName = "CARD-USAGE", legacyType = "Binary Integer")
    @RpcNumericField(minimumValue = -9999, maximumValue = 9999, decimalPlaces = 0)
    private Integer cardUsage;
}

