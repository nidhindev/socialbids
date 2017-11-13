package com.hackathon.socialbids.config

import org.springframework.context.annotation.Configuration
import org.springframework.http.client.SimpleClientHttpRequestFactory
import org.springframework.web.client.RestTemplate

@Configuration
class AppConfig {

    RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory()
        requestFactory.connectTimeout = 1000
        requestFactory.readTimeout = 1000
        new RestTemplate(requestFactory)
    }
}
