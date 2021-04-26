package org.freecode.demo.controller;

import org.freecode.demo.model.User;
import org.freecode.demo.service.EmailService;
import org.freecode.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;
import java.util.UUID;

@Controller
public class RegistrationController {
    @Autowired
    private BCryptPasswordEncoder bcPassEnc;
    @Autowired
    private UserService userSvc;
    @Autowired
    private EmailService emailSvc;

    @GetMapping("/register")
    public ModelAndView showRegistrationPage(ModelAndView mv, User user) {
        mv.addObject("user", user);
        mv.setViewName("register");

        return mv;
    }

    @PostMapping("/register")
    public ModelAndView processRegistrationForm(ModelAndView mv, @Valid User user, BindingResult bindReult, HttpServletRequest req) {
        User existed = userSvc.findByEmail(user.getEmail());

        if (existed != null) {
            mv.addObject("alreadyRegisteredMsg", "There is already a user with the same email provided.");
            mv.setViewName("register");
            bindReult.reject("email");
        }

        if (bindReult.hasErrors()) {
            mv.setViewName("register");
        } else {
            // register a new user
            String token = UUID.randomUUID().toString();
            userSvc.saveUser(user);
            String appUrl = req.getScheme() + "://" + req.getServerName() + ":8080";
            String msg = "To set your password, please click on the link below:\n" + appUrl + "/confirm?token=" + token;
            emailSvc.sendEmail(user.getEmail(), "Reset your password", msg);

            mv.addObject("confirmationMsg", "A password reset e-mail has been sent to " + user.getEmail());
            mv.setViewName("register");
        }

        return mv;
    }

    @GetMapping("/confirm")
    public ModelAndView confirmRegistration(ModelAndView mv, @RequestParam("token") String token) {
        User user = userSvc.findByEmail(token); // Todo: implement findByConfirmationToken

        if (user == null) {
            mv.addObject("invalidToken", "Invalid confirmation link.");
        } else {
            mv.addObject("confirmationToken", user.getEmail()); // Todo change to getConfirmationToken()
        }

        mv.setViewName("confirm");

        return mv;
    }

    @PostMapping("/confirm")
    public ModelAndView confirmRegistration(ModelAndView mv, BindingResult bindResult, @RequestParam Map<String, String> reqParams, RedirectAttributes redirectAttrs) {
        User user = userSvc.findByEmail(reqParams.get("token")); // Todo change to findByConfirmationToken()
        user.setPasswd(bcPassEnc.encode(reqParams.get("password")));
        // enable user
        userSvc.saveUser(user);

        mv.setViewName("confirm");
        mv.addObject("successMsg", "Password set okay.");

        return mv;
    }
}
