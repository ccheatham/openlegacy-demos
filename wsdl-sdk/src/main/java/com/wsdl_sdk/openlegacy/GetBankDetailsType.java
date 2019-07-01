package com.wsdl_sdk.openlegacy;

import org.openlegacy.core.annotations.rpc.RpcField;
import org.openlegacy.core.annotations.rpc.RpcPart;

@RpcPart(name = "GetBankDetailsType")
public class GetBankDetailsType {

    @RpcField(originalName = "bezeichnung")
    private String bezeichnung;

    @RpcField(originalName = "bic")
    private String bic;

    @RpcField(originalName = "ort")
    private String ort;

    @RpcField(originalName = "plz")
    private String plz;
}

