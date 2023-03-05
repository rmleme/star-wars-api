package org.rmleme.starwarsapi.persistence.configuration

import com.mongodb.client.MongoClients
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@Configuration
@EnableMongoRepositories(basePackages = ["org.rmleme.starwarsapi.persistence"])
@PropertySource("classpath:persistence.properties")
class MongoConfiguration : AbstractMongoClientConfiguration() {

    @Value("\${spring.data.mongodb.uri}")
    private lateinit var uri: String

    @Value("\${spring.data.mongodb.database}")
    private lateinit var database: String

    @Bean
    override fun mongoClient() = MongoClients.create(uri)

    override fun getDatabaseName() = database
}
