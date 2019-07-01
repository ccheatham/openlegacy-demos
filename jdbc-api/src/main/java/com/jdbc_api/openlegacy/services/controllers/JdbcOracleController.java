package com.jdbc_api.openlegacy.services.controllers;

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

import com.jdbc_api.openlegacy.services.JdbcOracleService;
import com.jdbc_api.openlegacy.services.JdbcOracleService.CreateIn;
import com.jdbc_api.openlegacy.services.JdbcOracleService.CreateOut;
import com.jdbc_api.openlegacy.services.JdbcOracleService.DeleteIn;
import com.jdbc_api.openlegacy.services.JdbcOracleService.DeleteOut;
import com.jdbc_api.openlegacy.services.JdbcOracleService.FindAllOut;
import com.jdbc_api.openlegacy.services.JdbcOracleService.FindAllPageableIn;
import com.jdbc_api.openlegacy.services.JdbcOracleService.FindAllPageableOut;
import com.jdbc_api.openlegacy.services.JdbcOracleService.FindOneIn;
import com.jdbc_api.openlegacy.services.JdbcOracleService.FindOneOut;
import com.jdbc_api.openlegacy.services.JdbcOracleService.UpdateIn;
import com.jdbc_api.openlegacy.services.JdbcOracleService.UpdateOut;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.AuthorizationScope;

@RestController
@RequestMapping(path = "/api/jdbcoracle", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value="JdbcOracle")
@OpenlegacyDesigntime(editor = "WebServiceEditor")
public class JdbcOracleController {

    @Inject
    JdbcOracleService jdbcOracleService;
    
    @GetMapping
    @ApiOperation(value="Find all Customer items", response = FindAllOut.class,
        authorizations = { @Authorization(value = "oauth2", scopes = { @AuthorizationScope(scope = "test", description = "Test") }) })
    public FindAllOut findAll() throws Exception {
        return jdbcOracleService.findAll();
    }
        
    @GetMapping(path = "/page")
    @ApiPageable
    @ApiOperation(value="Find all pages of Customer items", response = FindAllPageableOut.class,
        authorizations = { @Authorization(value = "oauth2", scopes = { @AuthorizationScope(scope = "test", description = "Test") }) })
    public FindAllPageableOut findAllPageable(Pageable page) throws Exception {
        FindAllPageableIn findAllPageableIn = new FindAllPageableIn();
        findAllPageableIn.setPageable(page);
        return jdbcOracleService.findAllPageable(findAllPageableIn);
    }
        
    @PostMapping(path = "/id")
    @ApiOperation(value="Find one Customer by ID", response = FindOneOut.class,
        authorizations = { @Authorization(value = "oauth2", scopes = { @AuthorizationScope(scope = "test", description = "Test") }) })
    public FindOneOut findOne(@RequestBody FindOneIn findOneIn) throws Exception {
        return jdbcOracleService.findOne(findOneIn);
    }
        
    @PostMapping
    @ApiOperation(value="Create Customer", response = CreateOut.class,
        authorizations = { @Authorization(value = "oauth2", scopes = { @AuthorizationScope(scope = "test", description = "Test") }) })
    public CreateOut create(@RequestBody CreateIn createIn) throws Exception {
        return jdbcOracleService.create(createIn);
    }
        
    @PutMapping
    @ApiOperation(value="Update Customer", response = UpdateOut.class,
        authorizations = { @Authorization(value = "oauth2", scopes = { @AuthorizationScope(scope = "test", description = "Test") }) })
    public UpdateOut update(@RequestBody UpdateIn updateIn) throws Exception {
        return jdbcOracleService.update(updateIn);
    }
        
    @DeleteMapping
    @ApiOperation(value="Delete Customer", response = DeleteOut.class,
        authorizations = { @Authorization(value = "oauth2", scopes = { @AuthorizationScope(scope = "test", description = "Test") }) })
    public DeleteOut delete(@RequestBody DeleteIn deleteIn) throws Exception {
        return jdbcOracleService.delete(deleteIn);
    }
        
}