package com.mysql_sp_sdk.openlegacy.config;

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
import org.openlegacy.providers.storedproc.utils.OLStorProcRpcBeanUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.Configuration;
import org.openlegacy.impl.properties.OLDatabaseProperties;
import org.openlegacy.impl.properties.OLRpcFieldValidationProperties;
import org.openlegacy.impl.rpc.support.binders.converters.ConvertFunctionsHolder;
import org.openlegacy.impl.validator.RpcFieldValidation;
import org.openlegacy.impl.validator.RpcFieldTypeValidation;
import org.openlegacy.impl.rpc.definitions.ConnectorPrimitives;
import org.openlegacy.core.rpc.modules.trail.RpcSessionTrail;
import org.openlegacy.impl.modules.trail.RpcTrailWriter;
import org.openlegacy.impl.properties.OLCommonProperties;

import org.openlegacy.core.rpc.*;
import org.openlegacy.core.rpc.definitions.*;
import org.openlegacy.impl.rpc.support.binders.*;
import org.openlegacy.providers.storedproc.CallableStatementWrapper;
import java.util.List;
import java.util.Arrays;

/**
 * RPC Configuration
 */
@Configuration
public class MysqlSpSdkConfiguration {
    private static final String ORCHESTRATED_KEY = "mysqlSpSdk";
    private static final String[] packagesToScan = new String[] {"com.mysql_sp_sdk.openlegacy"};

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_SESSION_SUFFIX)
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RpcSession rpcSession(
                @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.RPC_CONNECTION_SUFFIX) RpcConnection rpcConnection,
                @Qualifier(ORCHESTRATED_KEY + CommonBeanNames.SESSION_MODULES_SUFFIX) SessionModules sessionModules) {
        return OLStorProcRpcBeanUtils.rpcSession(rpcConnection, sessionModules);
    }

    @Bean(ORCHESTRATED_KEY + CommonBeanNames.SESSION_MODULES_SUFFIX)
    public SessionModules sessionModules(
                @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.RPC_TRAIL_MODULE) SessionModule rpcTrailModule,
                @Qualifier(ORCHESTRATED_KEY + CommonBeanNames.SESSION_REGISTRY_MODULE_SUFFIX) SessionModule sessionRegistryModule,
                @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.RPC_MENU_MODULE_SUFFIX) SessionModule rpcMenuModule,
                @Qualifier(RpcBeanNames.RPC_LOGIN_MODULE) SessionModule rpcLoginModule,
                @Qualifier(RpcBeanNames.RPC_TRANSACTION_MODULE) SessionModule rpcTransactionModule) {
        return OLStorProcRpcBeanUtils.sessionModules(Arrays.asList(
            rpcTrailModule,
            sessionRegistryModule,
            rpcMenuModule,
            rpcLoginModule,
            rpcTransactionModule));
    }

    @Bean(ORCHESTRATED_KEY + CommonBeanNames.MENU_BUILDER_SUFFIX)
    public MenuBuilder menuBuilder() {
        return OLStorProcRpcBeanUtils.menuBuilder(ORCHESTRATED_KEY);
    }

    @Bean(ORCHESTRATED_KEY + CommonBeanNames.SESSION_REGISTRY_MODULE_SUFFIX)
    public SessionModule sessionRegistryModule() {
        return OLStorProcRpcBeanUtils.sessionRegistryModule(ORCHESTRATED_KEY);
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_MENU_MODULE_SUFFIX)
    public SessionModule rpcMenuModule() {
        return OLStorProcRpcBeanUtils.rpcMenuModule(ORCHESTRATED_KEY);
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_ENTITIES_REGISTRY_SUFFIX)
    public EntitiesRegistry<?, ?, ?> rpcRegistry(@Qualifier(RpcBeanNames.RPC_REGISTRY_LOADER) RegistryLoader registryLoader) {
        return OLStorProcRpcBeanUtils.rpcRegistry(Arrays.asList(packagesToScan), registryLoader);
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_PROVIDER_PRIMITIVES)
    public ConnectorPrimitives connectorPrimitives() {
         return OLStorProcRpcBeanUtils.rpcConnectorPrimitives();
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_CONNECTION_FACTORY_SUFFIX)
    public RpcConnectionFactory rpcConnectionFactory(
            @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.STORED_PROC_CALL_BUILDER_FORMATTER) RpcInputFormatter<StringBuilder> callBuilderFormatter,
            @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.RPC_INPUT_FORMATTER_SUFFIX) RpcInputFormatter<CallableStatementWrapper> inputFormatter,
            @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.RPC_OUTPUT_FORMATTER_SUFFIX) RpcOutputFormatter<CallableStatementWrapper> outputFormatter,
            @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.TRANSPORTER_FACTORY_SUFFIX) RpcTransporterFactory<RpcTransporter<CallableStatementWrapper, String>> transporterFactory) {
        return OLStorProcRpcBeanUtils.rpcConnectionFactory(callBuilderFormatter, inputFormatter, outputFormatter, transporterFactory);
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_MOCK_CONNECTION_FACTORY_SUFFIX)
    public MockRpcConnectionFactory mockRpcConnectionFactory() {
        return OLStorProcRpcBeanUtils.mockRpcConnectionFactory(ORCHESTRATED_KEY);
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_CONNECTION_SUFFIX)
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RpcConnection rpcConnection() {
        return OLStorProcRpcBeanUtils.rpcConnection(ORCHESTRATED_KEY);
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.TRANSPORTER_FACTORY_SUFFIX)
    public RpcTransporterFactory<RpcTransporter<CallableStatementWrapper, String>> transporterFactory(OLDatabaseProperties properties) {
        return OLStorProcRpcBeanUtils.transporterFactory(ORCHESTRATED_KEY, properties);
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.STORED_PROC_CALL_BUILDER_CALLBACK)
    public RpcBindCallback<StringBuilder> storedProcCallBuilderCallback(
            @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.RPC_PROVIDER_PRIMITIVES) ConnectorPrimitives primitives) {
        return OLStorProcRpcBeanUtils.callBuilderCallback(primitives);
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.STORED_PROC_CALL_BUILDER)
    public RpcDirectInputBinder<StringBuilder> storedProcCallBuilder(
            @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.STORED_PROC_CALL_BUILDER_CALLBACK) RpcBindCallback<StringBuilder> builderCallback,
            @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.RPC_ENTITY_FIELDS_VALIDATIONS) RpcFieldValidation rpcFieldValidation,
            @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.INPUT_CONVERTORS_HOLDER) ConvertFunctionsHolder functionHolder) {
        return OLStorProcRpcBeanUtils.callBuilder(builderCallback, rpcFieldValidation, functionHolder);
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.STORED_PROC_CALL_BUILDER_FORMATTER)
    public RpcInputFormatter<StringBuilder> storedProcCallBuilderFormatter(
            @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.STORED_PROC_CALL_BUILDER) RpcDirectInputBinder<StringBuilder> builder) {
        return OLStorProcRpcBeanUtils.callBuilderFormatter(builder);
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_INPUT_BINDER_CALLBACK_SUFFIX)
    public RpcBindCallback<CallableStatementWrapper> storedProcInputBinderCallback(
            @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.RPC_PROVIDER_PRIMITIVES) ConnectorPrimitives primitives) {
        return OLStorProcRpcBeanUtils.inputBindCallback(primitives);
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_INPUT_BINDER_SUFFIX)
    public RpcDirectInputBinder<CallableStatementWrapper> storedProcInputBinder(
            @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.RPC_INPUT_BINDER_CALLBACK_SUFFIX) RpcBindCallback<CallableStatementWrapper> binderCallback,
            @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.RPC_ENTITY_FIELDS_VALIDATIONS) RpcFieldValidation rpcFieldValidation,
            @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.INPUT_CONVERTORS_HOLDER) ConvertFunctionsHolder functionHolder) {
        return OLStorProcRpcBeanUtils.inputBinder(binderCallback, rpcFieldValidation, functionHolder);
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_OUTPUT_BINDER_CALLBACK_SUFFIX)
    public RpcBindCallback<CallableStatementWrapper> storedProcOutputBinderCallback(
            @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.RPC_PROVIDER_PRIMITIVES) ConnectorPrimitives primitives) {
        return OLStorProcRpcBeanUtils.outputBindCallback(primitives);
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_OUTPUT_BINDER_SUFFIX)
    public RpcDirectOutputBinder<CallableStatementWrapper> storedProcOutputBinder(
            @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.RPC_OUTPUT_BINDER_CALLBACK_SUFFIX) RpcBindCallback<CallableStatementWrapper> binderCallback,
            @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.RPC_ENTITY_FIELDS_VALIDATIONS) RpcFieldValidation rpcFieldValidation,
            @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.OUTPUT_CONVERTORS_HOLDER) ConvertFunctionsHolder functionHolder) {
        return OLStorProcRpcBeanUtils.outputBinder(binderCallback, rpcFieldValidation, functionHolder);
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_INPUT_FORMATTER_SUFFIX)
    public RpcInputFormatter<CallableStatementWrapper> storedProcInputFormatter(
            @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.RPC_INPUT_BINDER_SUFFIX) RpcDirectInputBinder<CallableStatementWrapper> storedProcInputBinder) {
        return OLStorProcRpcBeanUtils.inputFormatter(storedProcInputBinder);
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_OUTPUT_FORMATTER_SUFFIX)
    public RpcOutputFormatter<CallableStatementWrapper> storedProcOutputFormatter(
            @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.RPC_OUTPUT_BINDER_SUFFIX) RpcDirectOutputBinder<CallableStatementWrapper> storedProcOutputBinder) {
        return OLStorProcRpcBeanUtils.outputFormatter(storedProcOutputBinder);
    }


    @Bean(ORCHESTRATED_KEY + RpcBeanNames.INPUT_CONVERTORS_HOLDER)
    public ConvertFunctionsHolder inputConvertHolder() {
        return OLStorProcRpcBeanUtils.inputConvertFunctionsHolder(null);
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.OUTPUT_CONVERTORS_HOLDER)
    public ConvertFunctionsHolder outputConvertHolder() {
        return OLStorProcRpcBeanUtils.outputConvertFunctionsHolder(null);
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_ENTITY_FIELDS_VALIDATIONS)
    public RpcFieldValidation defaultRpcFieldValidation(List<RpcFieldTypeValidation> rpcFieldTypeValidations, OLRpcFieldValidationProperties olFieldValidationProperties) {
        return OLStorProcRpcBeanUtils.defaultRpcFieldValidation(ORCHESTRATED_KEY, rpcFieldTypeValidations, olFieldValidationProperties);
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_SESSION_TRAIL)
    public RpcSessionTrail rpcSessionTrail(OLCommonProperties commonProperties,
            @Qualifier((ORCHESTRATED_KEY + RpcBeanNames.RPC_MOCK_CONNECTION_FACTORY_SUFFIX)) MockRpcConnectionFactory mock) {
        return OLStorProcRpcBeanUtils.rpcSessionTrail(ORCHESTRATED_KEY, commonProperties, mock.getEntitiesSnapshots());
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_TRAIL_MODULE)
    public SessionModule rpcTrailModule(@Qualifier(ORCHESTRATED_KEY + RpcBeanNames.RPC_SESSION_TRAIL) RpcSessionTrail sessionTrail,
             RpcTrailWriter rpcTrailUtil) {
        return OLStorProcRpcBeanUtils.rpcTrailModule(sessionTrail, rpcTrailUtil);
    }
}

