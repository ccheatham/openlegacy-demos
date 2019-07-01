package com.mssql_api.openlegacy.services.controllers;

import javax.inject.Inject;

import org.openlegacy.core.annotations.OpenlegacyDesigntime;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mssql_api.openlegacy.services.CustomerSpSvcService;
import com.mssql_api.openlegacy.services.CustomerSpSvcService.CustomerSpSvcIn;
import com.mssql_api.openlegacy.services.CustomerSpSvcService.CustomerSpSvcOut;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.AuthorizationScope;

@RestController
@RequestMapping(path = "/api/customerspsvc", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value="CustomerSpSvc")
@OpenlegacyDesigntime(editor = "WebServiceEditor")
public class CustomerSpSvcController {

    @Inject
    private CustomerSpSvcService customerSpSvcService;

    @PostMapping
    @ApiOperation(value="CustomerSpSvc GET operation", response = CustomerSpSvcOut.class, 
        authorizations = { @Authorization(value = "oauth2-password", scopes = { @AuthorizationScope(scope = "test", description = "Test") }) , @Authorization(value = "oauth2", scopes = { @AuthorizationScope(scope = "test", description = "Test") }) })
    public CustomerSpSvcOut getCustomerSpSvc(@RequestBody CustomerSpSvcIn customerSpSvcIn) throws Exception {
        return customerSpSvcService.getCustomerSpSvc(customerSpSvcIn);
    }
}