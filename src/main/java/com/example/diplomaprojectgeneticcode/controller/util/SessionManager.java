package com.example.diplomaprojectgeneticcode.controller.util;

import com.example.diplomaprojectgeneticcode.dto.CourseDTO;

import javax.servlet.http.HttpSession;
import java.util.LinkedHashSet;
import java.util.Set;

import static com.example.diplomaprojectgeneticcode.util.Constant.CART_SESSION;
import static com.example.diplomaprojectgeneticcode.util.Constant.USER_SESSION;

public class SessionManager {

    private SessionManager() {
    }

    public static Set<CourseDTO> getCartSession(HttpSession session) {
        Set<CourseDTO> cart = (Set<CourseDTO>) session.getAttribute(CART_SESSION);
        if (cart == null) {
            cart = new LinkedHashSet<>();
            session.setAttribute(CART_SESSION, cart);
            session.setMaxInactiveInterval(30 * 24 * 60 * 60);
        }
        return cart;
    }

    public static void destroyCartSession(HttpSession session) {
        session.setAttribute(CART_SESSION, null);
    }

    public static String getUserSession(HttpSession session) {
        return (String) session.getAttribute(USER_SESSION);
    }

}
