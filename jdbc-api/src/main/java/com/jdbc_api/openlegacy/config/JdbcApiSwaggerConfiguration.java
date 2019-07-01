package com.jdbc_api.openlegacy.config;

import org.openlegacy.swagger.models.SwaggerDocketConfig;
import org.openlegacy.swagger.utils.OLSwaggerDocketUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * Swagger configuration
 */
@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class JdbcApiSwaggerConfiguration {

    @Bean
    public Docket swaggerDocket() {
        return OLSwaggerDocketUtils.createSwaggerDocket(SwaggerDocketConfig.builder()
            .title("jdbc-api")
            .version("0.1")
            .description("jdbc-api API")
            .build());
    }


}

