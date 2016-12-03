package org.insysu.groceryproject.web.Controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.insysu.groceryproject.persistence.entity.Deal;
import org.insysu.groceryproject.persistence.entity.UserForm;
import org.insysu.groceryproject.persistence.service.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by buress on 12/3/16.
 */
@Controller
public class LoginController {
    @Autowired
    DealService dealService;
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLogin(final UserForm userForm) {
        return "login";
    }
    @RequestMapping(value = "/logout")
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "redirect:/index";
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(final UserForm userForm) {
        List<Deal> list =  this.dealService.findAll();
        UsernamePasswordToken token = new UsernamePasswordToken(userForm.getUsername(), userForm.getPassword());
        token.setRememberMe(userForm.getRememberMe());
        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.login(token);
        } catch (Exception e) {
            e.getMessage();
            return "login";
        }
        return "redirect:/index";
    }
}
