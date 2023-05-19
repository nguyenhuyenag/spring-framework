package com.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.payload.reponse.TokenRefreshResponse;
import com.service.RefreshTokenService;
import com.util.TokenHandler;

@Controller
@RequestMapping("auth")
public class AuthController {

	@Autowired
	private RefreshTokenService refreshTokenService;

	@Autowired
	HttpServletRequest req;

	public String url() {
		return req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort();
	}

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

	@GetMapping("validate-token")
	private ResponseEntity<?> checkToken(String token) {
		DecodedJWT validate = TokenHandler.verifyJWT(token);
		return ResponseEntity.ok(validate);
	}

	@PostMapping("refresh-token")
	public ResponseEntity<?> refreshtoken(String token) {
		TokenRefreshResponse refreshToken = refreshTokenService.refreshToken(token);
		return ResponseEntity.ok(refreshToken);
	}

}
