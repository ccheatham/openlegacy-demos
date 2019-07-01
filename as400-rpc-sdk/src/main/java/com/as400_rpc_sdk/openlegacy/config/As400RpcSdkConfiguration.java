package com.as400_rpc_sdk.openlegacy.config;

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
import org.openlegacy.providers.jt400.utils.OLJt400BeanUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.Configuration;
import org.openlegacy.providers.jt400.properties.OLJt400Properties;
import org.openlegacy.impl.properties.OLRpcFieldValidationProperties;
import org.openlegacy.impl.rpc.support.binders.converters.ConvertFunctionsHolder;
import org.openlegacy.impl.validator.RpcFieldValidation;
import org.openlegacy.impl.validator.RpcFieldTypeValidation;
import org.openlegacy.impl.rpc.definitions.ConnectorPrimitives;
import org.openlegacy.core.rpc.modules.trail.RpcSessionTrail;
import org.openlegacy.impl.modules.trail.RpcTrailWriter;
import org.openlegacy.impl.properties.OLCommonProperties;

import org.openlegacy.impl.properties.OLDebugProperties;
import com.ibm.as400.access.ProgramParameter;
import org.openlegacy.providers.jt400.utils.Jt400ConvertUtil;
import org.openlegacy.impl.rpc.support.binders.RpcBindCallback;
import org.openlegacy.impl.rpc.support.binders.RpcDirectInputBinder;
import org.openlegacy.impl.rpc.support.binders.RpcDirectOutputBinder;
import org.openlegacy.core.rpc.definitions.RpcInputFormatter;
import org.openlegacy.core.rpc.definitions.RpcOutputFormatter;
import org.openlegacy.providers.jt400.transporter.Jt400TransporterFactory;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import org.openlegacy.impl.rpc.utils.dynamic.SizeUtil.RpcSizeScanner;
import java.util.List;
import java.util.Arrays;

/**
 * RPC Configuration
 */
@Configuration
public class As400RpcSdkConfiguration {
    private static final String ORCHESTRATED_KEY = "as400RpcSdk";
    private static final String[] packagesToScan = new String[] {"com.as400_rpc_sdk.openlegacy"};

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_SESSION_SUFFIX)
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RpcSession rpcSession(
                @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.RPC_CONNECTION_SUFFIX) RpcConnection rpcConnection,
                @Qualifier(ORCHESTRATED_KEY + CommonBeanNames.SESSION_MODULES_SUFFIX) SessionModules sessionModules) {
        return OLJt400BeanUtils.rpcSession(rpcConnection, sessionModules);
    }

    @Bean(ORCHESTRATED_KEY + CommonBeanNames.SESSION_MODULES_SUFFIX)
    public SessionModules sessionModules(
                @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.RPC_TRAIL_MODULE) SessionModule rpcTrailModule,
                @Qualifier(ORCHESTRATED_KEY + CommonBeanNames.SESSION_REGISTRY_MODULE_SUFFIX) SessionModule sessionRegistryModule,
                @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.RPC_MENU_MODULE_SUFFIX) SessionModule rpcMenuModule,
                @Qualifier(RpcBeanNames.RPC_LOGIN_MODULE) SessionModule rpcLoginModule,
                @Qualifier(RpcBeanNames.RPC_TRANSACTION_MODULE) SessionModule rpcTransactionModule) {
        return OLJt400BeanUtils.sessionModules(Arrays.asList(
            rpcTrailModule,
            sessionRegistryModule,
            rpcMenuModule,
            rpcLoginModule,
            rpcTransactionModule));
    }

    @Bean(ORCHESTRATED_KEY + CommonBeanNames.MENU_BUILDER_SUFFIX)
    public MenuBuilder menuBuilder() {
        return OLJt400BeanUtils.menuBuilder(ORCHESTRATED_KEY);
    }

    @Bean(ORCHESTRATED_KEY + CommonBeanNames.SESSION_REGISTRY_MODULE_SUFFIX)
    public SessionModule sessionRegistryModule() {
        return OLJt400BeanUtils.sessionRegistryModule(ORCHESTRATED_KEY);
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_MENU_MODULE_SUFFIX)
    public SessionModule rpcMenuModule() {
        return OLJt400BeanUtils.rpcMenuModule(ORCHESTRATED_KEY);
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_ENTITIES_REGISTRY_SUFFIX)
    public EntitiesRegistry<?, ?, ?> rpcRegistry(@Qualifier(RpcBeanNames.RPC_REGISTRY_LOADER) RegistryLoader registryLoader) {
        return OLJt400BeanUtils.rpcRegistry(Arrays.asList(packagesToScan), registryLoader);
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_PROVIDER_PRIMITIVES)
    public ConnectorPrimitives connectorPrimitives() {
         return OLJt400BeanUtils.rpcConnectorPrimitives();
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_CONNECTION_FACTORY_SUFFIX)
    public RpcConnectionFactory rpcConnectionFactory(
            OLJt400Properties olJt400Properties,
            OLDebugProperties olDebugProperties,
            @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.RPC_INPUT_FORMATTER_SUFFIX) RpcInputFormatter<List<ProgramParameter>> rpcInputFormatter,
            @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.RPC_OUTPUT_FORMATTER_SUFFIX) RpcOutputFormatter<List<ProgramParameter>> rpcOutputFormatter,
            @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.TRANSPORTER_FACTORY_SUFFIX) Jt400TransporterFactory rpcTransporterFactory) {
        return OLJt400BeanUtils.rpcConnectionFactory(ORCHESTRATED_KEY, olJt400Properties, olDebugProperties, rpcInputFormatter, rpcOutputFormatter, rpcTransporterFactory);
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_MOCK_CONNECTION_FACTORY_SUFFIX)
    public MockRpcConnectionFactory mockRpcConnectionFactory() {
        return OLJt400BeanUtils.mockRpcConnectionFactory(ORCHESTRATED_KEY);
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_CONNECTION_SUFFIX)
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RpcConnection rpcConnection() {
        return OLJt400BeanUtils.rpcConnection(ORCHESTRATED_KEY);
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_INPUT_FORMATTER_SUFFIX)
     public RpcInputFormatter<List<ProgramParameter>> rpcInputFormatter(
            @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.RPC_INPUT_BINDER_SUFFIX) RpcDirectInputBinder<ByteArrayOutputStream> rpcDirectInputBinder,
            RpcSizeScanner rpcSizeScanner) {
        return OLJt400BeanUtils.rpcInputFormatter(rpcDirectInputBinder, rpcSizeScanner);
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_OUTPUT_FORMATTER_SUFFIX)
    public static RpcOutputFormatter<List<ProgramParameter>> rpcOutputFormatter(
            @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.RPC_OUTPUT_BINDER_SUFFIX) RpcDirectOutputBinder<ByteArrayInputStream> rpcDirectOutputBinder) {
        return OLJt400BeanUtils.rpcOutputFormatter(rpcDirectOutputBinder);
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.TRANSPORTER_FACTORY_SUFFIX)
    public static Jt400TransporterFactory rpcTransporterFactory(OLJt400Properties olJt400Properties) {
        return OLJt400BeanUtils.rpcTransporterFactory(ORCHESTRATED_KEY, olJt400Properties);
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_INPUT_BINDER_SUFFIX)
    public static RpcDirectInputBinder<ByteArrayOutputStream> rpcDirectInputBinder(
            @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.RPC_INPUT_BINDER_CALLBACK_SUFFIX) RpcBindCallback<ByteArrayOutputStream> rpcBindCallback,
            @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.RPC_ENTITY_FIELDS_VALIDATIONS) RpcFieldValidation rpcFieldValidation,
            @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.INPUT_CONVERTORS_HOLDER) ConvertFunctionsHolder functionHolder) {
        return OLJt400BeanUtils.rpcDirectInputBinder(rpcBindCallback, rpcFieldValidation, functionHolder);
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_OUTPUT_BINDER_SUFFIX)
    public RpcDirectOutputBinder<ByteArrayInputStream> rpcDirectOutputBinder(
            @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.RPC_OUTPUT_BINDER_CALLBACK_SUFFIX) RpcBindCallback<ByteArrayInputStream> rpcBindCallback,
            @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.RPC_ENTITY_FIELDS_VALIDATIONS) RpcFieldValidation rpcFieldValidation,
            @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.OUTPUT_CONVERTORS_HOLDER) ConvertFunctionsHolder functionHolder) {
        return OLJt400BeanUtils.rpcDirectOutputBinder(rpcBindCallback, rpcFieldValidation, functionHolder);
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_INPUT_BINDER_CALLBACK_SUFFIX)
    public RpcBindCallback<ByteArrayOutputStream> rpcBindInputCallback(
            @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.RPC_PROVIDER_PRIMITIVES) ConnectorPrimitives connectorPrimitives,
            @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.AS400_CONVERTER_UTIL_SUFFIX) Jt400ConvertUtil jt400ConvertUtil) {
        return OLJt400BeanUtils.rpcBindInputCallback(connectorPrimitives, jt400ConvertUtil);
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_OUTPUT_BINDER_CALLBACK_SUFFIX)
    public RpcBindCallback<ByteArrayInputStream> rpcBindOutputCallback(
            @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.RPC_PROVIDER_PRIMITIVES) ConnectorPrimitives connectorPrimitives,
            @Qualifier(ORCHESTRATED_KEY + RpcBeanNames.AS400_CONVERTER_UTIL_SUFFIX) Jt400ConvertUtil jt400ConvertUtil) {
        return OLJt400BeanUtils.rpcBindOutputCallback(connectorPrimitives, jt400ConvertUtil);
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.AS400_CONVERTER_UTIL_SUFFIX)
    public Jt400ConvertUtil jt400ConverUtil(OLJt400Properties olJt400Properties) {
        return OLJt400BeanUtils.jt400ConvertUtil(ORCHESTRATED_KEY, olJt400Properties);
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.INPUT_CONVERTORS_HOLDER)
    public ConvertFunctionsHolder inputConvertHolder() {
        return OLJt400BeanUtils.inputConvertFunctionsHolder(null);
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.OUTPUT_CONVERTORS_HOLDER)
    public ConvertFunctionsHolder outputConvertHolder() {
        return OLJt400BeanUtils.outputConvertFunctionsHolder(null);
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_ENTITY_FIELDS_VALIDATIONS)
    public RpcFieldValidation defaultRpcFieldValidation(List<RpcFieldTypeValidation> rpcFieldTypeValidations, OLRpcFieldValidationProperties olFieldValidationProperties) {
        return OLJt400BeanUtils.defaultRpcFieldValidation(ORCHESTRATED_KEY, rpcFieldTypeValidations, olFieldValidationProperties);
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_SESSION_TRAIL)
    public RpcSessionTrail rpcSessionTrail(OLCommonProperties commonProperties,
            @Qualifier((ORCHESTRATED_KEY + RpcBeanNames.RPC_MOCK_CONNECTION_FACTORY_SUFFIX)) MockRpcConnectionFactory mock) {
        return OLJt400BeanUtils.rpcSessionTrail(ORCHESTRATED_KEY, commonProperties, mock.getEntitiesSnapshots());
    }

    @Bean(ORCHESTRATED_KEY + RpcBeanNames.RPC_TRAIL_MODULE)
    public SessionModule rpcTrailModule(@Qualifier(ORCHESTRATED_KEY + RpcBeanNames.RPC_SESSION_TRAIL) RpcSessionTrail sessionTrail,
             RpcTrailWriter rpcTrailUtil) {
        return OLJt400BeanUtils.rpcTrailModule(sessionTrail, rpcTrailUtil);
    }
}

