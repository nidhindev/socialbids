package com.hackathon.socialbids.service

import com.hackathon.socialbids.domain.Customer
import com.hackathon.socialbids.domain.bid.BidMessage
import com.hackathon.socialbids.repositories.CustomerRepository
import com.hackathon.socialbids.repositories.FlightRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BiddingService {

    @Autowired
    FlightRepository flightRepository

    @Autowired
    CustomerService customerService

    @Autowired
    CustomerRepository customerRepository

    Map<String, String> customersIdNameMap = ['1563047500438143': 'Vinod', '1486773568044876': 'Divya', '1769834176420354': 'Nidhin']

    void sendBidValueToCustomer(String id) {
        def flights = flightRepository.findAll()
        def flight = flights[0]
        def minBidAmount = flight.upperBidValue
        def customersPlacedBid = customerRepository.findAll()
        if (customersPlacedBid.size() > 0) {
            customersPlacedBid.forEach() {
                if (it.amount > minBidAmount) {
                    minBidAmount = it.amount
                }
            }
        }
        def message = new BidMessage(id: id, type: 'quickReply', replyOptions: ['+10', '+15', '+20'], message: 'Hey! excited to have you on board. your base bid is ' + minBidAmount + '. Please place your bid')
        customerService.sendToCustomer(message, 'quickReply', 'bid')
    }

    void saveCustomerBid(String id, Long amountIncrement, boolean isRebid) {

        def flights = flightRepository.findAll()
        def flight = flights[0]
        def minBidAmount = flight.upperBidValue
        def customersPlacedBid = customerRepository.findAll()
        def oldCustomer
        if (customersPlacedBid.size() > 0) {
            customersPlacedBid.forEach() {
                if (it.amount > minBidAmount) {
                    minBidAmount = it.amount
                    oldCustomer = it
                }
            }
        }
        Customer customer = customerRepository.findByCustomerId(id);
        /* def customerBid = 0;
         if (customers) {
             customers.forEach() {
                 if (it.amount > customerBid) {
                     customerBid = it.amount
                 }
             }
         }
         if (customerBid + amountIncrement < minBidAmount && customerBid > 0 && !isRebid) {
             def message2 = new BidMessage(id: customers[0].customerId, type: 'quickReply', replyOptions: ['+10', '+15', '+20', 'No'], message: 'you are too late to respond. Do you like to bid on  ' + (minBidAmount + amountIncrement) + '?')
             customerService.sendToCustomer(message2, 'quickReply', 'rebid')
         } else {*/

        if (customer) {
            customer.amount = minBidAmount + amountIncrement
            customerRepository.save(customer)
        } else {
            def newCustomer = new Customer(customerId: id, customerName: customersIdNameMap.get(id), amount: minBidAmount + amountIncrement)
            customerRepository.save(newCustomer)
        }
        def message = new BidMessage(id: id, type: 'text', message: '👍')
        customerService.sendToCustomer(message, 'text', 'text')
        if (oldCustomer) {
            def message2 = new BidMessage(id: oldCustomer.customerId, type: 'quickReply', replyOptions: ['+10', '+15', '+20', 'No'], message: 'you got over bid by another passenger with ' + (minBidAmount + amountIncrement) + '. Do you want to raise your bid?')
            customerService.sendToCustomer(message2, 'quickReply', 'bid')
            /*oldCustomer.isOldBid = true
            customerRepository.save(oldCustomer)*/
            //}
        }
    }
}
