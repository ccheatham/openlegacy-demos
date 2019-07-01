package com.as400_rpc_sdk.openlegacy;

import java.util.List;

import org.openlegacy.core.annotations.rpc.Action;
import org.openlegacy.core.annotations.rpc.Languages;
import org.openlegacy.core.annotations.rpc.RpcActions;
import org.openlegacy.core.annotations.rpc.RpcEntity;
import org.openlegacy.core.annotations.rpc.RpcField;
import org.openlegacy.core.annotations.rpc.RpcList;
import org.openlegacy.core.annotations.rpc.RpcNavigation;
import org.openlegacy.core.rpc.actions.RpcActions.EXECUTE;

@RpcNavigation(category = "")

@RpcEntity(name="Items", language=Languages.COBOL)
@RpcActions(actions = {
        @Action(action = EXECUTE.class, path = "Items", displayName = "Execute", alias = "execute" )            })
public class Items {

    @RpcField(originalName = "INNER-RECORD", displayName = "INNERRECORD")
    @RpcList(count = 5)
    private List<ItemsInnerRecord> innerRecord;

}

