package com.hackathon.socialbids.controller

import com.hackathon.socialbids.domain.flightOffer.FlightOffer
import com.hackathon.socialbids.service.CustomerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import static org.springframework.web.bind.annotation.RequestMethod.POST

@RequestMapping('/socialbids/genericMessage')
@RestController
class GenericMessage {

    @Autowired
    CustomerService CustomerService

    @RequestMapping(method = POST)
    ResponseEntity genericMessage(@RequestBody FlightOffer flightOffer) {

        CustomerService.sendToCustomer(flightOffer, flightOffer.type,'')


    }
}
