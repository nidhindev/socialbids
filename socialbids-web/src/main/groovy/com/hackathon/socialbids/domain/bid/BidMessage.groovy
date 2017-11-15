package com.hackathon.socialbids.domain.bid

import com.fasterxml.jackson.annotation.JsonInclude
import groovy.transform.Canonical

@Canonical
@JsonInclude(JsonInclude.Include.NON_NULL)
class BidMessage {
    String id
    String type
    List<String> replyOptions
    String message
}
