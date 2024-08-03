package com.example.springjwt.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Iterator;

@RestController
@ResponseBody
public class MainController {

    @GetMapping("/")
    public String mainP() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

//        if (authentication instanceof AnonymousAuthenticationToken) {
//            return "사용자가 익명입니다";
//        }

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
        GrantedAuthority auth = iter.hasNext() ? iter.next() : null;
        String role = auth != null ? auth.getAuthority() : "역할 없음";

        String name = authentication.getName();

        System.out.println(authentication);
        System.out.println("권한: " + authorities);

        return "메인 컨트롤러 : " + name + " " + role;
    }

}
