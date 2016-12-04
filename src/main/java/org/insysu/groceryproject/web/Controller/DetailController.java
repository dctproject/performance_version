package org.insysu.groceryproject.web.Controller;

import org.insysu.groceryproject.persistence.entity.Cuisine;
import org.insysu.groceryproject.persistence.service.CuisineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by buress on 12/4/16.
 */
@Controller
public class DetailController {
    @Autowired
    private CuisineService cuisineService;

    public DetailController() {super();}
    @RequestMapping(path = "/cuisine_detail/{name}")
    public String showCuisineDetail(@PathVariable String name, Model model) {
        Cuisine cuisine = cuisineService.findOne(Long.parseLong(name));
        model.addAttribute(cuisine);
        return "detail";
    }
}
