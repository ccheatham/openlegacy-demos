package com.as400_rpc_sdk.openlegacy;

import org.openlegacy.core.annotations.rpc.Direction;
import org.openlegacy.core.annotations.rpc.RpcField;
import org.openlegacy.core.annotations.rpc.RpcPart;

@RpcPart(name = "TestOvEnt")
public class TestOvEnt {

    @RpcField(length = 2, direction = Direction.INPUT_OUTPUT, originalName = "ID", legacyType = "char")
    private String id;
}

