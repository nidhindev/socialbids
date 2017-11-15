package com.hackathon.socialbids.domain.messenger.receive

import com.fasterxml.jackson.annotation.JsonInclude
import groovy.transform.Canonical


@Canonical
@JsonInclude(JsonInclude.Include.NON_NULL)
class MessengerEntry {

    String id
    long time
    List<MessengerMessaging> messaging
}
