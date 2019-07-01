package com.wsdl_sdk.openlegacy;

import org.openlegacy.core.FieldType.NonStructural;
import org.openlegacy.core.annotations.rpc.Action;
import org.openlegacy.core.annotations.rpc.ActionProperty;
import org.openlegacy.core.annotations.rpc.Direction;
import org.openlegacy.core.annotations.rpc.Languages;
import org.openlegacy.core.annotations.rpc.RpcActions;
import org.openlegacy.core.annotations.rpc.RpcEntity;
import org.openlegacy.core.annotations.rpc.RpcField;
import org.openlegacy.core.rpc.actions.RpcActions.EXECUTE;


@RpcEntity(name="GetBank", displayName="Get Bank", language=Languages.WSDL)
@RpcActions(actions = {
        @Action(action = EXECUTE.class, path = "/axis2/services/BLZService", displayName = "Execute", alias = "execute", actionProperties = {
                @ActionProperty(name = "soap.methodInputNamespace", value = "http://thomas-bayer.com/blz/" ),
                    @ActionProperty(name = "soap.methodOutputNamespace", value = "" ),
                    @ActionProperty(name = "soap.soapAction", value = "" )
     }
 )            })
public class GetBank {

    @RpcField(direction = Direction.INPUT, originalName = "inputParams" ,fieldType=NonStructural.class)
    private GetBankInputParams inputParams;

    @RpcField(direction = Direction.OUTPUT, originalName = "outputParams" ,fieldType=NonStructural.class)
    private GetBankOutputParams outputParams;





}

