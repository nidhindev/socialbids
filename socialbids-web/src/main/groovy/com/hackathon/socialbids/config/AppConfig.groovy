package com.hackathon.socialbids.config

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import groovy.json.JsonSlurper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.http.client.SimpleClientHttpRequestFactory
import org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.client.RestTemplate

@Configuration
class AppConfig {

    @Bean
    RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory()
        requestFactory.connectTimeout = 1000
        requestFactory.readTimeout = 1000
        RestTemplate restTemplate = new RestTemplate(requestFactory)
        restTemplate.setInterceptors(Arrays.<ClientHttpRequestInterceptor> asList(loggingHttpRequestInterceptor()))
        restTemplate
    }

    @Bean
    JsonSlurper jsonSlurper() {
        new JsonSlurper()
    }

    @Bean
    ObjectMapper objectMapper() {
        final Jackson2ObjectMapperFactoryBean bean = new Jackson2ObjectMapperFactoryBean()
        bean.indentOutput = false
        bean.afterPropertiesSet()
        final ObjectMapper objectMapper = bean.getObject()
        if (objectMapper) {
            objectMapper.serializationInclusion = JsonInclude.Include.NON_NULL
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            objectMapper.configure(JsonParser.Feature.STRICT_DUPLICATE_DETECTION, true)
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        }
        objectMapper
    }

    @Bean
    MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter()
        converter.setObjectMapper(objectMapper())
        converter
    }

    @Bean
    ClientHttpRequestInterceptor loggingHttpRequestInterceptor() throws IOException {
        new LoggingRestInterceptor()
    }
}
