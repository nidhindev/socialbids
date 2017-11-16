package com.hackathon.socialbids.service

import com.hackathon.socialbids.domain.Customer
import com.hackathon.socialbids.domain.Offer
import com.hackathon.socialbids.domain.bid.BidMessage
import com.hackathon.socialbids.repositories.CustomerRepository
import com.hackathon.socialbids.repositories.FlightRepository
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
@Slf4j
class BiddingService {

    @Autowired
    FlightRepository flightRepository

    @Autowired
    CustomerService customerService

    @Autowired
    CustomerRepository customerRepository

    @Autowired
    RestTemplate restTemplate

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
        if (customer?.amount + amountIncrement < minBidAmount && !customer.isOldBid) {
            def message2 = new BidMessage(id: oldCustomer.customerId, type: 'quickReply', replyOptions: ['+10', '+15', '+20', 'No'], message: 'Sorry! mean time you got over bid by another passenger with ' + minBidAmount + '. Do you want to raise your bid?')
            customer.isOldBid = true;
            customerRepository.save(customer)
            customerService.sendToCustomer(message2, 'quickReply', 'bid')
        } else {
            customer.isOldBid = false
            if (customer) {
                customer.amount = minBidAmount + amountIncrement
            } else {
                def newCustomer = new Customer(customerId: id, customerName: customersIdNameMap.get(id), amount: minBidAmount + amountIncrement)
                customerRepository.save(newCustomer)
            }
            customerRepository.save(customer)
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

    void finalizeBids() {
        List<Customer> customerList = customerRepository.findAll()
        Customer hightesBidder = customerList[0]
        customerList.forEach() {
            if (it.amount > hightesBidder.amount)
                hightesBidder = it
        }
        Offer offer = new Offer();
        customerList.forEach() {
            if (hightesBidder.customerId == it.customerId) {
                it.result = true
                def message2 = new BidMessage(id: it.customerId, type: 'text', message: 'Congratulations 🎉🎉🎉 You have won the bid for ' + it.amount + 'Euro. You will receive the confirmation shortly')
                customerService.sendToCustomer(message2, 'text', 'bid')
                offer.winners.add(it.customerId)
            } else {
                it.result = false
                def message2 = new BidMessage(id: it.customerId, type: 'text', message: 'Sorry to say!! You have lost the bid. You will receive the token of appreciation shortly')
                customerService.sendToCustomer(message2, 'text', 'bid')
                offer.loosers.add(it.customerId)
            }
        }
        try {
            ResponseEntity object = restTemplate.postForObject('https://gamifybids.herokuapp.com/finalizebids', offer, Offer)
            log.info('finalizebids API response ' + object.statusCode)
        } catch (Exception e) {
            log.error(e)
        }
    }
}
