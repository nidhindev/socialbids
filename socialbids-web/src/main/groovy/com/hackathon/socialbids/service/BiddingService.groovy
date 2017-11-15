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
    FlightRepository flightRepository;

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerRepository customerRepository;

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
        customerService.sendToCustomer(message, 'quickReply','bid')
    }

    void saveCustomerBid(String id, Long amountIncrement) {

        def flights = flightRepository.findAll()
        def flight = flights[0]
        def minBidAmount = flight.upperBidValue
        def customersPlacedBid = customerRepository.findAll()
        def oldCustomer;
        if (customersPlacedBid.size() > 0) {
            customersPlacedBid.forEach() {
                if (it.amount > minBidAmount) {
                    minBidAmount = it.amount
                    oldCustomer = it
                }
            }
        }
        def customer = new Customer(customerId: id, customerName: customersIdNameMap.get(id), amount: minBidAmount + amountIncrement)
        customerRepository.save(customer)
        def message = new BidMessage(id: id, type: 'text', message: '1F5FF')
        customerService.sendToCustomer(message, 'text')
        def message2 = new BidMessage(id: oldCustomer.id, type: 'quickReply', replyOptions: ['+10', '+15', '+20', 'No'], message: 'you got over bid by another passenger with ' + minBidAmount + amountIncrement + '. Do you want to raise your bid')
        customerService.sendToCustomer(message, 'quickReply','bid')
    }
}
