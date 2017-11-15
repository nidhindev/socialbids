package com.hackathon.socialbids.domain.messenger.send

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical


@Canonical
@JsonInclude(JsonInclude.Include.NON_NULL)
class FlightSchedule {
    @JsonProperty("departure_time")
    String departureDateTime
    @JsonProperty("arrival_time")
    String arrivalDateTime
    @JsonProperty("boarding_time")
    String boardingTime
}
