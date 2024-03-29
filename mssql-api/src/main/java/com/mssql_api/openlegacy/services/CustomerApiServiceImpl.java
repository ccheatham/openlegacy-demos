package com.mssql_api.openlegacy.services;

import javax.inject.Inject;

import org.openlegacy.core.annotations.OpenlegacyDesigntime;
import org.openlegacy.core.annotations.services.BindFrom;
import org.openlegacy.core.annotations.services.BindInputs;
import org.openlegacy.core.annotations.services.BindOutputs;
import org.openlegacy.core.annotations.services.EntityMapping;
import org.openlegacy.core.annotations.services.LogServiceUsage;
import org.openlegacy.core.annotations.services.Service;
import org.openlegacy.core.annotations.services.ServiceMethod;
import org.openlegacy.core.exceptions.rest.exception.ConflictException;
import org.openlegacy.core.exceptions.rest.exception.NotFoundException;
import org.openlegacy.impl.services.ws.ServiceBinder;
import org.springframework.stereotype.Component;

import com.mssql_sdk.openlegacy.entities.Customer;
import com.mssql_sdk.openlegacy.repositories.CustomerRepository;

@Service(name = "CustomerApi")
@OpenlegacyDesigntime(editor = "WebServiceEditor")
@Component
public class CustomerApiServiceImpl implements CustomerApiService {

    @Inject
    private CustomerRepository customerRepository;

    @Inject
    private ServiceBinder serviceBinder;

    @Override
    @LogServiceUsage
    @ServiceMethod(name = "findAll", entities = {
            @EntityMapping(alias = "customer", entity = com.mssql_sdk.openlegacy.entities.Customer.class) })
    @BindInputs(endpoints = {})
    @BindOutputs(endpoints = {})
    public FindAllOut findAll() throws Exception {
        FindAllOut findAllOut = new FindAllOut();
        /* autogenerated - sessions block start*/
        ;
        /* autogenerated - sessions block end */
        ;
        /* autogenerated - execution block start */
        ;
        findAllOut.setRecords(customerRepository.findAll());
        /* autogenerated - execution block end */
        ;
        /* autogenerated - output block start */
        ;
        findAllOut = serviceBinder.bindOutput(this, "findAll", findAllOut, new String[] { });
        /* autogenerated - output block end */
        ;
        return findAllOut;
    }
    
    @Override
    @LogServiceUsage
    @ServiceMethod(name = "findAllPageable", entities = {
            @EntityMapping(alias = "customer", entity = com.mssql_sdk.openlegacy.entities.Customer.class) })
    @BindInputs(endpoints = {})
    @BindOutputs(endpoints = {})
    public FindAllPageableOut findAllPageable(FindAllPageableIn findAllPageableIn) throws Exception {
        FindAllPageableOut findAllPageableOut = new FindAllPageableOut();
        /* autogenerated - sessions block start*/
        ;
        /* autogenerated - sessions block end */
        ;
        /* autogenerated - execution block start */
        ;
        findAllPageableOut.setPage(customerRepository.findAll(findAllPageableIn.getPageable()));
        /* autogenerated - execution block end */
        ;
        /* autogenerated - output block start */
        ;
        findAllPageableOut = serviceBinder.bindOutput(this, "findAllPageable", findAllPageableOut, new String[] { "findAllPageableIn" }, findAllPageableIn);
        /* autogenerated - output block end */
        ;
        return findAllPageableOut;
    }
    
    @Override
    @LogServiceUsage
    @ServiceMethod(name = "findOne", entities = {
            @EntityMapping(alias = "customer", entity = com.mssql_sdk.openlegacy.entities.Customer.class) })
    @BindInputs(endpoints = {})
    @BindOutputs(endpoints = { @BindFrom(endpoint = "customer", expression = "customer") })
    public FindOneOut findOne(FindOneIn findOneIn) throws Exception {
        FindOneOut findOneOut = new FindOneOut();
        /* autogenerated - sessions block start*/
        ;
        /* autogenerated - sessions block end */
        ;
        /* autogenerated - execution block start */
        ;
        Customer customer = customerRepository.findOne(findOneIn.getId());
        if (customer == null) {
            throw new NotFoundException("Could not find entity with ID: " + findOneIn.getId());
        }
        /* autogenerated - execution block end */
        ;
        /* autogenerated - output block start */
        ;
        findOneOut = serviceBinder.bindOutput(this, "findOne", findOneOut, new String[] { "findOneIn", "customer" }, findOneIn, customer);
        /* autogenerated - output block end */
        ;
        return findOneOut;
    }
        
    @Override
    @LogServiceUsage
    @ServiceMethod(name = "create", entities = {
            @EntityMapping(alias = "customer", entity = com.mssql_sdk.openlegacy.entities.Customer.class) })
    @BindInputs(endpoints = {})
    @BindOutputs(endpoints = { @BindFrom(endpoint = "customer", expression = "customer") })
    public CreateOut create(CreateIn createIn) throws Exception {
        CreateOut createOut = new CreateOut();
        /* autogenerated - sessions block start*/
        ;
        /* autogenerated - sessions block end */
        ;
        /* autogenerated - execution block start */
        ;
        Customer customer = createIn.getCustomer();
        Integer id = customer.getId();
        if (customerRepository.exists(id)) {
            throw new ConflictException("Entity with ID: " + id + " already exists");
        }
        customer = serviceBinder.bindInputs(this, "create", "customer", customer, createIn);
        customer = customerRepository.save(customer);
        /* autogenerated - execution block end */
        ;
        /* autogenerated - output block start */
        ;
        createOut = serviceBinder.bindOutput(this, "create", createOut, new String[] { "createIn", "customer" }, createIn, customer);
        /* autogenerated - output block end */
        ;
        return createOut;
    }
        
    @Override
    @LogServiceUsage
    @ServiceMethod(name = "update", entities = {
            @EntityMapping(alias = "customer", entity = com.mssql_sdk.openlegacy.entities.Customer.class) })
    @BindInputs(endpoints = {})
    @BindOutputs(endpoints = { @BindFrom(endpoint = "customer", expression = "customer") })
    public UpdateOut update(UpdateIn updateIn) throws Exception {
        UpdateOut updateOut = new UpdateOut();
        /* autogenerated - sessions block start*/
        ;
        /* autogenerated - sessions block end */
        ;
        /* autogenerated - execution block start */
        ;
        Customer customer = updateIn.getCustomer();
        Integer id = customer.getId();
        if (!customerRepository.exists(id)) {
            throw new NotFoundException("Could not find entity with ID: " + id);
        }
        customer = serviceBinder.bindInputs(this, "update", "customer", customer, updateIn);
        customer = customerRepository.save(customer);
        /* autogenerated - execution block end */
        ;
        /* autogenerated - output block start */
        ;
        updateOut = serviceBinder.bindOutput(this, "update", updateOut, new String[] { "updateIn", "customer" }, updateIn, customer);
        /* autogenerated - output block end */
        ;
        return updateOut;
    }
        
    @Override
    @ServiceMethod(name = "delete")
    @LogServiceUsage
    public DeleteOut delete(DeleteIn deleteIn) throws Exception {
        DeleteOut deleteOut = new DeleteOut();
        /* autogenerated - sessions block start*/
        ;
        /* autogenerated - sessions block end */
        ;
        /* autogenerated - execution block start */
        ;
        customerRepository.delete(deleteIn.getId());
        deleteOut.setDeleted(!customerRepository.exists(deleteIn.getId()));
        /* autogenerated - execution block end */
        ;
        /* autogenerated - output block start */
        ;
        deleteOut = serviceBinder.bindOutput(this, "delete", deleteOut, new String[] { "deleteIn" }, deleteIn);
        /* autogenerated - output block end */
        ;
        return deleteOut;
    }
}

