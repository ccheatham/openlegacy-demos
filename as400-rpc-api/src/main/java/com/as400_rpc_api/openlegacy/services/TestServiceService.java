package com.as400_rpc_api.openlegacy.services;


import org.openlegacy.core.annotations.OpenlegacyDesigntime;
import org.openlegacy.core.annotations.services.Service;

import com.as400_rpc_sdk.openlegacy.TestOvHname;
import com.as400_rpc_sdk.openlegacy.TestOvName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 *  A service interface and input/output definition for a web service.
 *  Defines the contract between the client and server. The client uses the same interface for testing the service via Java code. 
 *  The interface TestServiceService can be customized to enabling passing parameters to the service.     
 */

@Service(name = "TestService")
@OpenlegacyDesigntime(editor = "WebServiceEditor")
public interface TestServiceService {

    public TestServiceOut getTestService(TestServiceIn testServiceIn) throws Exception;

    @Getter
    @Setter
    public static class TestServiceIn {
        
        String id;
    }
    
    @ApiModel(value="TestServiceOut", description="")
    @Getter
    @Setter
    public static class TestServiceOut {
        
        @ApiModelProperty(value="Test Ov Name")
        TestOvName name;
        
        @ApiModelProperty(value="Test Ov Hname")
        TestOvHname hname;
    }
}
