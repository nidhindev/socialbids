package com.hackathon.socialbids.domain.bid

import groovy.transform.Canonical

@Canonical
class BidMessage {
    String id;
    String type;
    List<String> replyOptions;
    String message;
}
