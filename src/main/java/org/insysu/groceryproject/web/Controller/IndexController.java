package org.insysu.groceryproject.web.Controller;

import org.insysu.groceryproject.persistence.entity.Dish;
import org.insysu.groceryproject.persistence.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by buress on 12/3/16.
 */
@Controller
public class IndexController {
    @Autowired
    private DishService dishService;

    @ModelAttribute("allDishes")
    public List<Dish> populateArea() {
        return this.dishService.findAll();
    }


    @RequestMapping({"/index"})
    public String showDefaultIndex() {
        return "index";
    }
}
