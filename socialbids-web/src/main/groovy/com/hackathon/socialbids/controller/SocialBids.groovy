package com.hackathon.socialbids.controller

import groovy.util.logging.Slf4j
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

import static org.springframework.http.HttpStatus.OK

@RestController
@RequestMapping('/socialbids/healthcheck')
@Slf4j
class SocialBids {
    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity webhook() {
        log.info('App has started!!!')
        new ResponseEntity(OK)
    }
}
