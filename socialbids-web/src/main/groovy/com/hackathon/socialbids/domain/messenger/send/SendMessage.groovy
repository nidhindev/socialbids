package com.hackathon.socialbids.domain.messenger.send

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical


@Canonical
@JsonInclude(JsonInclude.Include.NON_NULL)
class SendMessage {
    String text
    Attachment attachment
    @JsonProperty("quick_replies")
    List<QuickReplies> quickReplies
}
