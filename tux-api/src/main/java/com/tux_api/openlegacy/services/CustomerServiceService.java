package com.tux_api.openlegacy.services;


import org.openlegacy.core.annotations.OpenlegacyDesigntime;
import org.openlegacy.core.annotations.services.Service;

import com.tux_sdk.openlegacy.entities.Getuserdetails;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 *  A service interface and input/output definition for a web service.
 *  Defines the contract between the client and server. The client uses the same interface for testing the service via Java code. 
 *  The interface CustomerServiceService can be customized to enabling passing parameters to the service.     
 */

@Service(name = "CustomerService")
@OpenlegacyDesigntime(editor = "WebServiceEditor")
public interface CustomerServiceService {

    public CustomerServiceOut getCustomerService(CustomerServiceIn customerServiceIn) throws Exception;

    @Getter
    @Setter
    public static class CustomerServiceIn {
        
        Integer id3;
    }
    
    @ApiModel(value="CustomerServiceOut", description="")
    @Getter
    @Setter
    public static class CustomerServiceOut {
        
        @ApiModelProperty(value="Getuserdetails")
        Getuserdetails getuserdetails;
    }
}
