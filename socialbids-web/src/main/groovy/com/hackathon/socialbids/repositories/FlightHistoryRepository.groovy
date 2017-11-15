package com.hackathon.socialbids.repositories

import com.hackathon.socialbids.domain.FlightHistory
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface FlightHistoryRepository extends MongoRepository<FlightHistory, String> {

}