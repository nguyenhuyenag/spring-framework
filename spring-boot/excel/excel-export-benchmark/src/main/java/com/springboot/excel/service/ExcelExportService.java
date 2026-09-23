package com.springboot.excel.service;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.springboot.excel.dto.CustomerExportDTO;
import com.springboot.excel.entity.Customer;
import com.springboot.excel.repository.CustomerRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExcelExportService {

    private final CustomerRepository customerRepository;

    private static final int PAGE_SIZE = 1000;
    private static final String CUSTOMERS_SHEET_NAME = "Customers";
    private static final String[] HEADERS = {
            "ID", "First Name", "Last Name", "Email", "Phone",
            "Address", "City", "Country", "Job Title", "Join Date"
    };

    public void exportUsingXssf(HttpServletResponse response) throws IOException {
        long startTime = System.nanoTime();
        log.info("Starting XSSF Export...");

        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet(CUSTOMERS_SHEET_NAME);

            // Header
            Row headerRow = sheet.createRow(0);
            createHeader(headerRow);

            AtomicInteger rowIdx = new AtomicInteger(1);
            processAllCustomers(customers -> {
                for (Customer customer : customers) {
                    Row row = sheet.createRow(rowIdx.getAndIncrement());
                    populateRow(row, customer);
                }
            }, "XSSF");

            workbook.write(response.getOutputStream());
        }

        long endTime = System.nanoTime();
        log.info("XSSF Export completed in {} ms", (endTime - startTime) / 1_000_000);
    }

    public void exportUsingSxssf(HttpServletResponse response) throws IOException {
        long startTime = System.nanoTime();
        log.info("Starting SXSSF Export...");

        // Keep 100 rows in memory, exceeding rows will be flushed to disk
        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100)) {
            Sheet sheet = workbook.createSheet(CUSTOMERS_SHEET_NAME);

            // Header
            Row headerRow = sheet.createRow(0);
            createHeader(headerRow);

            AtomicInteger rowIdx = new AtomicInteger(1);
            processAllCustomers(customers -> {
                for (Customer customer : customers) {
                    Row row = sheet.createRow(rowIdx.getAndIncrement());
                    populateRow(row, customer);
                }
            }, "SXSSF");

            workbook.write(response.getOutputStream());
            workbook.dispose(); // Delete temporary files
        }

        long endTime = System.nanoTime();
        log.info("SXSSF Export completed in {} ms", (endTime - startTime) / 1_000_000);
    }

    public void exportUsingEasyExcel(HttpServletResponse response) throws IOException {
        long startTime = System.nanoTime();
        log.info("Starting EasyExcel Export...");

        try (ExcelWriter excelWriter = EasyExcelFactory.write(response.getOutputStream(), CustomerExportDTO.class).build()) {
            WriteSheet writeSheet = EasyExcelFactory.writerSheet(CUSTOMERS_SHEET_NAME).build();

            processAllCustomers(customers -> {
                List<CustomerExportDTO> dtoList = new ArrayList<>();
                for (Customer customer : customers) {
                    dtoList.add(convertToDTO(customer));
                }
                excelWriter.write(dtoList, writeSheet);
            }, "EasyExcel");

            // EasyExcel's ExcelWriter.finish() is automatically called by try-with-resources
        }

        long endTime = System.nanoTime();
        log.info("EasyExcel Export completed in {} ms", (endTime - startTime) / 1_000_000);
    }

    public void exportUsingFesod(HttpServletResponse response) throws IOException {
        long startTime = System.nanoTime();
        log.info("Starting Fesod Export...");

        // FesodSheet is a hypothetical library similar to EasyExcel. Adjust the code according to the actual Fesod library's API.
        try (org.apache.fesod.sheet.ExcelWriter excelWriter =
                     org.apache.fesod.sheet.FesodSheet
                             .write(response.getOutputStream(), CustomerExportDTO.class)
                             .build()) {

            org.apache.fesod.sheet.write.metadata.WriteSheet writeSheet =
                    org.apache.fesod.sheet.FesodSheet
                            .writerSheet(CUSTOMERS_SHEET_NAME)
                            .build();

            processAllCustomers(customers -> {
                List<CustomerExportDTO> dtoList = new ArrayList<>();
                for (Customer customer : customers) {
                    dtoList.add(convertToDTO(customer));
                }
                excelWriter.write(dtoList, writeSheet);
            }, "Fesod");
        }

        long endTime = System.nanoTime();
        log.info("Fesod Export completed in {} ms", (endTime - startTime) / 1_000_000);
    }

    private void processAllCustomers(Consumer<List<Customer>> batchProcessor, String exportType) {
        int pageNumber = 0;
        Page<Customer> page;

        do {
            page = customerRepository.findAll(PageRequest.of(pageNumber, PAGE_SIZE));
            batchProcessor.accept(page.getContent());
            log.info("Processed page {} for {}", pageNumber, exportType);
            pageNumber++;
        } while (page.hasNext());
    }

    private void createHeader(Row row) {
        for (int i = 0; i < HEADERS.length; i++) {
            row.createCell(i).setCellValue(HEADERS[i]);
        }
    }

    private void populateRow(Row row, Customer customer) {
        row.createCell(0).setCellValue(customer.getId() != null ? customer.getId() : 0L);
        row.createCell(1).setCellValue(customer.getFirstName() != null ? customer.getFirstName() : "");
        row.createCell(2).setCellValue(customer.getLastName() != null ? customer.getLastName() : "");
        row.createCell(3).setCellValue(customer.getEmail() != null ? customer.getEmail() : "");
        row.createCell(4).setCellValue(customer.getPhone() != null ? customer.getPhone() : "");
        row.createCell(5).setCellValue(customer.getAddress() != null ? customer.getAddress() : "");
        row.createCell(6).setCellValue(customer.getCity() != null ? customer.getCity() : "");
        row.createCell(7).setCellValue(customer.getCountry() != null ? customer.getCountry() : "");
        row.createCell(8).setCellValue(customer.getJobTitle() != null ? customer.getJobTitle() : "");
        if (customer.getJoinDate() != null) {
            row.createCell(9).setCellValue(customer.getJoinDate().toString());
        } else {
            row.createCell(9).setCellValue("");
        }
    }

    private CustomerExportDTO convertToDTO(Customer customer) {
        CustomerExportDTO dto = new CustomerExportDTO();
        dto.setId(customer.getId());
        dto.setFirstName(customer.getFirstName());
        dto.setLastName(customer.getLastName());
        dto.setEmail(customer.getEmail());
        dto.setPhone(customer.getPhone());
        dto.setAddress(customer.getAddress());
        dto.setCity(customer.getCity());
        dto.setCountry(customer.getCountry());
        dto.setJobTitle(customer.getJobTitle());
        dto.setJoinDate(customer.getJoinDate());
        return dto;
    }

}
