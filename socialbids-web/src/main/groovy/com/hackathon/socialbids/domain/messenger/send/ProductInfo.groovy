package com.hackathon.socialbids.domain.messenger.send

import com.fasterxml.jackson.annotation.JsonInclude
import groovy.transform.Canonical


@Canonical
@JsonInclude(JsonInclude.Include.NON_NULL)
class ProductInfo {

    String title
    String value

}
