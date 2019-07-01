package com.mf_rpc_sdk.openlegacy;

import java.util.List;

import org.openlegacy.core.annotations.rpc.RpcField;
import org.openlegacy.core.annotations.rpc.RpcList;
import org.openlegacy.core.annotations.rpc.RpcPart;

@RpcPart(name = "Fininq2Dfhcommarea")
public class Fininq2Dfhcommarea {

    @RpcField(length = 10, originalName = "CUST-ID", legacyType = "Char")
    private String custId;

    @RpcField(originalName = "CREDIT-CARDS", displayName = "CREDITCARDS")
    @RpcList(count = 5)
    private List<Fininq2CreditCards> creditCards;
}

