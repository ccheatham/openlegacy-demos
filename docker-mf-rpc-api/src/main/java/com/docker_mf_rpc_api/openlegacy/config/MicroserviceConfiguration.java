package com.docker_mf_rpc_api.openlegacy.config;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Enable eureka client and hystrix when running in any profile other than `dev` (development)
 */
@Configuration
@Profile("dev")
@EnableEurekaClient
@EnableHystrix
public class MicroserviceConfiguration {
}

