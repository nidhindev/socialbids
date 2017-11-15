package com.hackathon.socialbids.service

import com.hackathon.socialbids.domain.bid.BidMessage
import com.hackathon.socialbids.domain.flightOffer.FlightOffer
import com.hackathon.socialbids.domain.messenger.receive.MessengerRecipient
import com.hackathon.socialbids.domain.messenger.send.MessageWrapper
import com.hackathon.socialbids.domain.messenger.send.SendMessage
import com.hackathon.socialbids.mapper.CustomerMapper
import groovy.json.JsonSlurper
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate

@Component
@Slf4j
class CustomerService {

    @Autowired  RestTemplate restTemplate
    @Autowired JsonSlurper jsonSlurper

    ResponseEntity<String> sendToCustomer(def object, String type) {

        ResponseEntity<String> responseEntity = null
        switch (type) {
            case 'carousal':
                FlightOffer flightOffer = object as FlightOffer
                SendMessage sendMessage = CustomerMapper.mappToCarousal(flightOffer)
                MessengerRecipient recipient = new MessengerRecipient(id: flightOffer.id)
                HttpHeaders headers = new HttpHeaders()
                headers.setContentType(MediaType.APPLICATION_JSON_UTF8)
                HttpEntity<MessageWrapper> entity = new HttpEntity<>(new MessageWrapper(message: sendMessage, recipient: recipient), headers)
                responseEntity = sendToFacebook(entity)
                break

            case 'quickReply':
                BidMessage bidMessage = object as BidMessage
                SendMessage sendMessage = CustomerMapper.mappToQuickReply(bidMessage)
                MessengerRecipient recipient = new MessengerRecipient(id: bidMessage.id)
                HttpHeaders headers = new HttpHeaders()
                headers.setContentType(MediaType.APPLICATION_JSON_UTF8)
                HttpEntity<MessageWrapper> entity = new HttpEntity<>(new MessageWrapper(message: sendMessage, recipient: recipient), headers)
                responseEntity = sendToFacebook(entity)
                break


        }
        responseEntity
    }

    private ResponseEntity<String> sendToFacebook(HttpEntity<MessageWrapper> entity) {
        String accessToken = 'EAAh2Dnr9X7QBAFBcPVXQMQZB8A7Jizf6rse6lbg9PUdncLXZAUk2kbHRrQCtLJ0NJ9ALLt14iUOkIHyZAjkqNp9yiLbMsCyfTdvu5Rnp9oKZBHjCxTlli9WNuhVNUTHZBkd4rn6GwLXVcHnJtodtNyTEZAzJe69GvsOhZCtHxGfGQZDZD'
        ResponseEntity<String> resEntity = null
        def responseParamMap
        try {
            resEntity = restTemplate.exchange("https://graph.facebook.com/v2.9/me/messages?access_token=$accessToken" , HttpMethod.POST, entity, String)
        } catch (HttpClientErrorException e) {
            log.error('HttpClientErrorException error:  {}', e)
        }
        resEntity
    }
}
