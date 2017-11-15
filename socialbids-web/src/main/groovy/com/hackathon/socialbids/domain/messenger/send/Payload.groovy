package com.hackathon.socialbids.domain.messenger.send

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical

@Canonical
@JsonInclude(JsonInclude.Include.NON_NULL)
class Payload {
    @JsonProperty('template_type')
    String templateType
    @JsonProperty('intro_message')
    String introMessage
    @JsonProperty('update_type')
    String updateType
    String locale
    @JsonProperty('pnr_number')
    String pnrNumber
    @JsonProperty('passenger_info')
    List<PassengerInfo> passengerInfoList
    @JsonProperty('flight_info')
    List<Segment> checkinSegment
    @JsonProperty('passenger_segment_info')
    List<PassengerSegmentInfo> passengerSegmentInfoList
    @JsonProperty('price_info')
    List<PriceInfo> priceInfoList
    @JsonProperty('base_price')
    Double basePrice
    Double tax
    @JsonProperty('total_price')
    Number totalPrice
    String currency
    @JsonProperty('update_flight_info')
    Segment segment
    @JsonProperty('image_aspect_ratio')
    String imageAspectratio
    @JsonProperty('boarding_pass')
    List<BoardingPassForMessenger> boardingPass
    @JsonProperty('checkin_url')
    String checkinUrl
    String url
    String text
    List<Button> buttons
    List<Element> elements
    @JsonProperty('theme_color') String themeColor
}
