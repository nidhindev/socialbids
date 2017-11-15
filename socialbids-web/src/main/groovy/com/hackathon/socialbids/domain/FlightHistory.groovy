package com.hackathon.socialbids.domain

import groovy.transform.Canonical
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Canonical
@Document
class FlightHistory {

    @Id
    public String id;

    String flightNumber;
    String origin;
    String destination;
    String date;
    String soldBusinessSeats;
    String businessSeats;
}
