package com.mainframe_mq.openlegacy.config;

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
import org.openlegacy.providers.mq.utils.OLRpcMqBeanUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.Configuration;
import org.openlegacy.providers.mq.properties.OLMqProperties;
import org.openlegacy.impl.properties.OLRpcFieldValidationProperties;
import org.openlegacy.impl.rpc.support.binders.converters.ConvertFunctionsHolder;
import org.openlegacy.impl.validator.RpcFieldValidation;
import org.openlegacy.impl.validator.RpcFieldTypeValidation;
import org.openlegacy.impl.rpc.definitions.ConnectorPrimitives;
import org.openlegacy.core.rpc.modules.trail.RpcSessionTrail;
import org.openlegacy.impl.modules.trail.RpcTrailWriter;
import org.openlegacy.impl.properties.OLCommonProperties;

import org.openlegacy.providers.mq.MqBeanNames;
import org.openlegacy.providers.mq.properties.OLMqProperties.ProjectMqProperties;
import org.openlegacy.providers.mq.transporter.async.MqAsyncTransporter;
import org.openlegacy.providers.mq.transporter.async.MqResponseProvider;
import org.openlegacy.providers.mq.utils.MqHeaderUtils;
import org.openlegacy.providers.mq.utils.DefaultMqHeaderUtils;
import org.openlegacy.core.rpc.definitions.RpcInputFormatter;
import org.openlegacy.core.rpc.definitions.RpcOutputFormatter;
import org.openlegacy.core.rpc.definitions.RpcTransporterFactory;
import org.openlegacy.impl.rpc.support.binders.RpcDirectInputBinder;
import org.openlegacy.impl.rpc.support.binders.RpcDirectOutputBinder;
import org.openlegacy.impl.rpc.support.binders.RpcBindCallback;
import org.openlegacy.impl.rpc.support.binders.RpcDirectBinder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import org.openlegacy.impl.properties.OLDebugProperties;
import org.openlegacy.utils.mf.utils.MFConverterUtil;
import java.util.List;
import java.util.Arrays;

/**
 * RPC Configuration
 */
@Configuration
public class MainframeMqConfiguration {
    private static final String ORCHESTRATED_KEY = "mainframeMq";
    private static final String[] packagesToScan = new String[] {"com.mainframe_mq.openlegacy"};

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_SESSION_SUFFIX)
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RpcSession rpcSession(
                @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.RPC_CONNECTION_SUFFIX) RpcConnection rpcConnection,
                @Qualifier(ORCHESTRATED_KEY + CommonBeanNames.SESSION_MODULES_SUFFIX) SessionModules sessionModules) {
        return OLRpcMqBeanUtils.rpcSession(rpcConnection, sessionModules);
    }

    @Bean(ORCHESTRATED_KEY + CommonBeanNames.SESSION_MODULES_SUFFIX)
    public SessionModules sessionModules(
                @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.RPC_TRAIL_MODULE) SessionModule rpcTrailModule,
                @Qualifier(ORCHESTRATED_KEY + CommonBeanNames.SESSION_REGISTRY_MODULE_SUFFIX) SessionModule sessionRegistryModule,
                @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.RPC_MENU_MODULE_SUFFIX) SessionModule rpcMenuModule,
                @Qualifier(RpcBeanNames.RPC_LOGIN_MODULE) SessionModule rpcLoginModule,
                @Qualifier(RpcBeanNames.RPC_TRANSACTION_MODULE) SessionModule rpcTransactionModule) {
        return OLRpcMqBeanUtils.sessionModules(Arrays.asList(
            rpcTrailModule,
            sessionRegistryModule,
            rpcMenuModule,
            rpcLoginModule,
            rpcTransactionModule));
    }

    @Bean(ORCHESTRATED_KEY + CommonBeanNames.MENU_BUILDER_SUFFIX)
    public MenuBuilder menuBuilder() {
        return OLRpcMqBeanUtils.menuBuilder(ORCHESTRATED_KEY);
    }

    @Bean(ORCHESTRATED_KEY + CommonBeanNames.SESSION_REGISTRY_MODULE_SUFFIX)
    public SessionModule sessionRegistryModule() {
        return OLRpcMqBeanUtils.sessionRegistryModule(ORCHESTRATED_KEY);
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_MENU_MODULE_SUFFIX)
    public SessionModule rpcMenuModule() {
        return OLRpcMqBeanUtils.rpcMenuModule(ORCHESTRATED_KEY);
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_ENTITIES_REGISTRY_SUFFIX)
    public EntitiesRegistry<?, ?, ?> rpcRegistry(@Qualifier(RpcBeanNames.RPC_REGISTRY_LOADER) RegistryLoader registryLoader) {
        return OLRpcMqBeanUtils.rpcRegistry(Arrays.asList(packagesToScan), registryLoader);
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_PROVIDER_PRIMITIVES)
    public ConnectorPrimitives connectorPrimitives() {
         return OLRpcMqBeanUtils.rpcConnectorPrimitives();
    }


    @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_MOCK_CONNECTION_FACTORY_SUFFIX)
    public MockRpcConnectionFactory mockRpcConnectionFactory() {
        return OLRpcMqBeanUtils.mockRpcConnectionFactory(ORCHESTRATED_KEY);
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_CONNECTION_SUFFIX)
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RpcConnection rpcConnection() {
        return OLRpcMqBeanUtils.rpcConnection(ORCHESTRATED_KEY);
    }

    @Bean(ORCHESTRATED_KEY + MqBeanNames.HEADER_UTIL_SUFFIX)
    public MqHeaderUtils headerUtils() {
        return new DefaultMqHeaderUtils();
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.PROJECT_PROPERTIES)
    public ProjectMqProperties projectMqProperties(OLMqProperties properties) {
        return OLRpcMqBeanUtils.getProjectProperties(properties, ORCHESTRATED_KEY);
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_CONNECTION_FACTORY_SUFFIX)
    public RpcConnectionFactory connectionFactory(
            @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.RPC_INPUT_FORMATTER_SUFFIX) RpcInputFormatter<byte[]> inputFormatter,
            @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.TRANSPORTER_FACTORY_SUFFIX) RpcTransporterFactory<MqAsyncTransporter> transporterFactory,
            @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.RPC_OUTPUT_FORMATTER_SUFFIX) RpcOutputFormatter<byte[]> outputFormatter) {
        return OLRpcMqBeanUtils.rpcConnectionFactory(inputFormatter, transporterFactory, outputFormatter);
    }

    @Bean(ORCHESTRATED_KEY + MqBeanNames.TRANSPORTER_FACTORY_SUFFIX)
    public RpcTransporterFactory<MqAsyncTransporter> mqTransporterFactory(
            @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.PROJECT_PROPERTIES) ProjectMqProperties projectMqProperties,
            OLDebugProperties debugProperties,
            @Qualifier(ORCHESTRATED_KEY + MqBeanNames.RESPONSE_PROVIDER_SUFFIX) MqResponseProvider jmsResponseProvider,
            MqHeaderUtils headerUtils) {
        return OLRpcMqBeanUtils.rpcTransporterFactory(
            projectMqProperties,
            debugProperties,
            jmsResponseProvider,
            headerUtils
        );
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_INPUT_FORMATTER_SUFFIX)
    public RpcInputFormatter<byte[]> rpcInputFormatter(
            @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.PROJECT_PROPERTIES) ProjectMqProperties projectMqProperties,
            @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.RPC_INPUT_BINDER_SUFFIX) RpcDirectInputBinder<ByteArrayOutputStream> rpcDirectInputBinder) {
        return OLRpcMqBeanUtils.rpcInputFormatter(projectMqProperties, rpcDirectInputBinder);
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_OUTPUT_FORMATTER_SUFFIX)
    public static RpcOutputFormatter<byte[]> rpcOutputFormatter(
            @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.RPC_OUTPUT_BINDER_SUFFIX) RpcDirectOutputBinder<ByteArrayInputStream> rpcDirectOutputBinder) {
        return OLRpcMqBeanUtils.rpcOutputFormatter(rpcDirectOutputBinder);
    }

        @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_INPUT_BINDER_SUFFIX)
    public RpcDirectInputBinder<ByteArrayOutputStream> rpcDirectInputBinder(
            @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.RPC_INPUT_BINDER_CALLBACK_SUFFIX) RpcBindCallback<ByteArrayOutputStream> rpcBindCallback,
            @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.RPC_ENTITY_FIELDS_VALIDATIONS) RpcFieldValidation rpcFieldValidation,
            @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.INPUT_CONVERTORS_HOLDER) ConvertFunctionsHolder functionHolder) {
        return OLRpcMqBeanUtils.rpcDirectInputBinder(rpcBindCallback, rpcFieldValidation, functionHolder);
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_OUTPUT_BINDER_SUFFIX)
    public RpcDirectOutputBinder<ByteArrayInputStream> rpcDirectOutputBinder(
            @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.RPC_OUTPUT_BINDER_CALLBACK_SUFFIX) RpcBindCallback<ByteArrayInputStream> rpcBindCallback,
            @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.RPC_ENTITY_FIELDS_VALIDATIONS) RpcFieldValidation rpcFieldValidation,
            @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.OUTPUT_CONVERTORS_HOLDER) ConvertFunctionsHolder functionHolder) {
        return OLRpcMqBeanUtils.rpcDirectOutputBinder(rpcBindCallback, rpcFieldValidation, functionHolder);
    }

        @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_INPUT_BINDER_CALLBACK_SUFFIX)
    public RpcBindCallback<ByteArrayOutputStream> rpcBindInputCallback(
            @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.RPC_PROVIDER_PRIMITIVES) ConnectorPrimitives connectorPrimitives,
            @Qualifier(ORCHESTRATED_KEY + MqBeanNames.INPUT_SUFFIX + MqBeanNames.MF_CONVERTER_UTIL_SUFFIX) MFConverterUtil convertUtil) {
        return OLRpcMqBeanUtils.rpcBindInputCallback(connectorPrimitives, convertUtil);
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_OUTPUT_BINDER_CALLBACK_SUFFIX)
    public RpcBindCallback<ByteArrayInputStream> rpcBindOutputCallback(
            @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.RPC_PROVIDER_PRIMITIVES) ConnectorPrimitives connectorPrimitives,
            @Qualifier(ORCHESTRATED_KEY + MqBeanNames.OUTPUT_SUFFIX + MqBeanNames.MF_CONVERTER_UTIL_SUFFIX) MFConverterUtil convertUtil) {
        return OLRpcMqBeanUtils.rpcBindOutputCallback(connectorPrimitives, convertUtil);
    }

    @Bean(ORCHESTRATED_KEY + MqBeanNames.INPUT_SUFFIX + MqBeanNames.MF_CONVERTER_UTIL_SUFFIX)
    public MFConverterUtil inputConvertUtil(
    		@Qualifier(ORCHESTRATED_KEY + RpcBeanNames.PROJECT_PROPERTIES) ProjectMqProperties projectMqProperties) {
        return OLRpcMqBeanUtils.mfConverterUtil(projectMqProperties);
    }

    @Bean(ORCHESTRATED_KEY + MqBeanNames.OUTPUT_SUFFIX + MqBeanNames.MF_CONVERTER_UTIL_SUFFIX)
    public MFConverterUtil outputConvertUtil(
    		@Qualifier(ORCHESTRATED_KEY + RpcBeanNames.PROJECT_PROPERTIES) ProjectMqProperties projectMqProperties) {
        return OLRpcMqBeanUtils.mfConverterUtil(projectMqProperties);
    }


    @Bean(ORCHESTRATED_KEY + MqBeanNames.RESPONSE_PROVIDER_SUFFIX)
    public MqResponseProvider mqResponseProvider() {
        return OLRpcMqBeanUtils.mqResponseProvider();
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.INPUT_CONVERTORS_HOLDER)
    public ConvertFunctionsHolder inputConvertHolder() {
        return OLRpcMqBeanUtils.inputConvertFunctionsHolder(null);
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.OUTPUT_CONVERTORS_HOLDER)
    public ConvertFunctionsHolder outputConvertHolder() {
        return OLRpcMqBeanUtils.outputConvertFunctionsHolder(null);
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_ENTITY_FIELDS_VALIDATIONS)
    public RpcFieldValidation defaultRpcFieldValidation(List<RpcFieldTypeValidation> rpcFieldTypeValidations, OLRpcFieldValidationProperties olFieldValidationProperties) {
        return OLRpcMqBeanUtils.defaultRpcFieldValidation(ORCHESTRATED_KEY, rpcFieldTypeValidations, olFieldValidationProperties);
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_SESSION_TRAIL)
    public RpcSessionTrail rpcSessionTrail(OLCommonProperties commonProperties,
            @Qualifier((ORCHESTRATED_KEY + RpcBeanNames.RPC_MOCK_CONNECTION_FACTORY_SUFFIX)) MockRpcConnectionFactory mock) {
        return OLRpcMqBeanUtils.rpcSessionTrail(ORCHESTRATED_KEY, commonProperties, mock.getEntitiesSnapshots());
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_TRAIL_MODULE)
    public SessionModule rpcTrailModule(@Qualifier(ORCHESTRATED_KEY + RpcBeanNames.RPC_SESSION_TRAIL) RpcSessionTrail sessionTrail,
             RpcTrailWriter rpcTrailUtil) {
        return OLRpcMqBeanUtils.rpcTrailModule(sessionTrail, rpcTrailUtil);
    }
}

