package com.hackathon.socialbids.controller

import com.hackathon.socialbids.domain.messenger.receive.MessengerReceivedMessage
import groovy.util.logging.Slf4j
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

import static org.springframework.http.HttpStatus.OK
import static org.springframework.http.HttpStatus.UNAUTHORIZED
import static org.springframework.web.bind.annotation.RequestMethod.GET
import static org.springframework.web.bind.annotation.RequestMethod.POST

@RestController
@RequestMapping('/socialbids')
@Slf4j
class SocialBids {
    @RequestMapping(value = 'healthcheck', method = GET)
    ResponseEntity webhook() {
        log.info('App has started!!!')
        new ResponseEntity(OK)
    }

    @RequestMapping(value = 'verify', method = GET)
    ResponseEntity<String> verifyChallenge(@RequestParam('hub.mode') String mode,
                                           @RequestParam('hub.challenge') String challenge,
                                           @RequestParam('hub.verify_token') String verifyToken) {
        if ('subscribe' == mode && 'socialbidsverify' == verifyToken) {
            log.info('Webhook registration successful')
            ResponseEntity.ok(challenge)
        } else {
            log.error('Webhook registration failed for token : {}', verifyToken)
            new ResponseEntity('Webhook registration failed for token', UNAUTHORIZED)
        }

    }

    @RequestMapping(value = 'verify', method = POST)
    ResponseEntity webhook(
            @RequestBody MessengerReceivedMessage  messengerReceivedMessage,
            @RequestHeader('X-Hub-Signature') String xHubSignature) {
        log.info('Request from facebook : {}', messengerReceivedMessage.entry.find().messaging.find().sender.id)
        new ResponseEntity(OK)

    }
}
