package com.mf_rpc_sdk.openlegacy;

import org.openlegacy.core.annotations.rpc.Action;
import org.openlegacy.core.annotations.rpc.Languages;
import org.openlegacy.core.annotations.rpc.RpcActions;
import org.openlegacy.core.annotations.rpc.RpcEntity;
import org.openlegacy.core.annotations.rpc.RpcField;
import org.openlegacy.core.annotations.rpc.RpcNavigation;
import org.openlegacy.core.rpc.actions.RpcActions.EXECUTE;

@RpcNavigation(category = "")

@RpcEntity(name="Fininq2", language=Languages.COBOL)
@RpcActions(actions = {
        @Action(action = EXECUTE.class, path = "FININQ2", displayName = "Execute", alias = "execute" )            })
public class Fininq2 {

    @RpcField(originalName = "DFHCOMMAREA", displayName = "Dfhcommarea")
    private Fininq2Dfhcommarea dfhcommarea;


}

