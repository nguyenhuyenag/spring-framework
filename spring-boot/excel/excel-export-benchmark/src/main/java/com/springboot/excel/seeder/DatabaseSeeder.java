package com.springboot.excel.seeder;

import com.springboot.excel.entity.Customer;
import com.springboot.excel.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    
    private static final int TARGET_RECORD_COUNT = 100000;
    private static final int BATCH_SIZE = 5000;

    @Override
    public void run(String @NonNull ... args) throws Exception {
        long currentCount = customerRepository.count();
        if (currentCount < TARGET_RECORD_COUNT) {
            long recordsToGenerate = TARGET_RECORD_COUNT - currentCount;
            log.info("Database has {} records, generating {} more...", currentCount, recordsToGenerate);

            for (int i = (int) currentCount; i < TARGET_RECORD_COUNT; i += BATCH_SIZE) {
                int currentBatchSize = Math.min(BATCH_SIZE, TARGET_RECORD_COUNT - i);
                List<Customer> batch = new ArrayList<>();
                
                for (int j = 0; j < currentBatchSize; j++) {
                    int currentIndex = i + j + 1;
                    Customer customer = new Customer();
                    customer.setFirstName("FirstName" + currentIndex);
                    customer.setLastName("LastName" + currentIndex);
                    customer.setEmail("customer" + currentIndex + "@example.com");
                    customer.setPhone("12345678" + currentIndex);
                    customer.setAddress("Address " + currentIndex);
                    customer.setCity("City" + (currentIndex % 100));
                    customer.setCountry("Country" + (currentIndex % 50));
                    customer.setJobTitle("Job Title " + (currentIndex % 20));
                    customer.setJoinDate(LocalDate.now());
                    batch.add(customer);
                }
                customerRepository.saveAll(batch);
                log.info("Inserted {} records out of {}", (i + currentBatchSize), TARGET_RECORD_COUNT);
            }
            log.info("Data generation completed.");
        } else {
            log.info("Database already contains {} or more records, skipping seeder.", TARGET_RECORD_COUNT);
        }
    }
}
