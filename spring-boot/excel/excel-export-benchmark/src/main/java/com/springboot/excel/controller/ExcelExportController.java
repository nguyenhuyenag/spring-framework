package com.springboot.excel.controller;

import com.springboot.excel.service.ExcelExportService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/export")
@RequiredArgsConstructor
public class ExcelExportController {

    /*
        XSSF 		-> 119376 ms
        SXSSF 		-> 15855 ms
        EasyExcel 	-> 29824 ms
        Fesod 		-> 20608 ms
     */

    private final ExcelExportService excelExportService;

    @GetMapping("/xssf")
    public void exportXssf(HttpServletResponse response) throws IOException {
        setResponseHeaders(response, "data_xssf.xlsx");
        excelExportService.exportUsingXssf(response);
    }

    @GetMapping("/sxssf")
    public void exportSxssf(HttpServletResponse response) throws IOException {
        setResponseHeaders(response, "data_sxssf.xlsx");
        excelExportService.exportUsingSxssf(response);
    }

    @GetMapping("/easyexcel")
    public void exportEasyExcel(HttpServletResponse response) throws IOException {
        setResponseHeaders(response, "data_easyexcel.xlsx");
        excelExportService.exportUsingEasyExcel(response);
    }

    @GetMapping("/fesod")
    public void exportFesod(HttpServletResponse response) throws IOException {
        setResponseHeaders(response, "data_fesod.xlsx");
        excelExportService.exportUsingFesod(response);
    }

    private void setResponseHeaders(HttpServletResponse response, String filename) {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=" + filename);
    }
}
