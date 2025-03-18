package com.auth;

import com.entity.History;
import com.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/*
    Spring Security chỉ sử dụng một cơ chế xử lý thành công khi đăng nhập. Nếu đã thiết
    lập .successHandler(), thì .defaultSuccessUrl() sẽ bị bỏ qua và ngược lại.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CustomLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final int TOKEN_EXPIRED_TIME = 30; // Đặt thời gian là 30 phút
    private final HistoryRepository historyRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        log.info("onAuthenticationSuccess: {}", userDetails.getUsername());
        History history = History.builder()
                .tokenId(UUID.randomUUID().toString())
                .username(userDetails.getUsername())
                .loginAt(LocalDateTime.now())
                .expiredTime(Instant.now().plus(TOKEN_EXPIRED_TIME, TimeUnit.MINUTES.toChronoUnit()).toEpochMilli())
                .build();
        History save = historyRepository.save(history);
        log.info("Save history={}", save.getTokenId());
        super.onAuthenticationSuccess(request, response, authentication);
    }

}
