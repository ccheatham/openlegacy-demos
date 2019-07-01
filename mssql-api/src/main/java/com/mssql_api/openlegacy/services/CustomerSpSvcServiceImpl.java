package com.mssql_api.openlegacy.services;

import org.apache.commons.lang3.StringUtils;
import com.mssql_sp_sdk.openlegacy.GetCustomer;
import com.mssql_sp_sdk.openlegacy.GetCustomer.ResultSet;
import org.openlegacy.core.rpc.RpcSession;
import org.openlegacy.core.rpc.actions.RpcActions;

import org.openlegacy.core.annotations.services.*;
import org.openlegacy.core.annotations.OpenlegacyDesigntime;
import org.openlegacy.impl.services.ws.ServiceBinder;
import org.springframework.stereotype.Component;
import org.springframework.context.ApplicationContext;
import javax.inject.Inject;
import org.openlegacy.utils.ActionUtil;

/**
 *  A service implementation which invokes OpenLegacy API, and returns a service output.
 *  The code below should be customize to perform a working scenario which goes through the relevant screens.
 *  Can be tested by invoking the test CustomerSpSvcServiceTest.
 *  The interface CustomerSpSvcService can be customized to enabling passing parameters to the service, and this class can consume the parameters within the relevant screens.
 */
@Service(name = "CustomerSpSvc")
@OpenlegacyDesigntime(editor = "WebServiceEditor")
@Component
public class CustomerSpSvcServiceImpl implements CustomerSpSvcService {

    @Inject
    private ApplicationContext applicationContext;


    @Inject
    private ServiceBinder serviceBinder;

    @Override
    @ServiceMethod(name = "getCustomerSpSvc", entities = {
            @EntityMapping(alias="getCustomer", entity=com.mssql_sp_sdk.openlegacy.GetCustomer.class)})
    @LogServiceUsage
    @BindInputs(endpoints = {
            @BindTo(endpoint = "getCustomer.id", expression = "id")})
    @BindOutputs(endpoints = {
            @BindFrom(endpoint = "resultSet", expression = "getCustomer.resultSet")})
    public CustomerSpSvcOut getCustomerSpSvc(CustomerSpSvcIn customerSpSvcIn) throws Exception {
        CustomerSpSvcOut customerSpSvcOut = new CustomerSpSvcOut();
        /* autogenerated - sessions block start*/
        ;
        RpcSession mssqlSpSdkRpcSession = applicationContext.getBean("mssqlSpSdkRpcSession", RpcSession.class);
        /* autogenerated - sessions block end */
        ;

        try {
            /* autogenerated - execution block start */;
            GetCustomer getCustomer = serviceBinder.bindInputs(this, "getCustomerSpSvc", "getCustomer", new GetCustomer(), customerSpSvcIn);
            getCustomer = mssqlSpSdkRpcSession.doAction(ActionUtil.getRpcAction(GetCustomer.class), getCustomer);
            /* autogenerated - execution block end */;

            /* autogenerated - output block start */;
            customerSpSvcOut = serviceBinder.bindOutput(this, "getCustomerSpSvc", customerSpSvcOut, new String[]{"customerSpSvcIn", "getCustomer"}, customerSpSvcIn, getCustomer);
            /* autogenerated - output block end */;

            return customerSpSvcOut;
        } finally {
            /* autogenerated - disconnect block start */
            ;
            mssqlSpSdkRpcSession.disconnect();
            /* autogenerated - disconnect block end */
            ;
        }
    }

}