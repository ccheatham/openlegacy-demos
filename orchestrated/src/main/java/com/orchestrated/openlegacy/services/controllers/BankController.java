package com.orchestrated.openlegacy.services.controllers;

import javax.inject.Inject;

import org.openlegacy.core.annotations.OpenlegacyDesigntime;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orchestrated.openlegacy.services.BankService;
import com.orchestrated.openlegacy.services.BankService.BankIn;
import com.orchestrated.openlegacy.services.BankService.BankOut;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.AuthorizationScope;

@RestController
@RequestMapping(path = "/api/bank", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value="Bank")
@OpenlegacyDesigntime(editor = "WebServiceEditor")
public class BankController {

    @Inject
    private BankService bankService;

    @PostMapping
    @ApiOperation(value="Bank GET operation", response = BankOut.class, 
        authorizations = { @Authorization(value = "oauth2-password", scopes = { @AuthorizationScope(scope = "test", description = "Test") }) , @Authorization(value = "oauth2", scopes = { @AuthorizationScope(scope = "test", description = "Test") }) })
    public BankOut getBank(@RequestBody BankIn bankIn) throws Exception {
        return bankService.getBank(bankIn);
    }
}