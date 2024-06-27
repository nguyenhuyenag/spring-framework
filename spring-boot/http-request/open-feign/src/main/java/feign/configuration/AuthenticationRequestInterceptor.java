package feign.configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
// @Component // Nếu đánh dấu đây là Component thì sẽ áp dụng cho global
public class AuthenticationRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        ServletRequestAttributes servletRequestAttributes
                = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        String authHeader = servletRequestAttributes.getRequest().getHeader(HttpHeaders.AUTHORIZATION);

        log.info("Header: {}", authHeader);
        if (StringUtils.hasText(authHeader)) {
            template.header(HttpHeaders.AUTHORIZATION, authHeader);
        }
    }

}
