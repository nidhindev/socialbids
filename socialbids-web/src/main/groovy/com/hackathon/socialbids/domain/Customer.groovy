package com.hackathon.socialbids.domain

import groovy.transform.Canonical
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Canonical
@Document
class Customer {

    @Id
    String id

    String customerId
    String customerName
    Long amount
    Boolean result
}
