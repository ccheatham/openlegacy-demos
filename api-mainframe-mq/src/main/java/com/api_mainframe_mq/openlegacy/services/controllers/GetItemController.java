package com.api_mainframe_mq.openlegacy.services.controllers;

import javax.inject.Inject;

import org.openlegacy.core.annotations.OpenlegacyDesigntime;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api_mainframe_mq.openlegacy.services.GetItemService;
import com.api_mainframe_mq.openlegacy.services.GetItemService.GetItemIn;
import com.api_mainframe_mq.openlegacy.services.GetItemService.GetItemOut;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.AuthorizationScope;

@RestController
@RequestMapping(path = "/api/getitem", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value="GetItem")
@OpenlegacyDesigntime(editor = "WebServiceEditor")
public class GetItemController {

    @Inject
    private GetItemService getItemService;

    @PostMapping
    @ApiOperation(value="GetItem GET operation", response = GetItemOut.class, 
        authorizations = { @Authorization(value = "oauth2-password", scopes = { @AuthorizationScope(scope = "test", description = "Test") }) , @Authorization(value = "oauth2", scopes = { @AuthorizationScope(scope = "test", description = "Test") }) })
    public GetItemOut getGetItem(@RequestBody GetItemIn getItemIn) throws Exception {
        return getItemService.getGetItem(getItemIn);
    }
}