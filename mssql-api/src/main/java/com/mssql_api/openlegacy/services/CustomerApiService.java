package com.mssql_api.openlegacy.services;

import java.util.List;

import org.openlegacy.core.annotations.OpenlegacyDesigntime;
import org.openlegacy.core.annotations.services.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mssql_sdk.openlegacy.entities.Customer;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Service(name = "CustomerApi")
@OpenlegacyDesigntime(editor = "WebServiceEditor")
public interface CustomerApiService {
    
    public FindAllOut findAll() throws Exception;
    
    @Getter
    @Setter
    public static class FindAllOut {
        private List<Customer> records;
    }
    
    public FindAllPageableOut findAllPageable(FindAllPageableIn findAllPageableIn) throws Exception;
    
    @ApiModel(value="FindAllPageableIn", description="")
    @Getter
    @Setter
    public static class FindAllPageableIn {
        private Pageable pageable;
    }
    
    @Getter
    @Setter
    public static class FindAllPageableOut {
        private Page<Customer> page;
    }
    
    public FindOneOut findOne(FindOneIn findOneIn) throws Exception;
    
    @ApiModel(value="FindOneIn", description="")
    @Getter
    @Setter
    public static class FindOneIn {
        private Integer id;
    }
    
    @Getter
    @Setter
    public static class FindOneOut {
        private Customer customer;
    }
        
    public CreateOut create(CreateIn createIn) throws Exception;
        
    @ApiModel(value="CreateIn", description="")
    @Getter
    @Setter
    public static class CreateIn {
        private Customer customer;
    }
    
    @Getter
    @Setter
    public static class CreateOut {
        private Customer customer;
    }
        
    public UpdateOut update(UpdateIn updateIn) throws Exception;
        
    @ApiModel(value="UpdateIn", description="")
    @Getter
    @Setter
    public static class UpdateIn {
        private Customer customer;
    }
    
    @Getter
    @Setter
    public static class UpdateOut {
        private Customer customer;
    }
        
    public DeleteOut delete(DeleteIn deleteIn) throws Exception;
    
    @ApiModel(value="DeleteIn", description="")
    @Getter
    @Setter
    public static class DeleteIn {
        private Integer id;
    }
    
    @Getter
    @Setter
    public static class DeleteOut {
        private Boolean deleted;
    }
}
