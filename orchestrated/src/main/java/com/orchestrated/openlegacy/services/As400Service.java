package com.orchestrated.openlegacy.services;


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
 *  The interface As400Service can be customized to enabling passing parameters to the service.     
 */

@Service(name = "As400")
@OpenlegacyDesigntime(editor = "WebServiceEditor")
public interface As400Service {

    public As400Out getAs400(As400In as400In) throws Exception;

    @Getter
    @Setter
    public static class As400In {
        
        String id;
    }
    
    @ApiModel(value="As400Out", description="")
    @Getter
    @Setter
    public static class As400Out {
        
        @ApiModelProperty(value="Test Ov Name")
        TestOvName name;
        
        @ApiModelProperty(value="Test Ov Hname")
        TestOvHname hname;
    }
}
