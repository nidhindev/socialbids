package com.hackathon.socialbids.domain.messenger.receive

import com.fasterxml.jackson.annotation.JsonInclude
import groovy.transform.Canonical

@Canonical
@JsonInclude(JsonInclude.Include.NON_NULL)
class MessengerMessaging {

    MessengerSender sender
    MessengerRecipient recipient
    long timestamp
    MessengerMessage message
    MessengerPostback postback
    MessengerDelivery delivery
    MessengerRead read
    MessengerOptin optin
}
