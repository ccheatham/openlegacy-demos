package com.mssql_api.openlegacy.services;

import java.util.List;

import org.openlegacy.core.annotations.OpenlegacyDesigntime;
import org.openlegacy.core.annotations.services.Service;

import com.mssql_sp_sdk.openlegacy.GetCustomer.ResultSet;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 *  A service interface and input/output definition for a web service.
 *  Defines the contract between the client and server. The client uses the same interface for testing the service via Java code. 
 *  The interface CustomerSpSvcService can be customized to enabling passing parameters to the service.     
 */

@Service(name = "CustomerSpSvc")
@OpenlegacyDesigntime(editor = "WebServiceEditor")
public interface CustomerSpSvcService {

    public CustomerSpSvcOut getCustomerSpSvc(CustomerSpSvcIn customerSpSvcIn) throws Exception;

    @Getter
    @Setter
    public static class CustomerSpSvcIn {
        
        Integer id;
    }
    
    @ApiModel(value="CustomerSpSvcOut", description="")
    @Getter
    @Setter
    public static class CustomerSpSvcOut {
        
        @ApiModelProperty(value="Result Set")
        List<ResultSet> resultSet;
    }
}
