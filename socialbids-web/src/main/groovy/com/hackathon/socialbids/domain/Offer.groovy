package com.hackathon.socialbids.domain

import com.fasterxml.jackson.annotation.JsonInclude
import groovy.transform.Canonical

@Canonical
@JsonInclude(JsonInclude.Include.NON_NULL)
class Offer {
    List<String> winners = []
    List<String> loosers = []
}
