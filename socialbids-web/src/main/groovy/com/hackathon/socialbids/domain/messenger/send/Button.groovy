package com.hackathon.socialbids.domain.messenger.send

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical


@Canonical
@JsonInclude(JsonInclude.Include.NON_NULL)
class Button {

    enum Type {
        @JsonProperty("web_url")
                web_url,
        @JsonProperty("postback")
                postback,
        @JsonProperty("element_share")
                element_share
    }

    enum WebviewHeightRatio {

        full,
        compact,
        tall
    }
    Type type
    String title
    URL url
    @JsonProperty("webview_height_ratio") WebviewHeightRatio webviewHeightRatio
    @JsonProperty("messenger_extensions") Boolean messengerExtensions
    String payload
    @JsonProperty("fallback_url") String fallbackUrl
}
