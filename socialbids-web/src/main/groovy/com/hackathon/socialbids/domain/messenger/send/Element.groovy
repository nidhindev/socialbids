package com.hackathon.socialbids.domain.messenger.send

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical

import javax.validation.constraints.Size


@Canonical
@JsonInclude(JsonInclude.Include.NON_NULL)
class Element {

    @Size(max = 80)
    String title
    @JsonProperty("item_url")
    String itemurl
    @JsonProperty("image_url")
    String imageurl
    @Size(max = 80)
    String subtitle
    List<Button> buttons

}
