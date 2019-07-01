package com.wsdl_sdk.openlegacy;

import org.openlegacy.core.annotations.common.Attribute;
import org.openlegacy.core.annotations.common.FieldAttributes;
import org.openlegacy.core.annotations.rpc.RpcField;
import org.openlegacy.core.annotations.rpc.RpcPart;
import org.openlegacy.core.soap.SoapConst;

@RpcPart(name = "GetBankGetBankType")
public class GetBankGetBankType {

    @FieldAttributes(attributes = {
            @Attribute(key = SoapConst.PREFIX_ATTRIBUTE, value = "ns_p0")
    })
    @RpcField(originalName = "blz")
    private String blz;
}

