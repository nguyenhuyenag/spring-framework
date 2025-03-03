package com.controller.weblux;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;

import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
public class FluxController {

    @GetMapping(value = "/stream-flux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> getFlux() {
        // @formatter:off
        return Flux.interval(Duration.ofSeconds(2))
                   .map(sequence -> "Dynamic data: " + Instant.now().toEpochMilli());
        // @formatter:on
    }

    @GetMapping("/stream-sse")
    public Flux<ServerSentEvent<String>> streamEvents() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(sequence -> ServerSentEvent.<String>builder()
                        .id(String.valueOf(sequence))
                        .event("periodic-event")
                        .data("SSE - " + LocalTime.now())
                        .build());
    }

//	protected Flux<String> extracted() {
//		return Flux.interval(Duration.ofSeconds(1))
//			      .map(sequence -> "Flux - " + LocalTime.now().toString());
//	}

    protected Flux<String> extracted2() {
        return Flux.just("Data 1", "Data 2", "Data 3")
                .delayElements(Duration.ofMillis(500));
    }

}
