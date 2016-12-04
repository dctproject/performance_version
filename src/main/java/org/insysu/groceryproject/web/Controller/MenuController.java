package org.insysu.groceryproject.web.Controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.insysu.groceryproject.persistence.entity.Cuisine;
import org.insysu.groceryproject.persistence.entity.Deal;
import org.insysu.groceryproject.persistence.entity.DealContent;
import org.insysu.groceryproject.persistence.entity.User;
import org.insysu.groceryproject.persistence.service.CuisineService;
import org.insysu.groceryproject.persistence.service.DealContentService;
import org.insysu.groceryproject.persistence.service.DealService;
import org.insysu.groceryproject.persistence.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by buress on 12/4/16.
 */
@Controller
public class MenuController {
    @Autowired
    private CuisineService cuisineService;
    @Autowired
    private DealService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private DealContentService rowService;
    @ModelAttribute("allCuisines")
    public List<Cuisine> populateCuisineNames() {return this.cuisineService.findAll();}

    @RequestMapping(value="/menu")
    public String showMenu(Model model,final HttpServletRequest req) {
        Subject currentUser = SecurityUtils.getSubject();
        HttpSession session = req.getSession();
//        Session session = currentUser.getSession();
        String id = (String) session.getAttribute("order");
        if(null!= id) {
            model.addAttribute("deal", this.orderService.findOne(Long.parseLong(id)));
        } else {
            User user = this.userService.findByName((String) currentUser.getPrincipal());
            Deal deal = new Deal(System.currentTimeMillis(), user);
            this.orderService.create(deal);
            model.addAttribute(deal);
            session.setAttribute("order", Long.toString(deal.getOid()));
        }
        return "menu";
    }

    @RequestMapping(value="/menu", params={"select-cuisine"})
    public String addRow(final HttpServletRequest req, Model model, final HttpServletResponse res) {
        Subject currentUser = SecurityUtils.getSubject();
        HttpSession session = req.getSession();
        String id = (String) session.getAttribute("order");
        Deal deal = this.orderService.findOne(Long.parseLong(id));
        DealContent dealContent = new DealContent(Integer.parseInt(req.getParameter("quantity")),deal,this.cuisineService.findOne(Long.parseLong(req.getParameter("select-cuisine"))));
        this.rowService.create(dealContent);
        deal.getOrdercontents().add(dealContent);
        model.addAttribute(deal);
        return "menu";
    }
    @RequestMapping(value="/menu", params={"remove-cuisine"})
    public String removeRow(final HttpServletRequest req, Model model) {

        Subject currentUser = SecurityUtils.getSubject();
//        Session session = currentUser.getSession();
        HttpSession session = req.getSession();
        String id = (String) session.getAttribute("order");
        Deal deal = this.orderService.findOne(Long.parseLong(id));
        DealContent row =  deal.getOrdercontents().remove(Integer.parseInt(req.getParameter("remove-cuisine")));
        this.rowService.deleteById(row.getOcid());
        model.addAttribute(deal);
        return "menu";
    }

    @RequestMapping(value="/menu", params={"save"})
    public String saveSeedstarter(final HttpServletRequest req) {
        Subject currentUser = SecurityUtils.getSubject();
//        Session session = currentUser.getSession();
        HttpSession session = req.getSession();
        String id = (String) session.getAttribute("order");
        session.removeAttribute("order");
        return "redirect:/order/" + id;
    }
}
