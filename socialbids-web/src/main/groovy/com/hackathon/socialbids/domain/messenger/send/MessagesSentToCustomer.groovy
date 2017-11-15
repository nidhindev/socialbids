package com.hackathon.socialbids.domain.messenger.send

import com.fasterxml.jackson.annotation.JsonInclude
import com.hackathon.socialbids.domain.messenger.receive.MessengerRecipient
import groovy.transform.Canonical

/**
 * MongoDB stores data in collections.
 * Spring Data MongoDB will maps a class to a collection having same name.
 * If you want to change the name of the collection, you can use
 * Spring Data MongoDB’s @Document annotation on the class.
 * eg: @Document(collection = "messengerTextMessage")
 */
@Canonical
@JsonInclude(JsonInclude.Include.NON_NULL)
class MessagesSentToCustomer {

    String _id
    String messageID
    Long watermark
    Date timestamp
    String messageStatus
    String errorMsg
    MessengerRecipient recipient
    SendMessage sendMessage
    boolean readStatus
}
