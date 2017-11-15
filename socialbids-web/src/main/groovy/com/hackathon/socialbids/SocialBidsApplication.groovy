package com.hackathon.socialbids

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration

@SpringBootApplication
@EnableAutoConfiguration
class SocialBidsApplication {

    static void main(String[] args) {
        SpringApplication.run(SocialBidsApplication, args)
    }
}
