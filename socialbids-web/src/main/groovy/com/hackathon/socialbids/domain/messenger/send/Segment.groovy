package com.hackathon.socialbids.domain.messenger.send

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical


@Canonical
@JsonInclude(JsonInclude.Include.NON_NULL)
class Segment {

    @JsonProperty("connection_id")
    String connectionID
    @JsonProperty("segment_id")
    String segmentID
    @JsonProperty("flight_number")
    String flightNumber
    @JsonProperty("aircraft_type")
    String aircraftType
    @JsonProperty("departure_airport")
    Airport departureAirport
    @JsonProperty("arrival_airport")
    Airport arrivalAirport
    @JsonProperty("flight_schedule")
    FlightSchedule flightSchedule
    @JsonProperty("travel_class")
    String travelClass
}
