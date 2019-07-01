package com.wsdl_sdk.openlegacy;

import org.openlegacy.core.annotations.rpc.RpcField;
import org.openlegacy.core.annotations.rpc.RpcPart;

@RpcPart(name = "GetBankGetBankResponseType")
public class GetBankGetBankResponseType {

    @RpcField(originalName = "details")
    private GetBankDetailsType details;
}

