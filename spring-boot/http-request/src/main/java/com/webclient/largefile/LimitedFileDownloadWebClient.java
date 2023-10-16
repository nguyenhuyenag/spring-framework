package com.webclient.largefile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class LimitedFileDownloadWebClient {

	// If we don’t allocate enough memory, we’ll get an OutOfMemoryError
	public static long fetch(WebClient client, String destination) throws IOException {
		Mono<byte[]> mono = client.get().retrieve().bodyToMono(byte[].class);
		byte[] bytes = mono.block();
		Path path = Paths.get(destination);
		Files.write(path, bytes);
		return bytes.length;
	}

	public static long fetchAnyFileSizeWithDataBuffer(WebClient client, String destination) throws IOException {
		Flux<DataBuffer> flux = client.get().retrieve().bodyToFlux(DataBuffer.class);
		Path path = Paths.get(destination);
		DataBufferUtils.write(flux, path).block();
		return Files.size(path);
	}

	public static void main(String... args) throws IOException {
		String baseUrl = "http://localhost:8081/large-file";
		String destination = "C:/Users/huyennv/Desktop/new/download_large.dat";
		WebClient client = WebClient.builder().baseUrl(baseUrl).exchangeStrategies(useMaxMemory()).build();
		// long bytes = fetch(client, destination);
		long bytes = fetchAnyFileSizeWithDataBuffer(client, destination);
		System.out.printf("downloaded %d bytes", bytes);
	}

	private static ExchangeStrategies useMaxMemory() {
		long totalMemory = Runtime.getRuntime().maxMemory();
		return ExchangeStrategies.builder()
				.codecs(configurer -> configurer.defaultCodecs().maxInMemorySize((int) totalMemory)) //
				.build();
	}

}
