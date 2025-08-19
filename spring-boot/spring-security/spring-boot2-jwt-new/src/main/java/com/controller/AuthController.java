package com.controller;

import com.payload.request.ValidateTokenRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.payload.response.TokenRefreshResponse;
import com.service.RefreshTokenService;
import com.util.TokenHandler;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    // @Autowired
    private final RefreshTokenService refreshTokenService;

//	@Autowired
//	HttpServletRequest req;

//	public String url() {
//		return req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort();
//	}

//	@PostMapping("/signin")
//	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
//
//		Authentication authentication = authenticationManager.authenticate(
//				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
//
//		SecurityContextHolder.getContext().setAuthentication(authentication);
//
//		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//
//		String jwt = jwtUtils.generateJwtToken(userDetails);
//
//		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
//				.collect(Collectors.toList());
//
//		RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());
//
//		return ResponseEntity.ok(new JwtResponse(jwt, refreshToken.getToken(), userDetails.getId(),
//				userDetails.getUsername(), userDetails.getEmail(), roles));
//	}

//	@PostMapping("login-handle")
//	private ResponseEntity<?> login(@RequestBody(required = false) LoginRequest login) throws IOException {
//		ErrorResponse error = new ErrorResponse();
//		if (login == null) {
//			error.setError("Required request body is missing");
//			error.setMessage("");
//			error.setPath(req.getRequestURI());
//			return ResponseEntity.ok(error);
//		}
//		User user = userService.findByUsername(login.getUsername());
//		if (user != null) {
//			Date timeDisabled = user.getTimeLoginDisabled();
//			if (timeDisabled != null && timeDisabled.after(new Date())) {
//				// khoa tai khoan
//				// error.setStatus(401);
//				error.setError("Unauthorized");
//				error.setMessage("Your account is disabled!");
//				error.setPath(req.getRequestURI());
//				return ResponseEntity.ok(error);
//			} else {
//				user.setLoginDisabled(0);
//				userService.save(user);
//			}
//		}
//		HttpPost httpPost = new HttpPost(url() + "/auth/login");
//		StringEntity entity = new StringEntity(JsonUtils.toJSON(login));
//		httpPost.setEntity(entity);
//		try (CloseableHttpClient client = HttpClients.createDefault()) {
//			HttpResponse response = client.execute(httpPost);
//			String content = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
//			if (content.contains("token")) {
//				JsonNode jsonNode = JsonUtils.toJsonNode(content);
//				return ResponseEntity.status(HttpStatus.OK).body(jsonNode);
//			}
//			return new ResponseEntity<String>(content, HttpStatus.OK);
//		}
//	}

    @PostMapping("/validate-token")
    private ResponseEntity<?> validateToken(@RequestBody ValidateTokenRequest request) {
        // Map<String, Boolean> validate = TokenHandler.validateToken(request);
        boolean validateToken = TokenHandler.validateJwt(request.getToken());
        Map<String, Boolean> result = new HashMap<>();
        result.put("validate", validateToken);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(String token) {
        TokenRefreshResponse refreshToken = refreshTokenService.refreshToken(token);
        return ResponseEntity.ok(refreshToken);
    }

}
