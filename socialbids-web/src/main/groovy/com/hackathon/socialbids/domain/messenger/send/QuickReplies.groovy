package com.hackathon.socialbids.domain.messenger.send

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical


@Canonical
@JsonInclude(JsonInclude.Include.NON_NULL)
class QuickReplies {

    @JsonProperty("content_type")
    String contentType
    String title
    String payload
    @JsonProperty("image_url")
    URL imageURL
}
