package com.as400_rpc_api.openlegacy.services.controllers;

import javax.inject.Inject;

import org.openlegacy.core.annotations.OpenlegacyDesigntime;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.as400_rpc_api.openlegacy.services.TestServiceService;
import com.as400_rpc_api.openlegacy.services.TestServiceService.TestServiceIn;
import com.as400_rpc_api.openlegacy.services.TestServiceService.TestServiceOut;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.AuthorizationScope;

@RestController
@RequestMapping(path = "/api/testservice", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value="TestService")
@OpenlegacyDesigntime(editor = "WebServiceEditor")
public class TestServiceController {

    @Inject
    private TestServiceService testServiceService;

    @PostMapping
    @ApiOperation(value="TestService GET operation", response = TestServiceOut.class, 
        authorizations = { @Authorization(value = "oauth2-password", scopes = { @AuthorizationScope(scope = "test", description = "Test") }) , @Authorization(value = "oauth2", scopes = { @AuthorizationScope(scope = "test", description = "Test") }) })
    public TestServiceOut getTestService(@RequestBody TestServiceIn testServiceIn) throws Exception {
        return testServiceService.getTestService(testServiceIn);
    }
}