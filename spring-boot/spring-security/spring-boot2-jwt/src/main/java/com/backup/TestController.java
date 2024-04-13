//package com.controller;
//
//public class TestController {
//	
//	@GetMapping("/authorities")
//    public Map<String,Object> getPrincipalInfo(JwtAuthenticationToken principal) {
//        
//        Collection<String> authorities = principal.getAuthorities()
//          .stream()
//          .map(GrantedAuthority::getAuthority)
//          .collect(Collectors.toList());
//        
//        Map<String,Object> info = new HashMap<>();
//        info.put("name", principal.getName());
//        info.put("authorities", authorities);
//        info.put("tokenAttributes", principal.getTokenAttributes());
//        
//        return info;
//    }
//	
//}
