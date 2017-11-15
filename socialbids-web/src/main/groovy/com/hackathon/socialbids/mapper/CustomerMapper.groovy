package com.hackathon.socialbids.mapper

import com.hackathon.socialbids.domain.bid.BidMessage
import com.hackathon.socialbids.domain.flightOffer.FlightOffer
import com.hackathon.socialbids.domain.messenger.send.Attachment
import com.hackathon.socialbids.domain.messenger.send.Button
import com.hackathon.socialbids.domain.messenger.send.Element
import com.hackathon.socialbids.domain.messenger.send.Payload
import com.hackathon.socialbids.domain.messenger.send.QuickReplies
import com.hackathon.socialbids.domain.messenger.send.SendMessage

final class CustomerMapper {

    static SendMessage mappToCarousal(FlightOffer flightOffer) {
        SendMessage sendMessage = new SendMessage()
        Attachment attachment = new Attachment()
        attachment.type = 'template'
        List<Element> elements = []
        Payload payload = new Payload()
        payload.templateType = 'generic'
        flightOffer.flights.forEach() {

            Element element = new Element(title: it.text, imageurl: it.imageUrl, subtitle: "great to see you")
            List<Button> buttons = [new Button(type: 'web_url', url: it.bookingUrl, title: 'Book Now')]
            element.buttons = buttons
            elements << element
        }
        payload.elements = elements
        attachment.payload = payload
        sendMessage.attachment = attachment
        sendMessage
    }

    static SendMessage mappToQuickReply(BidMessage bidMessage, String tag) {
        SendMessage sendMessage = new SendMessage()
        sendMessage.text = bidMessage.message
        List<QuickReplies> quickReplies = []
        bidMessage.replyOptions.forEach() {
            QuickReplies quickReply = new QuickReplies()
            quickReply.title = it
            quickReply.contentType = 'text'
            quickReply.payload = tag+'_'+it
            quickReplies << quickReply
        }
        sendMessage.quickReplies = quickReplies
        sendMessage

    }

}
