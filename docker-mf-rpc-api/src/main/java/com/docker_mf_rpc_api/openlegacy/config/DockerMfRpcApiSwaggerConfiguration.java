package com.docker_mf_rpc_api.openlegacy.config;

import org.openlegacy.swagger.models.SwaggerDocketConfig;
import org.openlegacy.swagger.utils.OLSwaggerDocketUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.service.ClientCredentialsGrant;
import springfox.documentation.service.AuthorizationScope;
import static com.google.common.collect.Lists.newArrayList;

/**
 * Swagger configuration
 */
@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class DockerMfRpcApiSwaggerConfiguration {

    @Bean
    public Docket swaggerDocket() {
        return OLSwaggerDocketUtils.createSwaggerDocket(SwaggerDocketConfig.builder()
            .title("docker-mf-rpc-api")
            .version("0.1")
            .description("docker-mf-rpc-api API")
            .securityScheme(uaaSecurityScheme())
            .build());
    }

    private List<SecurityScheme> uaaSecurityScheme() {
        return Arrays.asList(new OAuthBuilder()
        .name("oauth2")
        .grantTypes(newArrayList(new ClientCredentialsGrant("/uaa/oauth/token")))
        .scopes(newArrayList(new AuthorizationScope("test", "Test")))
        .build());
        }

}

