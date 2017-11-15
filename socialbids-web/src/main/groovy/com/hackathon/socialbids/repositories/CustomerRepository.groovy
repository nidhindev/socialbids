package com.hackathon.socialbids.repositories

import com.hackathon.socialbids.domain.Customer
import com.hackathon.socialbids.domain.Flight
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository extends MongoRepository<Customer, String> {

    List<Customer> findAllByCustomerId(String customerId)

}