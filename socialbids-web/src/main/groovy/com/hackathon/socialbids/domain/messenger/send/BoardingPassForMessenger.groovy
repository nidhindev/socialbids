package com.hackathon.socialbids.domain.messenger.send

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical


@Canonical
@JsonInclude(JsonInclude.Include.NON_NULL)
class BoardingPassForMessenger {

    @JsonProperty("passenger_name")
    String passengerName
    @JsonProperty("pnr_number")
    String pnr
    @JsonProperty("travel_class")
    String travelClass
    String seat
    @JsonProperty("auxiliary_fields")
    ArrayList<Field> auxiliaryField
    @JsonProperty("secondary_fields")
    ArrayList<Field> secondaryField
    @JsonProperty("logo_image_url")
    URL logoImageURL
    @JsonProperty("header_image_url")
    URL headerImageURL
    @JsonProperty("header_text_field")
    Field headerTextField
    @JsonProperty("qr_code")
    String barcodeString
    @JsonProperty("barcode_image_url")
    URL barCodeImageURL
    @JsonProperty("above_bar_code_image_url")
    URL frequentFlyerStatusImageURL
    @JsonProperty("flight_info")
    Segment segment
}
