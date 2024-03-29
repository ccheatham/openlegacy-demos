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

import com.mf_rpc_sdk.openlegacy.Fininq2;

/**
 *  A service implementation which invokes OpenLegacy API, and returns a service output.
 *  The code below should be customize to perform a working scenario which goes through the relevant screens.
 *  Can be tested by invoking the test CreditCardsServiceTest.
 *  The interface CreditCardsService can be customized to enabling passing parameters to the service, and this class can consume the parameters within the relevant screens.
 */
@Service(name = "CreditCards")
@OpenlegacyDesigntime(editor = "WebServiceEditor")
@Component
public class CreditCardsServiceImpl implements CreditCardsService {

    @Inject
    private ApplicationContext applicationContext;


    @Inject
    private ServiceBinder serviceBinder;

    @Override
    @ServiceMethod(name = "getCreditCards", entities = {
            @EntityMapping(alias="fininq2", entity=com.mf_rpc_sdk.openlegacy.Fininq2.class)})
    @LogServiceUsage
    @BindInputs(endpoints = {
            @BindTo(endpoint = "fininq2.dfhcommarea.custId", expression = "custId")})
    @BindOutputs(endpoints = {
            @BindFrom(endpoint = "creditCards", expression = "fininq2.dfhcommarea.creditCards")})
    public CreditCardsOut getCreditCards(CreditCardsIn creditCardsIn) throws Exception {
        CreditCardsOut creditCardsOut = new CreditCardsOut();
        /* autogenerated - sessions block start*/
        ;
        RpcSession mfRpcSdkRpcSession = applicationContext.getBean("mfRpcSdkRpcSession", RpcSession.class);
        /* autogenerated - sessions block end */
        ;

        try {
            /* autogenerated - execution block start */;
            Fininq2 fininq2 = serviceBinder.bindInputs(this, "getCreditCards", "fininq2", new Fininq2(), creditCardsIn);
            fininq2 = mfRpcSdkRpcSession.doAction(ActionUtil.getRpcAction(Fininq2.class), fininq2);
            /* autogenerated - execution block end */;

            /* autogenerated - output block start */;
            creditCardsOut = serviceBinder.bindOutput(this, "getCreditCards", creditCardsOut, new String[]{"creditCardsIn", "fininq2"}, creditCardsIn, fininq2);
            /* autogenerated - output block end */;

            return creditCardsOut;
        } finally {
            /* autogenerated - disconnect block start */
            ;
            mfRpcSdkRpcSession.disconnect();
            /* autogenerated - disconnect block end */
            ;
        }
    }

}
