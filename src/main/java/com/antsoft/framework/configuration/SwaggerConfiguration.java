/*
 * Copyright (C) 2015 Ant, Inc. All Rights Reserved.
 */
package com.antsoft.framework.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.google.common.base.Optional;
import com.mangofactory.swagger.configuration.JacksonScalaSupport;
import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.configuration.SwaggerGlobalSettings;
import com.mangofactory.swagger.core.DefaultSwaggerPathProvider;
import com.mangofactory.swagger.core.ResourceGroupingStrategy;
import com.mangofactory.swagger.core.SwaggerApiResourceListing;
import com.mangofactory.swagger.core.SwaggerPathProvider;
import com.mangofactory.swagger.scanners.ApiListingReferenceScanner;
import com.mangofactory.swagger.scanners.ResourceGroup;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static com.google.common.base.Strings.emptyToNull;
import static com.google.common.base.Strings.isNullOrEmpty;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static com.mangofactory.swagger.core.StringUtils.maybeChompLeadingSlash;
import static com.mangofactory.swagger.core.StringUtils.splitCamelCase;

/**
 * Created by shiziye on 2014/6/7.
 */ // reference: http://raibledesigns.com/rd/entry/documenting_your_spring_api_with
@Configuration
@Profile("default")
@ComponentScan(basePackages = {"com.mangofactory.swagger.controllers", "com.mangofactory.swagger.configuration"})
class SwaggerConfiguration {

    public static final List<String> DEFAULT_INCLUDE_PATTERNS = Arrays.asList("/.*");
    public static final String SWAGGER_GROUP = "antlab-api";

    @Value("${app.docs}")
    private String docsLocation;

    @Autowired
    private SpringSwaggerConfig springSwaggerConfig;

    /**
     * Adds the jackson scala module to the MappingJackson2HttpMessageConverter registered with spring
     * Swagger core models are scala so we need to be able to convert to JSON
     * Also registers some custom serializers needed to transform swagger models to swagger-ui required json format
     */
    @Bean
    public JacksonScalaSupport jacksonScalaSupport() {
        JacksonScalaSupport jacksonScalaSupport = new JacksonScalaSupport();
        // Set to false to disable
        jacksonScalaSupport.setRegisterScalaModule(true);
        return jacksonScalaSupport;
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JodaModule());
        // This is the opportunity to override object mapper behavior
        return new ObjectMapper();
    }

    /**
     * Global swagger settings
     */
    @Bean
    public SwaggerGlobalSettings swaggerGlobalSettings() {
        SwaggerGlobalSettings swaggerGlobalSettings = new SwaggerGlobalSettings();
        // swaggerGlobalSettings.setGlobalResponseMessages(springSwaggerConfig.defaultResponseMessages());
        swaggerGlobalSettings.setIgnorableParameterTypes(springSwaggerConfig.defaultIgnorableParameterTypes());
        swaggerGlobalSettings.setAlternateTypeProvider(springSwaggerConfig.defaultAlternateTypeProvider());
        return swaggerGlobalSettings;
    }

    /**
     * API Info as it appears on the swagger-ui page
     */
    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo("AntLab API", "接口定义", null, null, null, null);
        return apiInfo;
    }

    /**
     * Configure a SwaggerApiResourceListing for each swagger instance within your app. e.g. 1. private  2. external
     * apis
     * Required to be a spring bean as spring will call the postConstruct method to bootstrap swagger scanning.
     *
     * @return
     */
    @Bean
    public SwaggerApiResourceListing swaggerApiResourceListing() {
        // The group name is important and should match the group set on ApiListingReferenceScanner
        // Note that swaggerCache() is by DefaultSwaggerController to serve the swagger json
        SwaggerApiResourceListing swaggerApiResourceListing =
                new SwaggerApiResourceListing(springSwaggerConfig.swaggerCache(), SWAGGER_GROUP);

        // Set the required swagger settings
        swaggerApiResourceListing.setSwaggerGlobalSettings(swaggerGlobalSettings());

        // Use a custom path provider or springSwaggerConfig.defaultSwaggerPathProvider()
        swaggerApiResourceListing.setSwaggerPathProvider(apiPathProvider());

        // Supply the API Info as it should appear on swagger-ui web page
        swaggerApiResourceListing.setApiInfo(apiInfo());

        // Global authorization - see the swagger documentation
        swaggerApiResourceListing.setAuthorizationTypes(authorizationTypes());

        // Every SwaggerApiResourceListing needs an ApiListingReferenceScanner to scan the spring request mappings
        swaggerApiResourceListing.setApiListingReferenceScanner(apiListingReferenceScanner());

        return swaggerApiResourceListing;
    }

    @Bean
    /**
     * The ApiListingReferenceScanner does most of the work.
     * Scans the appropriate spring RequestMappingHandlerMappings
     * Applies the correct absolute paths to the generated swagger resources
     */
    public ApiListingReferenceScanner apiListingReferenceScanner() {
        ApiListingReferenceScanner apiListingReferenceScanner = new ApiListingReferenceScanner();

        // Picks up all of the registered spring RequestMappingHandlerMappings for scanning
        apiListingReferenceScanner
                .setRequestMappingHandlerMapping(springSwaggerConfig.swaggerRequestMappingHandlerMappings());

        // Excludes any controllers with the supplied annotations
        apiListingReferenceScanner.setExcludeAnnotations(springSwaggerConfig.defaultExcludeAnnotations());

        // shiziye: regroup
        apiListingReferenceScanner.setResourceGroupingStrategy(apiResourceGroupingStrategy());

        // Path provider used to generate the appropriate uri's
        apiListingReferenceScanner.setSwaggerPathProvider(apiPathProvider());

        // Must match the swagger group set on the SwaggerApiResourceListing
        apiListingReferenceScanner.setSwaggerGroup(SWAGGER_GROUP);

        // Only include paths that match the supplied regular expressions
        apiListingReferenceScanner.setIncludePatterns(DEFAULT_INCLUDE_PATTERNS);

        return apiListingReferenceScanner;
    }

    /**
     * Example of a custom path provider
     */
    @Bean
    public ApiPathProvider apiPathProvider() {
        ApiPathProvider apiPathProvider = new ApiPathProvider(docsLocation);
        apiPathProvider.setDefaultSwaggerPathProvider(springSwaggerConfig.defaultSwaggerPathProvider());
        return apiPathProvider;
    }

    private List<AuthorizationType> authorizationTypes() {
        ArrayList<AuthorizationType> authorizationTypes = new ArrayList<AuthorizationType>();

        List<AuthorizationScope> authorizationScopeList = newArrayList();
        authorizationScopeList.add(new AuthorizationScope("global", "access all"));

        List<GrantType> grantTypes = newArrayList();

        LoginEndpoint loginEndpoint = new LoginEndpoint(apiPathProvider().getAppBasePath() + "/user/authenticate");
        grantTypes.add(new ImplicitGrant(loginEndpoint, "access_token"));

        return authorizationTypes;
    }

    @Bean
    public SwaggerPathProvider relativeSwaggerPathProvider() {
        return new ApiRelativeSwaggerPathProvider();
    }

    private class ApiRelativeSwaggerPathProvider extends DefaultSwaggerPathProvider {
        @Override
        public String getAppBasePath() {
            return "/";
        }

        // @Override
        public String getSwaggerDocumentationBasePath() {
            return "/api-docs";
        }
    }

    static class ApiPathProvider implements SwaggerPathProvider {
        private SwaggerPathProvider defaultSwaggerPathProvider;
        @Autowired
        private ServletContext servletContext;

        private String docsLocation;

        public ApiPathProvider(String docsLocation) {
            this.docsLocation = docsLocation;
        }

        @Override
        public String getApiResourcePrefix() {
            return defaultSwaggerPathProvider.getApiResourcePrefix();
        }

        @Override
        public String getAppBasePath() {
            return UriComponentsBuilder.fromHttpUrl(docsLocation).path(servletContext.getContextPath()).build()
                    .toString();
        }

        @Override
        public String sanitizeRequestMappingPattern(String requestMappingPattern) {
            return defaultSwaggerPathProvider.sanitizeRequestMappingPattern(requestMappingPattern);
        }

        public void setDefaultSwaggerPathProvider(SwaggerPathProvider defaultSwaggerPathProvider) {
            this.defaultSwaggerPathProvider = defaultSwaggerPathProvider;
        }
    }

    @Bean
    public ResourceGroupingStrategy apiResourceGroupingStrategy() {
        return new ApiGroupingStrategy();
    }

    static class ApiGroupingStrategy implements ResourceGroupingStrategy {
        @Override
        public Set<ResourceGroup> getResourceGroups(RequestMappingInfo requestMappingInfo,
                                                    HandlerMethod handlerMethod) {
            Class<?> controllerClass = handlerMethod.getBeanType();

            String[] parts = controllerClass.getName().split("\\.");
            String controllerName =
                    org.apache.commons.lang.StringUtils.removeEnd(controllerClass.getSimpleName(), "Controller");
            String serviceName = "";
            for (int i = parts.length - 2; i > 0; i--) {
                String name = parts[i];
                if (!name.toLowerCase().equals("controller")) {
                    serviceName = name;
                    break;
                }
            }

            String groupName = serviceName.toUpperCase() + "_" + controllerName;
            return newHashSet(new ResourceGroup(maybeChompLeadingSlash(groupName), "/"));
        }

        @Override
        public String getResourceDescription(RequestMappingInfo requestMappingInfo, HandlerMethod handlerMethod) {
            Class<?> controllerClass = handlerMethod.getBeanType();
            String description = splitCamelCase(controllerClass.getSimpleName(), " ");

            Api apiAnnotation = AnnotationUtils.findAnnotation(controllerClass, Api.class);
            if (null != apiAnnotation) {
                String descriptionFromAnnotation =
                        Optional.fromNullable(emptyToNull(apiAnnotation.value())).or(apiAnnotation.description());
                if (!isNullOrEmpty(descriptionFromAnnotation)) {
                    return descriptionFromAnnotation;
                }
            }
            return description;
        }
    }
}
