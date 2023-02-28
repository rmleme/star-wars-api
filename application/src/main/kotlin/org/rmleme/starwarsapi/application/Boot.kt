package org.rmleme.starwarsapi.application

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(basePackages = ["org.rmleme.starwarsapi"])
class Boot

fun main() {
    AnnotationConfigApplicationContext(Boot::class.java)
}
