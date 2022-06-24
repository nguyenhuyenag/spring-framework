package com.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class JWTController {
	
//	@GetMapping("authorities")
//    public Map<String,Object> getPrincipalInfo(JaasAuthenticationToken principal) {
//        
//        Collection<String> authorities = principal.getAuthorities()
//          .stream()
//          .map(GrantedAuthority::getAuthority)
//          .collect(Collectors.toList());
//        
//        Map<String,Object> info = new HashMap<>();
//        info.put("name", principal.getName());
//        info.put("authorities", authorities);
//        //info.put("tokenAttributes", principal.getTokenAttributes());
//        
//        return info;
//    }
	
}
