package com.hackathon.socialbids.domain.messenger.receive

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical


@Canonical
@JsonInclude(JsonInclude.Include.NON_NULL)
class MessengerLocation {

    float lat
    @JsonProperty("long")
    float longitude

}

