package com.mssql_api.openlegacy.services.controllers;

import javax.inject.Inject;

import org.openlegacy.core.annotations.OpenlegacyDesigntime;
import org.openlegacy.swagger.annotations.ApiPageable;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mssql_api.openlegacy.services.CustomerApiService;
import com.mssql_api.openlegacy.services.CustomerApiService.CreateIn;
import com.mssql_api.openlegacy.services.CustomerApiService.CreateOut;
import com.mssql_api.openlegacy.services.CustomerApiService.DeleteIn;
import com.mssql_api.openlegacy.services.CustomerApiService.DeleteOut;
import com.mssql_api.openlegacy.services.CustomerApiService.FindAllOut;
import com.mssql_api.openlegacy.services.CustomerApiService.FindAllPageableIn;
import com.mssql_api.openlegacy.services.CustomerApiService.FindAllPageableOut;
import com.mssql_api.openlegacy.services.CustomerApiService.FindOneIn;
import com.mssql_api.openlegacy.services.CustomerApiService.FindOneOut;
import com.mssql_api.openlegacy.services.CustomerApiService.UpdateIn;
import com.mssql_api.openlegacy.services.CustomerApiService.UpdateOut;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.AuthorizationScope;

@RestController
@RequestMapping(path = "/api/customerapi", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value="CustomerApi")
@OpenlegacyDesigntime(editor = "WebServiceEditor")
public class CustomerApiController {

    @Inject
    CustomerApiService customerApiService;
    
    @GetMapping
    @ApiOperation(value="Find all Customer items", response = FindAllOut.class,
        authorizations = { @Authorization(value = "oauth2", scopes = { @AuthorizationScope(scope = "test", description = "Test") }) })
    public FindAllOut findAll() throws Exception {
        return customerApiService.findAll();
    }
        
    @GetMapping(path = "/page")
    @ApiPageable
    @ApiOperation(value="Find all pages of Customer items", response = FindAllPageableOut.class,
        authorizations = { @Authorization(value = "oauth2", scopes = { @AuthorizationScope(scope = "test", description = "Test") }) })
    public FindAllPageableOut findAllPageable(Pageable page) throws Exception {
        FindAllPageableIn findAllPageableIn = new FindAllPageableIn();
        findAllPageableIn.setPageable(page);
        return customerApiService.findAllPageable(findAllPageableIn);
    }
        
    @PostMapping(path = "/id")
    @ApiOperation(value="Find one Customer by ID", response = FindOneOut.class,
        authorizations = { @Authorization(value = "oauth2", scopes = { @AuthorizationScope(scope = "test", description = "Test") }) })
    public FindOneOut findOne(@RequestBody FindOneIn findOneIn) throws Exception {
        return customerApiService.findOne(findOneIn);
    }
        
    @PostMapping
    @ApiOperation(value="Create Customer", response = CreateOut.class,
        authorizations = { @Authorization(value = "oauth2", scopes = { @AuthorizationScope(scope = "test", description = "Test") }) })
    public CreateOut create(@RequestBody CreateIn createIn) throws Exception {
        return customerApiService.create(createIn);
    }
        
    @PutMapping
    @ApiOperation(value="Update Customer", response = UpdateOut.class,
        authorizations = { @Authorization(value = "oauth2", scopes = { @AuthorizationScope(scope = "test", description = "Test") }) })
    public UpdateOut update(@RequestBody UpdateIn updateIn) throws Exception {
        return customerApiService.update(updateIn);
    }
        
    @DeleteMapping
    @ApiOperation(value="Delete Customer", response = DeleteOut.class,
        authorizations = { @Authorization(value = "oauth2", scopes = { @AuthorizationScope(scope = "test", description = "Test") }) })
    public DeleteOut delete(@RequestBody DeleteIn deleteIn) throws Exception {
        return customerApiService.delete(deleteIn);
    }
        
}