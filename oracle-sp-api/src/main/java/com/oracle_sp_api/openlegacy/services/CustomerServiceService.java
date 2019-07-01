package com.oracle_sp_api.openlegacy.services;


import java.math.BigDecimal;

import org.openlegacy.core.annotations.OpenlegacyDesigntime;
import org.openlegacy.core.annotations.services.Service;

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
        
        BigDecimal custidIn;
    }
    
    @ApiModel(value="CustomerServiceOut", description="")
    @Getter
    @Setter
    public static class CustomerServiceOut {
        
        @ApiModelProperty(value="Email Out")
        String emailOut;
        
        @ApiModelProperty(value="Name Out")
        String nameOut;
        
        @ApiModelProperty(value="Bankcode Out")
        BigDecimal bankcodeOut;
    }
}
