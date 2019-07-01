package com.as400_rpc_sdk.openlegacy;

import org.openlegacy.core.annotations.rpc.Direction;
import org.openlegacy.core.annotations.rpc.RpcField;
import org.openlegacy.core.annotations.rpc.RpcPart;

@RpcPart(name = "TestOvHname")
public class TestOvHname {

    @RpcField(length = 10, direction = Direction.INPUT_OUTPUT, originalName = "FNAME", legacyType = "char")
    private String fname;

    @RpcField(length = 10, direction = Direction.INPUT_OUTPUT, originalName = "LNAME", legacyType = "char")
    private String lname;
}

