package com.wsdl_sdk.openlegacy.config;

import org.openlegacy.core.EntitiesRegistry;
import org.openlegacy.core.beans.CommonBeanNames;
import org.openlegacy.core.beans.RpcBeanNames;
import org.openlegacy.core.loaders.RegistryLoader;
import org.openlegacy.core.modules.SessionModule;
import org.openlegacy.core.modules.menu.MenuBuilder;
import org.openlegacy.core.rpc.MockRpcConnectionFactory;
import org.openlegacy.core.rpc.RpcConnection;
import org.openlegacy.core.rpc.RpcConnectionFactory;
import org.openlegacy.core.rpc.RpcSession;
import org.openlegacy.impl.support.SessionModules;
import org.openlegacy.providers.soap.utils.OLSoapRpcBeanUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.Configuration;
import org.openlegacy.providers.soap.properties.OLSoapProperties;
import org.openlegacy.impl.properties.OLRpcFieldValidationProperties;
import org.openlegacy.impl.rpc.support.binders.converters.ConvertFunctionsHolder;
import org.openlegacy.impl.validator.RpcFieldValidation;
import org.openlegacy.impl.validator.RpcFieldTypeValidation;
import org.openlegacy.impl.rpc.definitions.ConnectorPrimitives;
import org.openlegacy.core.rpc.modules.trail.RpcSessionTrail;
import org.openlegacy.impl.modules.trail.RpcTrailWriter;
import org.openlegacy.impl.properties.OLCommonProperties;

import org.openlegacy.providers.soap.binder.SoapDirectInputBinder;
import org.openlegacy.providers.soap.binder.SoapDirectOutputBinder;
import org.openlegacy.providers.soap.binder.SoapInputBinderCallback;
import org.openlegacy.providers.soap.binder.SoapOutputBinderCallback;
import org.openlegacy.providers.soap.formatter.SoapInputFormatter;
import org.openlegacy.providers.soap.formatter.SoapOutputFormatter;
import org.openlegacy.providers.soap.properties.OLSoapProperties;
import org.openlegacy.providers.soap.properties.OLSoapProperties.OLSoapProjectProperties;
import org.openlegacy.providers.soap.transporter.SoapTransporterFactory;
import java.util.List;
import java.util.Arrays;

/**
 * RPC Configuration
 */
@Configuration
public class WsdlSdkConfiguration {
    private static final String ORCHESTRATED_KEY = "wsdlSdk";
    private static final String[] packagesToScan = new String[] {"com.wsdl_sdk.openlegacy"};

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_SESSION_SUFFIX)
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RpcSession rpcSession(
                @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.RPC_CONNECTION_SUFFIX) RpcConnection rpcConnection,
                @Qualifier(ORCHESTRATED_KEY + CommonBeanNames.SESSION_MODULES_SUFFIX) SessionModules sessionModules) {
        return OLSoapRpcBeanUtils.rpcSession(rpcConnection, sessionModules);
    }

    @Bean(ORCHESTRATED_KEY + CommonBeanNames.SESSION_MODULES_SUFFIX)
    public SessionModules sessionModules(
                @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.RPC_TRAIL_MODULE) SessionModule rpcTrailModule,
                @Qualifier(ORCHESTRATED_KEY + CommonBeanNames.SESSION_REGISTRY_MODULE_SUFFIX) SessionModule sessionRegistryModule,
                @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.RPC_MENU_MODULE_SUFFIX) SessionModule rpcMenuModule,
                @Qualifier(RpcBeanNames.RPC_LOGIN_MODULE) SessionModule rpcLoginModule,
                @Qualifier(RpcBeanNames.RPC_TRANSACTION_MODULE) SessionModule rpcTransactionModule) {
        return OLSoapRpcBeanUtils.sessionModules(Arrays.asList(
            rpcTrailModule,
            sessionRegistryModule,
            rpcMenuModule,
            rpcLoginModule,
            rpcTransactionModule));
    }

    @Bean(ORCHESTRATED_KEY + CommonBeanNames.MENU_BUILDER_SUFFIX)
    public MenuBuilder menuBuilder() {
        return OLSoapRpcBeanUtils.menuBuilder(ORCHESTRATED_KEY);
    }

    @Bean(ORCHESTRATED_KEY + CommonBeanNames.SESSION_REGISTRY_MODULE_SUFFIX)
    public SessionModule sessionRegistryModule() {
        return OLSoapRpcBeanUtils.sessionRegistryModule(ORCHESTRATED_KEY);
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_MENU_MODULE_SUFFIX)
    public SessionModule rpcMenuModule() {
        return OLSoapRpcBeanUtils.rpcMenuModule(ORCHESTRATED_KEY);
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_ENTITIES_REGISTRY_SUFFIX)
    public EntitiesRegistry<?, ?, ?> rpcRegistry(@Qualifier(RpcBeanNames.RPC_REGISTRY_LOADER) RegistryLoader registryLoader) {
        return OLSoapRpcBeanUtils.rpcRegistry(Arrays.asList(packagesToScan), registryLoader);
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_PROVIDER_PRIMITIVES)
    public ConnectorPrimitives connectorPrimitives() {
         return OLSoapRpcBeanUtils.rpcConnectorPrimitives();
    }


    @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_MOCK_CONNECTION_FACTORY_SUFFIX)
    public MockRpcConnectionFactory mockRpcConnectionFactory() {
        return OLSoapRpcBeanUtils.mockRpcConnectionFactory(ORCHESTRATED_KEY);
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_CONNECTION_SUFFIX)
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RpcConnection rpcConnection() {
        return OLSoapRpcBeanUtils.rpcConnection(ORCHESTRATED_KEY);
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_CONNECTION_FACTORY_SUFFIX)
    public RpcConnectionFactory connectionFactory(
            @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.RPC_INPUT_FORMATTER_SUFFIX) SoapInputFormatter inputFormatter,
            @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.RPC_OUTPUT_FORMATTER_SUFFIX) SoapOutputFormatter outputFormatter,
            @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.TRANSPORTER_FACTORY_SUFFIX) SoapTransporterFactory transporterFactory) {
        return OLSoapRpcBeanUtils.connectionFactory(inputFormatter, outputFormatter, transporterFactory);
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.URL_PROPS_SUFFIX)
    public OLSoapProjectProperties urlProjectProperties(OLSoapProperties properties) {
        return OLSoapRpcBeanUtils.projectProperties(ORCHESTRATED_KEY, properties);
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_INPUT_FORMATTER_SUFFIX)
    public SoapInputFormatter inputFormatter(
            @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.URL_PROPS_SUFFIX) OLSoapProjectProperties properties,
            @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.RPC_INPUT_BINDER_SUFFIX) SoapDirectInputBinder binder) {
        return OLSoapRpcBeanUtils.inputFormatter(properties, binder);
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_INPUT_BINDER_SUFFIX)
    public SoapDirectInputBinder inputBinder(
            @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.RPC_INPUT_BINDER_CALLBACK_SUFFIX) SoapInputBinderCallback rpcBindCallback,
            @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.RPC_ENTITY_FIELDS_VALIDATIONS) RpcFieldValidation rpcFieldValidation,
            @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.INPUT_CONVERTORS_HOLDER) ConvertFunctionsHolder functionsHolder) {
        return OLSoapRpcBeanUtils.inputBinder(rpcBindCallback, rpcFieldValidation, functionsHolder);
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_INPUT_BINDER_CALLBACK_SUFFIX)
    public SoapInputBinderCallback inputBindCallback(@Qualifier(ORCHESTRATED_KEY + RpcBeanNames.RPC_PROVIDER_PRIMITIVES) ConnectorPrimitives connectorPrimitives) {
        return OLSoapRpcBeanUtils.inputBindCallback(connectorPrimitives);
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.TRANSPORTER_FACTORY_SUFFIX)
    public SoapTransporterFactory transporterFactory(@Qualifier(ORCHESTRATED_KEY + RpcBeanNames.URL_PROPS_SUFFIX) OLSoapProjectProperties properties) {
        return OLSoapRpcBeanUtils.transporterFactory(properties);
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_OUTPUT_FORMATTER_SUFFIX)
    public SoapOutputFormatter outputFormatter(
            @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.URL_PROPS_SUFFIX) OLSoapProjectProperties properties,
            @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.RPC_OUTPUT_BINDER_SUFFIX) SoapDirectOutputBinder outputBinder) {
        return OLSoapRpcBeanUtils.outputFormatter(properties, outputBinder);
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_OUTPUT_BINDER_SUFFIX)
    public SoapDirectOutputBinder outputBinder(
            @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.RPC_OUTPUT_BINDER_CALLBACK_SUFFIX) SoapOutputBinderCallback outputBindCallback,
            @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.RPC_ENTITY_FIELDS_VALIDATIONS) RpcFieldValidation rpcFieldValidation,
            @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.OUTPUT_CONVERTORS_HOLDER) ConvertFunctionsHolder functionsHolder) {
        return OLSoapRpcBeanUtils.outputBinder(outputBindCallback, rpcFieldValidation, functionsHolder);
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_OUTPUT_BINDER_CALLBACK_SUFFIX)
    public SoapOutputBinderCallback outputBindCallback(@Qualifier(ORCHESTRATED_KEY + RpcBeanNames.RPC_PROVIDER_PRIMITIVES) ConnectorPrimitives connectorPrimitives) {
        return OLSoapRpcBeanUtils.outputBindCallback(connectorPrimitives);
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.INPUT_CONVERTORS_HOLDER)
    public ConvertFunctionsHolder inputConvertHolder() {
        return OLSoapRpcBeanUtils.inputConvertFunctionsHolder(null);
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.OUTPUT_CONVERTORS_HOLDER)
    public ConvertFunctionsHolder outputConvertHolder() {
        return OLSoapRpcBeanUtils.outputConvertFunctionsHolder(null);
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_ENTITY_FIELDS_VALIDATIONS)
    public RpcFieldValidation defaultRpcFieldValidation(List<RpcFieldTypeValidation> rpcFieldTypeValidations, OLRpcFieldValidationProperties olFieldValidationProperties) {
        return OLSoapRpcBeanUtils.defaultRpcFieldValidation(ORCHESTRATED_KEY, rpcFieldTypeValidations, olFieldValidationProperties);
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_SESSION_TRAIL)
    public RpcSessionTrail rpcSessionTrail(OLCommonProperties commonProperties,
            @Qualifier((ORCHESTRATED_KEY + RpcBeanNames.RPC_MOCK_CONNECTION_FACTORY_SUFFIX)) MockRpcConnectionFactory mock) {
        return OLSoapRpcBeanUtils.rpcSessionTrail(ORCHESTRATED_KEY, commonProperties, mock.getEntitiesSnapshots());
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_TRAIL_MODULE)
    public SessionModule rpcTrailModule(@Qualifier(ORCHESTRATED_KEY + RpcBeanNames.RPC_SESSION_TRAIL) RpcSessionTrail sessionTrail,
             RpcTrailWriter rpcTrailUtil) {
        return OLSoapRpcBeanUtils.rpcTrailModule(sessionTrail, rpcTrailUtil);
    }
}

