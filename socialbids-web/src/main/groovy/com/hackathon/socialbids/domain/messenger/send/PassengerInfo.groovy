package com.hackathon.socialbids.domain.messenger.send

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical


@Canonical
@JsonInclude(JsonInclude.Include.NON_NULL)
class PassengerInfo {

    String name
    @JsonProperty("ticket_number")
    String ticketNumber
    @JsonProperty("passenger_id")
    String passengerId

}
