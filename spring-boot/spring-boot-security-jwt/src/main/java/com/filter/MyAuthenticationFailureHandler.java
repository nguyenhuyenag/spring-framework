//package com.filter;
//
//import java.io.IOException;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//
//import com.reponse.ErrorResponse;
//import com.util.JsonUtils;
//
//public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
//
//	@Override
//	public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse res,
//			AuthenticationException exception) throws IOException, ServletException {
//
//		res.setStatus(401);
//		res.setContentType("application/json;charset=UTF-8");
//
//		ErrorResponse error = new ErrorResponse();
//		error.setStatus(401);
//		error.setError("Unauthorized");
//		error.setMessage("From MyAuthenticationFailureHandler");
//		error.setPath(req.getRequestURI());
//
//		String json = JsonUtils.toJSON(error);
//		res.getWriter().write(json);
//
//	}
//
//}
