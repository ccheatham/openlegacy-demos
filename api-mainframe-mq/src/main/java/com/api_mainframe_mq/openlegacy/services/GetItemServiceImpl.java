package com.api_mainframe_mq.openlegacy.services;

import org.apache.commons.lang3.StringUtils;
import com.mainframe_mq.openlegacy.Itemde;
import com.mainframe_mq.openlegacy.Itemde.Item;
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
 *  Can be tested by invoking the test GetItemServiceTest.
 *  The interface GetItemService can be customized to enabling passing parameters to the service, and this class can consume the parameters within the relevant screens.
 */
@Service(name = "GetItem")
@OpenlegacyDesigntime(editor = "WebServiceEditor")
@Component
public class GetItemServiceImpl implements GetItemService {

    @Inject
    private ApplicationContext applicationContext;


    @Inject
    private ServiceBinder serviceBinder;

    @Override
    @ServiceMethod(name = "getGetItem", entities = {
            @EntityMapping(alias="itemde", entity=com.mainframe_mq.openlegacy.Itemde.class)})
    @LogServiceUsage
    @BindInputs(endpoints = {
            @BindTo(endpoint = "itemde.itemNum", expression = "itemNum")})
    @BindOutputs(endpoints = {
            @BindFrom(endpoint = "item", expression = "itemde.item")})
    public GetItemOut getGetItem(GetItemIn getItemIn) throws Exception {
        GetItemOut getItemOut = new GetItemOut();
        /* autogenerated - sessions block start*/
        ;
        RpcSession mainframeMqRpcSession = applicationContext.getBean("mainframeMqRpcSession", RpcSession.class);
        /* autogenerated - sessions block end */
        ;

        try {
            /* autogenerated - execution block start */;
            Itemde itemde = serviceBinder.bindInputs(this, "getGetItem", "itemde", new Itemde(), getItemIn);
            itemde = mainframeMqRpcSession.doAction(ActionUtil.getRpcAction(Itemde.class), itemde);
            /* autogenerated - execution block end */;

            /* autogenerated - output block start */;
            getItemOut = serviceBinder.bindOutput(this, "getGetItem", getItemOut, new String[]{"getItemIn", "itemde"}, getItemIn, itemde);
            /* autogenerated - output block end */;

            return getItemOut;
        } finally {
            /* autogenerated - disconnect block start */
            ;
            mainframeMqRpcSession.disconnect();
            /* autogenerated - disconnect block end */
            ;
        }
    }

}
