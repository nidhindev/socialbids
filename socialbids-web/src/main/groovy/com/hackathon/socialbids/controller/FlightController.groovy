package com.hackathon.socialbids.controller

import com.hackathon.socialbids.domain.Flight
import com.hackathon.socialbids.domain.bid.BidMessage
import com.hackathon.socialbids.repositories.FlightHistoryRepository
import com.hackathon.socialbids.repositories.FlightRepository
import com.hackathon.socialbids.service.BiddingService
import com.hackathon.socialbids.service.CustomerService
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import static org.springframework.http.HttpStatus.OK
import static org.springframework.web.bind.annotation.RequestMethod.GET

@RestController
@RequestMapping('/bids')
@Slf4j
class FlightController {
    //querry flight search api to get all the flights on given date.
    //compared with database flights if any matches then process bid for that flight
    //get the history for the flight to check if it is upperbid or lower bid
    @Autowired
    FlightRepository flightRepository;

    @Autowired
    CustomerService customerService;

    @Autowired
    BiddingService biddingService;

    @Autowired
    FlightHistoryRepository flightHistoryRepository

    @RequestMapping(value = 'process', method = GET)
    ResponseEntity verifyChallenge() {

        def flights = flightRepository.findAll()
        def flight = flights[0]
        def flightHistorys = flightHistoryRepository.findAll()
        def overBid = false;
        flightHistorys.forEach() {
            if (it.soldBusinessSeats.toInteger() > 7)
                overBid = true
        }
        def messages = [new BidMessage(id: "1769834176420354", type: 'quickReply', replyOptions: ['Yes', 'No'], message: 'Hi Nidhin dev,  For the Flight ✈️ which you have booked on 26th December biding for Paid upgrade is open. Do you want to participate?'),
                        new BidMessage(id: "1563047500438143", type: 'quickReply', replyOptions: ['Yes', 'No'], message: 'Hi Vinod,  For the Flight ✈️ which you have booked on 26th December biding Paid upgrade is open. Do you want to participate?'),
                        new BidMessage(id: "1486773568044876", type: 'quickReply', replyOptions: ['Yes', 'No'], message: 'Hi Divya,  For the Flight ✈️ which you have booked on 26th December biding Paid upgrade is open. Do you want to participate?'),
                        new BidMessage(id: "1530825326952542", type: 'quickReply', replyOptions: ['Yes', 'No'], message: 'Hi Devayan,  For the Flight ✈️ which you have booked on 26th December biding Paid upgrade is open. Do you want to participate?')]
        messages.forEach() {
            customerService.sendToCustomer(it, 'quickReply', 'intro')
        }
        new ResponseEntity(OK)

    }
    @RequestMapping(value = 'finalize', method = GET)
    ResponseEntity finalizeBids(){
        biddingService.finalizeBids()
        new ResponseEntity(OK)
    }

}
