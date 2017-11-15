package com.hackathon.socialbids.controller

import com.hackathon.socialbids.domain.Flight
import com.hackathon.socialbids.repositories.FlightHistoryRepository
import com.hackathon.socialbids.repositories.FlightRepository
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import javax.annotation.Resource

import static org.springframework.http.HttpStatus.OK
import static org.springframework.http.HttpStatus.UNAUTHORIZED
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
    FlightHistoryRepository flightHistoryRepository

    @RequestMapping(value = 'process', method = GET)
    ResponseEntity verifyChallenge() {

        //def flights = flightRepository.findAll()
        def flight = new Flight(flightNumber: "SK1419", upperBidValue: 500, lowerBidValue: 100)
        def flightHistorys = flightHistoryRepository.findAll()
        def overBid = false;
        flightHistorys.forEach() {
            if(it.soldBusinessSeats.toInteger()>7)
                overBid = true
        }

        new ResponseEntity(OK)

    }

}