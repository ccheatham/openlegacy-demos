package com.orchestrated.openlegacy.services;

import java.util.List;

import org.openlegacy.core.annotations.OpenlegacyDesigntime;
import org.openlegacy.core.annotations.services.Service;

import com.mf_rpc_sdk.openlegacy.Fininq2CreditCards;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 *  A service interface and input/output definition for a web service.
 *  Defines the contract between the client and server. The client uses the same interface for testing the service via Java code. 
 *  The interface CreditCardsService can be customized to enabling passing parameters to the service.     
 */

@Service(name = "CreditCards")
@OpenlegacyDesigntime(editor = "WebServiceEditor")
public interface CreditCardsService {

    public CreditCardsOut getCreditCards(CreditCardsIn creditCardsIn) throws Exception;

    @Getter
    @Setter
    public static class CreditCardsIn {
        
        String custId;
    }
    
    @ApiModel(value="CreditCardsOut", description="")
    @Getter
    @Setter
    public static class CreditCardsOut {
        
        @ApiModelProperty(value="Fininq2Credit Cards")
        List<Fininq2CreditCards> creditCards;
    }
}
