package com.api_mainframe_mq.openlegacy.services;


import org.openlegacy.core.annotations.OpenlegacyDesigntime;
import org.openlegacy.core.annotations.services.Service;

import com.mainframe_mq.openlegacy.Itemde.Item;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 *  A service interface and input/output definition for a web service.
 *  Defines the contract between the client and server. The client uses the same interface for testing the service via Java code. 
 *  The interface GetItemService can be customized to enabling passing parameters to the service.     
 */

@Service(name = "GetItem")
@OpenlegacyDesigntime(editor = "WebServiceEditor")
public interface GetItemService {

    public GetItemOut getGetItem(GetItemIn getItemIn) throws Exception;

    @Getter
    @Setter
    public static class GetItemIn {
        
        Integer itemNum;
    }
    
    @ApiModel(value="GetItemOut", description="")
    @Getter
    @Setter
    public static class GetItemOut {
        
        @ApiModelProperty(value="Item")
        Item item;
    }
}
