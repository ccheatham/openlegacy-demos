package com.wsld_api.openlegacy.services;


import org.openlegacy.core.annotations.OpenlegacyDesigntime;
import org.openlegacy.core.annotations.services.Service;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 *  A service interface and input/output definition for a web service.
 *  Defines the contract between the client and server. The client uses the same interface for testing the service via Java code. 
 *  The interface BankService can be customized to enabling passing parameters to the service.     
 */

@Service(name = "Bank")
@OpenlegacyDesigntime(editor = "WebServiceEditor")
public interface BankService {

    public BankOut getBank(BankIn bankIn) throws Exception;

    @Getter
    @Setter
    public static class BankIn {
        
        String blz;
    }
    
    @ApiModel(value="BankOut", description="")
    @Getter
    @Setter
    public static class BankOut {
        
        @ApiModelProperty(value="Plz")
        String plz;
        
        @ApiModelProperty(value="Ort")
        String ort;
        
        @ApiModelProperty(value="Bic")
        String bic;
        
        @ApiModelProperty(value="Bezeichnung")
        String bezeichnung;
    }
}
