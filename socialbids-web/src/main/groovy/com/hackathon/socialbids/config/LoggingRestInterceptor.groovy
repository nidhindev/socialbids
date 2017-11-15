package com.hackathon.socialbids.config

import groovy.util.logging.Slf4j
import org.springframework.http.HttpRequest
import org.springframework.http.client.ClientHttpRequestExecution
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.http.client.ClientHttpResponse

@Slf4j
class LoggingRestInterceptor implements ClientHttpRequestInterceptor {
    @Override
    ClientHttpResponse intercept(HttpRequest httpRequest, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        log.info('Request: {} {}\npayload: {}', httpRequest.method, httpRequest.URI, new String(body))
        ClientHttpResponse response = execution.execute(httpRequest, body)
        response
    }
}
