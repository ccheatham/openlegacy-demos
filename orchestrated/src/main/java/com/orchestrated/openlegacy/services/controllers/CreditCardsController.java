package com.orchestrated.openlegacy.services.controllers;

import javax.inject.Inject;

import org.openlegacy.core.annotations.OpenlegacyDesigntime;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orchestrated.openlegacy.services.CreditCardsService;
import com.orchestrated.openlegacy.services.CreditCardsService.CreditCardsIn;
import com.orchestrated.openlegacy.services.CreditCardsService.CreditCardsOut;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.AuthorizationScope;

@RestController
@RequestMapping(path = "/api/creditcards", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value="CreditCards")
@OpenlegacyDesigntime(editor = "WebServiceEditor")
public class CreditCardsController {

    @Inject
    private CreditCardsService creditCardsService;

    @PostMapping
    @ApiOperation(value="CreditCards GET operation", response = CreditCardsOut.class, 
        authorizations = { @Authorization(value = "oauth2-password", scopes = { @AuthorizationScope(scope = "test", description = "Test") }) , @Authorization(value = "oauth2", scopes = { @AuthorizationScope(scope = "test", description = "Test") }) })
    public CreditCardsOut getCreditCards(@RequestBody CreditCardsIn creditCardsIn) throws Exception {
        return creditCardsService.getCreditCards(creditCardsIn);
    }
}