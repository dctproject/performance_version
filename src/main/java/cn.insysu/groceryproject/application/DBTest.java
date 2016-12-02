package cn.insysu.groceryproject.application;

import cn.insysu.groceryproject.configuration.JPAConfig;
import cn.insysu.groceryproject.persistence.entity.Dish;
import cn.insysu.groceryproject.persistence.service.*;
import cn.insysu.groceryproject.test.CuisineTest;
import cn.insysu.groceryproject.test.DealTest;
import cn.insysu.groceryproject.test.UserTest;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.jws.soap.SOAPBinding;

import static java.lang.System.out;

/**
 * Created by Souler on 2016/11/30.
 */
public class DBTest {
    public static void main(String [] args) {

        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();

        annotationConfigApplicationContext.register(JPAConfig.class);

        annotationConfigApplicationContext.refresh();
        out.println("Start:"+ annotationConfigApplicationContext.getBean("entityManagerFactory").toString());
        out.println("Start:" + annotationConfigApplicationContext.getBean(LocalContainerEntityManagerFactoryBean.class).toString());

        UserService us = annotationConfigApplicationContext.getBean(UserService.class);
//        UserTest.ExecuteTest(us , 10);
//
        CuisineService cs = annotationConfigApplicationContext.getBean(CuisineService.class);
        DishService ds = annotationConfigApplicationContext.getBean(DishService.class);
//        CuisineTest.ExecuteTest(ds,cs,10);

        DealContentService dcs = annotationConfigApplicationContext.getBean(DealContentService.class);
        DealService es = annotationConfigApplicationContext.getBean(DealService.class);
        DealTest.ExecuteTest(us,cs,ds,dcs,es,10);

    }
}
