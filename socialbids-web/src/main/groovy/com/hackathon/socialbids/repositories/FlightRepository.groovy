package com.hackathon.socialbids.repositories

import com.hackathon.socialbids.domain.Flight
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface FlightRepository extends MongoRepository<Flight, String> {

}