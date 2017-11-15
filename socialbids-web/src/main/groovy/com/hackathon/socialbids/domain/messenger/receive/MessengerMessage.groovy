package com.hackathon.socialbids.domain.messenger.receive

import com.fasterxml.jackson.annotation.JsonInclude
import groovy.transform.Canonical


@Canonical
@JsonInclude(JsonInclude.Include.NON_NULL)
class MessengerMessage {
    Boolean is_echo
    String mid
    int seq
    List<MessengerAttachment> attachments
    String text
    long sticker_id
    MessengerQuickReply quick_reply
    MessengerTags tags
}
