package com.oracle_table_api.openlegacy.services;

import java.util.List;
import java.util.Optional;
import org.openlegacy.core.annotations.OpenlegacyDesigntime;
import org.openlegacy.core.annotations.services.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.oracle_table_sdk.openlegacy.entities.Customer;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Service(name = "OracleTableApi")
@OpenlegacyDesigntime(editor = "WebServiceEditor")
public interface OracleTableApiService {
    
    public FindAllOut findAll() throws Exception;
    
    @Getter
    @Setter
    public static class FindAllOut {
        private List<Customer> records;
    }
    
    @Getter
    @Setter
    public static class FindAllByNameOut {
        private Optional<Customer> records;
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
        private Long id;
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
        private Long id;
    }
    
    @Getter
    @Setter
    public static class DeleteOut {
        private Boolean deleted;
    }
}
