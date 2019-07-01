package com.orchestrated.openlegacy.services.controllers;

import javax.inject.Inject;

import org.openlegacy.core.annotations.OpenlegacyDesigntime;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orchestrated.openlegacy.services.As400Service;
import com.orchestrated.openlegacy.services.As400Service.As400In;
import com.orchestrated.openlegacy.services.As400Service.As400Out;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.AuthorizationScope;

@RestController
@RequestMapping(path = "/api/as400", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value="As400")
@OpenlegacyDesigntime(editor = "WebServiceEditor")
public class As400Controller {

    @Inject
    private As400Service as400Service;

    @PostMapping
    @ApiOperation(value="As400 GET operation", response = As400Out.class, 
        authorizations = { @Authorization(value = "oauth2-password", scopes = { @AuthorizationScope(scope = "test", description = "Test") }) , @Authorization(value = "oauth2", scopes = { @AuthorizationScope(scope = "test", description = "Test") }) })
    public As400Out getAs400(@RequestBody As400In as400In) throws Exception {
        return as400Service.getAs400(as400In);
    }
}