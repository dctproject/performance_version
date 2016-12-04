package org.insysu.groceryproject.web.Controller;

import org.insysu.groceryproject.persistence.service.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by buress on 12/4/16.
 */
@Controller
public class OrderController {
    public OrderController() {super();}
    @Autowired
    DealService dealService;
    @RequestMapping(path="/order/{id}")
    public String showOrderDetail(@PathVariable String id) {
        this.dealService.findOne(Long.parseLong(id));
        return "order";
    }
}
