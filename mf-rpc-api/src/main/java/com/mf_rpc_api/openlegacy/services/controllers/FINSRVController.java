package com.mf_rpc_api.openlegacy.services.controllers;

import javax.inject.Inject;

import org.openlegacy.core.annotations.OpenlegacyDesigntime;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mf_rpc_api.openlegacy.services.FINSRVService;
import com.mf_rpc_api.openlegacy.services.FINSRVService.FINSRVIn;
import com.mf_rpc_api.openlegacy.services.FINSRVService.FINSRVOut;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.AuthorizationScope;

@RestController
@RequestMapping(path = "/api/finsrv", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value="FINSRV")
@OpenlegacyDesigntime(editor = "WebServiceEditor")
public class FINSRVController {

    @Inject
    private FINSRVService fINSRVService;

    @PostMapping
    @ApiOperation(value="FINSRV GET operation", response = FINSRVOut.class, 
        authorizations = { @Authorization(value = "oauth2-password", scopes = { @AuthorizationScope(scope = "test", description = "Test") }) , @Authorization(value = "oauth2", scopes = { @AuthorizationScope(scope = "test", description = "Test") }) })
    public FINSRVOut getFINSRV(@RequestBody FINSRVIn fINSRVIn) throws Exception {
        return fINSRVService.getFINSRV(fINSRVIn);
    }
}