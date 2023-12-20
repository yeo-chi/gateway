package yeo.chi.gateway.configuration

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig
import io.github.resilience4j.timelimiter.TimeLimiterConfig
import java.time.Duration
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder
import org.springframework.cloud.client.circuitbreaker.Customizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class CircuitBreakerConfiguration {
    @Bean
    fun defaultCustomizer() = Customizer<Resilience4JCircuitBreakerFactory> { factory ->
        factory.configureDefault { id ->
            Resilience4JConfigBuilder(id)
                .timeLimiterConfig(
                    TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(4)).build()
                )
                .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
                .build()
        }
    }
}
