package com.hackathon.socialbids.domain.messenger.send

import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical

@Canonical
class FbRecipient {

    @JsonProperty('user_ref')
    String userRefef
}
