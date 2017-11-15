package com.hackathon.socialbids.domain.messenger.send

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical


@Canonical
@JsonInclude(JsonInclude.Include.NON_NULL)
class PassengerSegmentInfo {
    @JsonProperty("segment_id")
    String segmentId
    @JsonProperty("passenger_id")
    String passengerId
    String seat
    @JsonProperty("seat_type")
    String seatType
    @JsonProperty("product_info")
    ArrayList<ProductInfo> productInfo
}
