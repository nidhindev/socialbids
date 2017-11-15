package com.hackathon.socialbids.service

import com.hackathon.socialbids.domain.bid.BidMessage
import com.hackathon.socialbids.domain.flightOffer.FlightOffer
import com.hackathon.socialbids.domain.messenger.receive.MessengerMessaging
import com.hackathon.socialbids.domain.messenger.receive.MessengerReceivedMessage
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
    @Autowired BiddingService biddingService

    ResponseEntity<String> sendToCustomer(def object, String type, String tag) {

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
                SendMessage sendMessage = CustomerMapper.mappToQuickReply(bidMessage, tag)
                MessengerRecipient recipient = new MessengerRecipient(id: bidMessage.id)
                HttpHeaders headers = new HttpHeaders()
                headers.setContentType(MediaType.APPLICATION_JSON_UTF8)
                log.info('sending carousal')
                HttpEntity<MessageWrapper> entity = new HttpEntity<>(new MessageWrapper(message: sendMessage, recipient: recipient), headers)
                responseEntity = sendToFacebook(entity)
                break


        }
        responseEntity
    }

    private ResponseEntity<String> sendToFacebook(HttpEntity<MessageWrapper> entity) {
        String accessToken = 'EAAh2Dnr9X7QBAFBcPVXQMQZB8A7Jizf6rse6lbg9PUdncLXZAUk2kbHRrQCtLJ0NJ9ALLt14iUOkIHyZAjkqNp9yiLbMsCyfTdvu5Rnp9oKZBHjCxTlli9WNuhVNUTHZBkd4rn6GwLXVcHnJtodtNyTEZAzJe69GvsOhZCtHxGfGQZDZD'
        ResponseEntity<String> resEntity = null
        try {
            resEntity = restTemplate.exchange("https://graph.facebook.com/v2.9/me/messages?access_token=$accessToken" , HttpMethod.POST, entity, String)
        } catch (HttpClientErrorException e) {
            log.error('HttpClientErrorException error:  {}', e)
        }
        resEntity
    }

     def messageFromCustomer(MessengerReceivedMessage messengerReceivedMessage) {
        MessengerMessaging messengerMessaging = messengerReceivedMessage.entry.find().messaging.find() as MessengerMessaging
        if(messengerMessaging.message.quick_reply) {
            String socialId = messengerMessaging.sender.id
            String postback = messengerMessaging.message.quick_reply.payload
            String trimmedMsg = ''
            if(postback.split('_')[0] == 'intro') {
                trimmedMsg = postback.split('_')[1]
            }
            if (postback.split('_')[0] == 'bid') {
                trimmedMsg = postback.split('_')[1].substring(1)
            }
            log.info('The user: {} messaged :{}', socialId, postback)
            switch (trimmedMsg.toLowerCase()) {
                case 'yes' :
                    log.info('send for sendBidValueToCustomer')
                    biddingService.sendBidValueToCustomer(socialId)
                    break
                case '10' :
                    case '20':
                    case '15':
                    log.info(' sending to save bid: {}', trimmedMsg)
                    biddingService.saveCustomerBid(socialId, trimmedMsg as Long)



            }

        }

    }
}
