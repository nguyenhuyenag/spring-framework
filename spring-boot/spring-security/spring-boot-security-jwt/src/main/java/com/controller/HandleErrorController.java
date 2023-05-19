//package com.controller;
//
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.boot.web.servlet.error.ErrorController;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//public class HandleErrorController implements ErrorController {
//
//	@Override
//	public String getErrorPath() {
//		return "error";
//	}
//
//	@RequestMapping("error")
//	public String handleError(Model model, HttpServletRequest req) {
//		Object status = req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
//		if (status != null) {
//			Integer statusCode = Integer.valueOf(status.toString());
//			model.addAttribute("statusCode", statusCode);
//		}
//		try {
//			String hostAddress = InetAddress.getLocalHost().getHostAddress(); // 192.168.11.55
//			model.addAttribute("hostAddress", hostAddress);
//		} catch (UnknownHostException e) {
//			e.printStackTrace();
//		}
//		return "error";
//	}
//
//}
