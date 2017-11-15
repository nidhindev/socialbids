package com.hackathon.socialbids.domain.messenger.send

import com.fasterxml.jackson.annotation.JsonInclude
import com.hackathon.socialbids.domain.messenger.receive.MessengerRecipient
import groovy.transform.Canonical

@Canonical
@JsonInclude(JsonInclude.Include.NON_NULL)
class MessageWrapper {
    MessengerRecipient recipient
    SendMessage message
    String sender_action
}
