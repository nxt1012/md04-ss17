package com.ra.controller;

import com.ra.model.entity.User;
import com.ra.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(HttpSession httpSession, Model model, @CookieValue(value = "email", required = false) String email) {
        User user = new User();

        if (email != null) {
            model.addAttribute("checkbox", true);
            user.setEmail(email);
        }
        model.addAttribute("user", user);


        return "user/login";
    }

    @PostMapping("/login")
    public String processLogin(@ModelAttribute("user") User user, HttpSession httpSession, RedirectAttributes redirectAttributes, HttpServletResponse response, @RequestParam(value = "checkbox", required = false) boolean isChecked) {

        if (userService.login(user)) {
            httpSession.setAttribute("email", user.getEmail());
            System.out.println("Đăng nhập thành công");

            //create cookie of email & sessionID
            if (isChecked) {
                Cookie emailCookie = new Cookie("email", user.getEmail());
                emailCookie.setMaxAge(24*60*60);
                response.addCookie(emailCookie);

            } else {
                Cookie emptyCookie = new Cookie("email", null);
                emptyCookie.setMaxAge(0);
                response.addCookie(emptyCookie);
            }

            String sessionID = httpSession.getId();
            Cookie sessionIDCookie = new Cookie("sessionID", sessionID);
            response.addCookie(sessionIDCookie);

            return "redirect:/";
        }
        redirectAttributes.addFlashAttribute("err", "Sai thông tin đăng nhập");
        System.out.println("Đăng nhập thất bại");
        return "redirect:user/login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user/register";
    }

    @PostMapping("/register")
    public String processRegister(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
        if (userService.save(user)) {
            System.out.println("Đăng ký thành công");
        }
        return "redirect:/user/login";
    }
}