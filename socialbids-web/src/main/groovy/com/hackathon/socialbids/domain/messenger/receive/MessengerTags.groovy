package com.hackathon.socialbids.domain.messenger.receive

import com.fasterxml.jackson.annotation.JsonInclude
import groovy.transform.Canonical

@JsonInclude(JsonInclude.Include.NON_NULL)
@Canonical
class MessengerTags {

    String source
}
