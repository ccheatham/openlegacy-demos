package com.docker_mf_rpc_api.openlegacy.config;

import org.openlegacy.core.auth.DefaultProtectedEndpoints;
import org.openlegacy.oauth.client.properties.OLOauthClientProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * Spring security oauth2 resource server configuration
 */
@Configuration
@EnableResourceServer
@EnableWebSecurity
public class SecurityConfiguration extends ResourceServerConfigurerAdapter {

    private final OLOauthClientProperties olOauthClientProperties;

    public SecurityConfiguration(OLOauthClientProperties olOauthClientProperties) {
        this.olOauthClientProperties = olOauthClientProperties;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers(this.olOauthClientProperties.getIgnored().toArray(new String[] {})).permitAll()
            .antMatchers("/**/actuator/health", "/**/actuator/info").permitAll()
            .antMatchers(DefaultProtectedEndpoints.getEndpointArray()).access(olOauthClientProperties.getAccessExpression())
            .anyRequest().authenticated();
    }

}

