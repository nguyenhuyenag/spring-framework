package cors.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class ApiController {

    @Autowired
    private HttpServletRequest request;

    private final AtomicLong counter = new AtomicLong();

    @GetMapping("data")
    public Map<String, Object> greeting() {
        Map<String, Object> content = new LinkedHashMap<>();
        content.put("id", counter.incrementAndGet());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        var dateTime = LocalDateTime.now();
        String formattedDateTime = dateTime.format(formatter); // "1986-04-08 12:30"
        content.put("datetime", formattedDateTime);

        String clientIp = request.getHeader("X-Real-IP");
        if (clientIp == null || clientIp.isEmpty()) {
            clientIp = request.getRemoteAddr();
            content.put("clientId", clientIp);
        }
        // System.out.println("Request IP: " + clientIp);
        return content;
    }

}
