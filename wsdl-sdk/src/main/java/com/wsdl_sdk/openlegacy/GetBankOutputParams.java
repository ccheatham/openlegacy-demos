package com.wsdl_sdk.openlegacy;

import org.openlegacy.core.annotations.rpc.Direction;
import org.openlegacy.core.annotations.rpc.RpcField;
import org.openlegacy.core.annotations.rpc.RpcPart;

@RpcPart(name = "GetBankOutputParams")
public class GetBankOutputParams {

    @RpcField(direction = Direction.OUTPUT, originalName = "getBankResponse")
    private GetBankGetBankResponseType getBankResponse;
}

