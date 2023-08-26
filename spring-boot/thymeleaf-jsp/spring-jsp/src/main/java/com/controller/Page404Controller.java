package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


//public class Page404Controller implements ErrorController {
//
//	@RequestMapping("error")
//	public String handleError(HttpServletRequest request) {
//		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
//		if (status != null) {
//			int statusCode = Integer.parseInt(status.toString());
//			if (statusCode == HttpStatus.NOT_FOUND.value()) {
//				return "404";
//			} else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
//				return "error500";
//			}
//		}
//		return "error";
//	}
//
//	@Override
//	public String getErrorPath() {
//		return "error";
//	}
//
//}

// https://stackoverflow.com/questions/38777723/how-i-create-an-error-handler-404-500-in-spring-boot-mvc
@Controller
public class Page404Controller {
	
	@GetMapping("403")
    public String forbidden(Model model) {
        return "error/403";
    }

    @GetMapping("404")
    public String notFound(Model model) {
        return "error/404";
    }

    @GetMapping("500")
    public String internal(Model model) {
        return "error/500";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "error/access-denied";
    }
}
