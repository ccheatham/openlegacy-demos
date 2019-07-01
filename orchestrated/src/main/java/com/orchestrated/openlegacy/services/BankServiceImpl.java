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

import com.wsdl_sdk.openlegacy.GetBank;

/**
 *  A service implementation which invokes OpenLegacy API, and returns a service output.
 *  The code below should be customize to perform a working scenario which goes through the relevant screens.
 *  Can be tested by invoking the test BankServiceTest.
 *  The interface BankService can be customized to enabling passing parameters to the service, and this class can consume the parameters within the relevant screens.
 */
@Service(name = "Bank")
@OpenlegacyDesigntime(editor = "WebServiceEditor")
@Component
public class BankServiceImpl implements BankService {

    @Inject
    private ApplicationContext applicationContext;


    @Inject
    private ServiceBinder serviceBinder;

    @Override
    @ServiceMethod(name = "getBank", entities = {
            @EntityMapping(alias="getBank", entity=com.wsdl_sdk.openlegacy.GetBank.class)})
    @LogServiceUsage
    @BindInputs(endpoints = {
            @BindTo(endpoint = "getBank.inputParams.getBank.blz", expression = "blz")})
    @BindOutputs(endpoints = {
            @BindFrom(endpoint = "details", expression = "getBank.outputParams.getBankResponse.details")})
    public BankOut getBank(BankIn bankIn) throws Exception {
        BankOut bankOut = new BankOut();
        /* autogenerated - sessions block start*/
        ;
        RpcSession wsdlSdkRpcSession = applicationContext.getBean("wsdlSdkRpcSession", RpcSession.class);
        /* autogenerated - sessions block end */
        ;

        try {
            /* autogenerated - execution block start */;
            GetBank getBank = serviceBinder.bindInputs(this, "getBank", "getBank", new GetBank(), bankIn);
            getBank = wsdlSdkRpcSession.doAction(ActionUtil.getRpcAction(GetBank.class), getBank);
            /* autogenerated - execution block end */;

            /* autogenerated - output block start */;
            bankOut = serviceBinder.bindOutput(this, "getBank", bankOut, new String[]{"bankIn", "getBank"}, bankIn, getBank);
            /* autogenerated - output block end */;

            return bankOut;
        } finally {
            /* autogenerated - disconnect block start */
            ;
            wsdlSdkRpcSession.disconnect();
            /* autogenerated - disconnect block end */
            ;
        }
    }

}
