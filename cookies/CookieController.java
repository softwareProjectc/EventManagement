package com.software.eventmanagement.cookies;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
public class CookieController {

    public static void setUserCookie(HttpServletResponse response, String username) {
        String token = "username:" + username; // deadline affecting security measures but fine for development
        Cookie cookie = new Cookie("userAuthenticationToken", token);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24);
        response.addCookie(cookie);
    }
    public static void setStudentCookie(HttpServletResponse response, String username) {
        String token = "username:" + username; // deadline affecting security measures but fine for development
        Cookie cookie = new Cookie("studentAuthenticationToken", token);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24);
        response.addCookie(cookie);
    }
    public static String getUsernameFromCookie(String token) {
        return token.substring(9);
    }
}
