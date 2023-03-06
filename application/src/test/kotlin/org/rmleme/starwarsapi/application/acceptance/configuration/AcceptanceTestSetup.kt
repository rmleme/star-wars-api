package org.rmleme.starwarsapi.application.acceptance.configuration

import com.mongodb.client.MongoClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.utility.DockerImageName

@Configuration
@ComponentScan(basePackages = ["org.rmleme.starwarsapi"])
class AcceptanceTestSetup {

    @Bean
    fun mongoClient() = MongoClients.create(mongoDBContainer.connectionString)

    companion object {
        private const val MONGODB_IMAGE = "mongo:6.0.4"

        @JvmStatic
        private val mongoDBContainer = MongoDBContainer(DockerImageName.parse(MONGODB_IMAGE))

        init {
            mongoDBContainer.start()
        }
    }
}
