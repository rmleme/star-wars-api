package org.rmleme.starwarsapi.application.acceptance.configuration

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(basePackages = ["org.rmleme.starwarsapi"])
class AcceptanceTestSetup
