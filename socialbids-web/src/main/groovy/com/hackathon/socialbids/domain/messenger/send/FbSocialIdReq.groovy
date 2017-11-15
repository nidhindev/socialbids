package com.hackathon.socialbids.domain.messenger.send

import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical

@Canonical
class FbSocialIdReq {

    @JsonProperty('message')
    FbMessage fbMessage
    @JsonProperty('recipient')
    FbRecipient fbRecipient
}
