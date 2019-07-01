package com.oracle_table_api.openlegacy.services.controllers;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import org.springframework.http.HttpStatus;
import org.openlegacy.core.annotations.OpenlegacyDesigntime;
import org.openlegacy.swagger.annotations.ApiPageable;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.oracle_table_api.openlegacy.services.OracleTableApiService;
import com.oracle_table_api.openlegacy.services.OracleTableApiService.CreateIn;
import com.oracle_table_api.openlegacy.services.OracleTableApiService.CreateOut;
import com.oracle_table_api.openlegacy.services.OracleTableApiService.DeleteIn;
import com.oracle_table_api.openlegacy.services.OracleTableApiService.DeleteOut;
import com.oracle_table_api.openlegacy.services.OracleTableApiService.FindAllOut;
import com.oracle_table_api.openlegacy.services.OracleTableApiService.FindAllPageableIn;
import com.oracle_table_api.openlegacy.services.OracleTableApiService.FindAllPageableOut;
import com.oracle_table_api.openlegacy.services.OracleTableApiService.FindOneIn;
import com.oracle_table_api.openlegacy.services.OracleTableApiService.FindOneOut;
import com.oracle_table_api.openlegacy.services.OracleTableApiService.UpdateIn;
import com.oracle_table_api.openlegacy.services.OracleTableApiService.UpdateOut;
import com.oracle_table_sdk.openlegacy.entities.Customer;
import com.oracle_table_sdk.openlegacy.repositories.CustomerRepository;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.AuthorizationScope;

@RestController
@RequestMapping(path = "/api/oracletableapi", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "OracleTableApi")
@OpenlegacyDesigntime(editor = "WebServiceEditor")
public class OracleTableApiController {

    @Inject
    OracleTableApiService oracleTableApiService;

    @Inject
   CustomerRepository customerRepository;
    
    @GetMapping
    @ApiOperation(value = "Find all Customer items", response = FindAllOut.class, authorizations = { @Authorization(value = "oauth2", scopes = { @AuthorizationScope(scope = "test", description = "Test") }) })
    public FindAllOut findAll() throws Exception {
        return oracleTableApiService.findAll();
    }

    @GetMapping(path = "/page")
    @ApiPageable
    @ApiOperation(value = "Find all pages of Customer items", response = FindAllPageableOut.class, authorizations = { @Authorization(value = "oauth2", scopes = { @AuthorizationScope(scope = "test", description = "Test") }) })
    public FindAllPageableOut findAllPageable(Pageable page) throws Exception {
        FindAllPageableIn findAllPageableIn = new FindAllPageableIn();
        findAllPageableIn.setPageable(page);
        return oracleTableApiService.findAllPageable(findAllPageableIn);
    }

    @PostMapping(path = "/id")
    @ApiOperation(value = "Find one Customer by ID", response = FindOneOut.class, authorizations = { @Authorization(value = "oauth2", scopes = { @AuthorizationScope(scope = "test", description = "Test") }) })
    public FindOneOut findOne(@RequestBody FindOneIn findOneIn) throws Exception {
        return oracleTableApiService.findOne(findOneIn);
    }

    @PostMapping
    @ApiOperation(value = "Create Customer", response = CreateOut.class, authorizations = { @Authorization(value = "oauth2", scopes = { @AuthorizationScope(scope = "test", description = "Test") }) })
    public CreateOut create(@RequestBody CreateIn createIn) throws Exception {
        return oracleTableApiService.create(createIn);
    }

    @PutMapping
    @ApiOperation(value = "Update Customer", response = UpdateOut.class, authorizations = { @Authorization(value = "oauth2", scopes = { @AuthorizationScope(scope = "test", description = "Test") }) })
    public UpdateOut update(@RequestBody UpdateIn updateIn) throws Exception {
        return oracleTableApiService.update(updateIn);
    }

    @DeleteMapping
    @ApiOperation(value = "Delete Customer", response = DeleteOut.class, authorizations = { @Authorization(value = "oauth2", scopes = { @AuthorizationScope(scope = "test", description = "Test") }) })
    public DeleteOut delete(@RequestBody DeleteIn deleteIn) throws Exception {
        return oracleTableApiService.delete(deleteIn);
    }
    
    @PostMapping(path = "/name/{name}")
    @ApiOperation(value = "Find Customers by Name", response = ResponseEntity.class, authorizations = { @Authorization(value = "oauth2", scopes = { @AuthorizationScope(scope = "test", description = "Test") }) })
    public ResponseEntity<?> findAllByName(@PathVariable String name) throws Exception {
        Optional<Customer> customers = customerRepository.findByName(name);
        return customers.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
