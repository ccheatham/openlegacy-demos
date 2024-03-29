package com.orchestrated.openlegacy.services;

import javax.inject.Inject;

import org.openlegacy.core.annotations.OpenlegacyDesigntime;
import org.openlegacy.core.annotations.services.BindFrom;
import org.openlegacy.core.annotations.services.BindInputs;
import org.openlegacy.core.annotations.services.BindOutputs;
import org.openlegacy.core.annotations.services.BindTo;
import org.openlegacy.core.annotations.services.EntityMapping;
import org.openlegacy.core.annotations.services.LogServiceUsage;
import org.openlegacy.core.annotations.services.Service;
import org.openlegacy.core.annotations.services.ServiceMethod;
import org.openlegacy.core.rpc.RpcSession;
import org.openlegacy.impl.services.ws.ServiceBinder;
import org.openlegacy.utils.ActionUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.as400_rpc_sdk.openlegacy.TestOv;

/**
 *  A service implementation which invokes OpenLegacy API, and returns a service output.
 *  The code below should be customize to perform a working scenario which goes through the relevant screens.
 *  Can be tested by invoking the test As400ServiceTest.
 *  The interface As400Service can be customized to enabling passing parameters to the service, and this class can consume the parameters within the relevant screens.
 */
@Service(name = "As400")
@OpenlegacyDesigntime(editor = "WebServiceEditor")
@Component
public class As400ServiceImpl implements As400Service {

    @Inject
    private ApplicationContext applicationContext;


    @Inject
    private ServiceBinder serviceBinder;

    @Override
    @ServiceMethod(name = "getAs400", entities = {
            @EntityMapping(alias="testOv", entity=com.as400_rpc_sdk.openlegacy.TestOv.class)})
    @LogServiceUsage
    @BindInputs(endpoints = {
            @BindTo(endpoint = "testOv.ent.id", expression = "id")})
    @BindOutputs(endpoints = {
            @BindFrom(endpoint = "name", expression = "testOv.name"),
            @BindFrom(endpoint = "hname", expression = "testOv.hname")})
    public As400Out getAs400(As400In as400In) throws Exception {
        As400Out as400Out = new As400Out();
        /* autogenerated - sessions block start*/
        ;
        RpcSession as400RpcSdkRpcSession = applicationContext.getBean("as400RpcSdkRpcSession", RpcSession.class);
        /* autogenerated - sessions block end */
        ;

        try {
            /* autogenerated - execution block start */;
            TestOv testOv = serviceBinder.bindInputs(this, "getAs400", "testOv", new TestOv(), as400In);
            testOv = as400RpcSdkRpcSession.doAction(ActionUtil.getRpcAction(TestOv.class), testOv);
            /* autogenerated - execution block end */;

            /* autogenerated - output block start */;
            as400Out = serviceBinder.bindOutput(this, "getAs400", as400Out, new String[]{"as400In", "testOv"}, as400In, testOv);
            /* autogenerated - output block end */;

            return as400Out;
        } finally {
            /* autogenerated - disconnect block start */
            ;
            as400RpcSdkRpcSession.disconnect();
            /* autogenerated - disconnect block end */
            ;
        }
    }

}
