package com.homihq.gqlreactive;

import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;

@Controller
@Slf4j
public class StockController {

    @QueryMapping
    public Mono<StockDetail> stockDetail(@Argument String symbol) {
        log.info("Symbol - {}", symbol);
        return Mono.just(new StockDetail("IBM", "IBM", "100b"));
    }

    @SubscriptionMapping
    public Flux<StockPrice> stockPrice(@Argument  String symbol) {
        log.info("Symbol - ", symbol);
        Random random = new Random();
        return Flux.interval(Duration.ofSeconds(1))
                .map(num -> new StockPrice(symbol, random.nextDouble(), LocalDateTime.now()));
    }
    public record StockPrice(String symbol, double price, LocalDateTime timestamp) {}
    public record StockDetail(String symbol, String name, String marketCap) {}

}
