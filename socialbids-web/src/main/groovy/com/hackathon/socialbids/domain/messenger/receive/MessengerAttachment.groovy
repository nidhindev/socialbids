package com.hackathon.socialbids.domain.messenger.receive

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical

@Canonical
@JsonInclude(JsonInclude.Include.NON_NULL)
class MessengerAttachment {
    enum Type {
        @JsonProperty("audio") audio,
        @JsonProperty("image") image,
        @JsonProperty("video") video,
        @JsonProperty("file") file,
        @JsonProperty("location")location,
        @JsonProperty("fallback") fallback,
        @JsonProperty("template") template
    }

    Type type
    MessengerPayload payload
}
