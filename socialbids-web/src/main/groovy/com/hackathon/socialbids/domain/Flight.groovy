package com.hackathon.socialbids.domain

import com.fasterxml.jackson.annotation.JsonInclude
import groovy.transform.Canonical
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Canonical
@Document
class Flight {
    @Id
    public String id;

    String flightNumber;
    String origin;
    String destination;
    Integer upperBidValue;
    Integer lowerBidValue;
    String date;
    String soldBusinessSeats;
    String businessSeats;
}
