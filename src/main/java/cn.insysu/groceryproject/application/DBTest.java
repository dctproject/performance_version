package cn.insysu.groceryproject.application;

import cn.insysu.groceryproject.configuration.JPAConfig;
import cn.insysu.groceryproject.persistence.entity.Dish;
import cn.insysu.groceryproject.persistence.service.CuisineService;
import cn.insysu.groceryproject.persistence.service.DishService;
import cn.insysu.groceryproject.persistence.service.UserService;
import cn.insysu.groceryproject.test.CuisineTest;
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
        out.println("Exec1");
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        out.println("Exec1");
        annotationConfigApplicationContext.register(JPAConfig.class);
        out.println("Exec1");
        annotationConfigApplicationContext.refresh();
        out.println("Start:"+ annotationConfigApplicationContext.getBean("entityManagerFactory").toString());
        out.println("Start:" + annotationConfigApplicationContext.getBean(LocalContainerEntityManagerFactoryBean.class).toString());

//        UserService us = annotationConfigApplicationContext.getBean(UserService.class);
//        UserTest.ExecuteTest(us , 10);

        CuisineService cs = annotationConfigApplicationContext.getBean(CuisineService.class);
        DishService ds = annotationConfigApplicationContext.getBean(DishService.class);
        CuisineTest.ExecuteTest(ds,cs,10);
    }
}
