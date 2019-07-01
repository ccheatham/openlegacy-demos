package com.mysql_table_api.openlegacy.services.controllers;

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

import com.mysql_table_api.openlegacy.services.MySQLTableApiService;
import com.mysql_table_api.openlegacy.services.MySQLTableApiService.CreateIn;
import com.mysql_table_api.openlegacy.services.MySQLTableApiService.CreateOut;
import com.mysql_table_api.openlegacy.services.MySQLTableApiService.DeleteIn;
import com.mysql_table_api.openlegacy.services.MySQLTableApiService.DeleteOut;
import com.mysql_table_api.openlegacy.services.MySQLTableApiService.FindAllOut;
import com.mysql_table_api.openlegacy.services.MySQLTableApiService.FindAllPageableIn;
import com.mysql_table_api.openlegacy.services.MySQLTableApiService.FindAllPageableOut;
import com.mysql_table_api.openlegacy.services.MySQLTableApiService.FindOneIn;
import com.mysql_table_api.openlegacy.services.MySQLTableApiService.FindOneOut;
import com.mysql_table_api.openlegacy.services.MySQLTableApiService.UpdateIn;
import com.mysql_table_api.openlegacy.services.MySQLTableApiService.UpdateOut;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.AuthorizationScope;

@RestController
@RequestMapping(path = "/api/mysqltableapi", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value="MySQLTableApi")
@OpenlegacyDesigntime(editor = "WebServiceEditor")
public class MySQLTableApiController {

    @Inject
    MySQLTableApiService mySQLTableApiService;
    
    @GetMapping
    @ApiOperation(value="Find all Customer items", response = FindAllOut.class,
        authorizations = { @Authorization(value = "oauth2", scopes = { @AuthorizationScope(scope = "test", description = "Test") }) })
    public FindAllOut findAll() throws Exception {
        return mySQLTableApiService.findAll();
    }
        
    @GetMapping(path = "/page")
    @ApiPageable
    @ApiOperation(value="Find all pages of Customer items", response = FindAllPageableOut.class,
        authorizations = { @Authorization(value = "oauth2", scopes = { @AuthorizationScope(scope = "test", description = "Test") }) })
    public FindAllPageableOut findAllPageable(Pageable page) throws Exception {
        FindAllPageableIn findAllPageableIn = new FindAllPageableIn();
        findAllPageableIn.setPageable(page);
        return mySQLTableApiService.findAllPageable(findAllPageableIn);
    }
        
    @PostMapping(path = "/id")
    @ApiOperation(value="Find one Customer by ID", response = FindOneOut.class,
        authorizations = { @Authorization(value = "oauth2", scopes = { @AuthorizationScope(scope = "test", description = "Test") }) })
    public FindOneOut findOne(@RequestBody FindOneIn findOneIn) throws Exception {
        return mySQLTableApiService.findOne(findOneIn);
    }
        
    @PostMapping
    @ApiOperation(value="Create Customer", response = CreateOut.class,
        authorizations = { @Authorization(value = "oauth2", scopes = { @AuthorizationScope(scope = "test", description = "Test") }) })
    public CreateOut create(@RequestBody CreateIn createIn) throws Exception {
        return mySQLTableApiService.create(createIn);
    }
        
    @PutMapping
    @ApiOperation(value="Update Customer", response = UpdateOut.class,
        authorizations = { @Authorization(value = "oauth2", scopes = { @AuthorizationScope(scope = "test", description = "Test") }) })
    public UpdateOut update(@RequestBody UpdateIn updateIn) throws Exception {
        return mySQLTableApiService.update(updateIn);
    }
        
    @DeleteMapping
    @ApiOperation(value="Delete Customer", response = DeleteOut.class,
        authorizations = { @Authorization(value = "oauth2", scopes = { @AuthorizationScope(scope = "test", description = "Test") }) })
    public DeleteOut delete(@RequestBody DeleteIn deleteIn) throws Exception {
        return mySQLTableApiService.delete(deleteIn);
    }
        
}