package com.oracle_sp_api.openlegacy.services.controllers;

import javax.inject.Inject;

import org.openlegacy.core.annotations.OpenlegacyDesigntime;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oracle_sp_api.openlegacy.services.CustomerServiceService;
import com.oracle_sp_api.openlegacy.services.CustomerServiceService.CustomerServiceIn;
import com.oracle_sp_api.openlegacy.services.CustomerServiceService.CustomerServiceOut;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.AuthorizationScope;

@RestController
@RequestMapping(path = "/api/customerservice", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value="CustomerService")
@OpenlegacyDesigntime(editor = "WebServiceEditor")
public class CustomerServiceController {

    @Inject
    private CustomerServiceService customerServiceService;

    @PostMapping
    @ApiOperation(value="CustomerService GET operation", response = CustomerServiceOut.class, 
        authorizations = { @Authorization(value = "oauth2-password", scopes = { @AuthorizationScope(scope = "test", description = "Test") }) , @Authorization(value = "oauth2", scopes = { @AuthorizationScope(scope = "test", description = "Test") }) })
    public CustomerServiceOut getCustomerService(@RequestBody CustomerServiceIn customerServiceIn) throws Exception {
        return customerServiceService.getCustomerService(customerServiceIn);
    }
}