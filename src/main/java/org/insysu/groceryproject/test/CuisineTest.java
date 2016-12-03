package org.insysu.groceryproject.test;

import org.insysu.groceryproject.persistence.entity.Cuisine;
import org.insysu.groceryproject.persistence.entity.Dish;
import org.insysu.groceryproject.persistence.service.CuisineService;
import org.insysu.groceryproject.persistence.service.DishService;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

/**
 * Created by Souler on 2016/12/1.
 */
public class CuisineTest {
    private static ArrayList<Cuisine> cuisineTestList;
    private static ArrayList<Dish> dishTestList;

    private final static String dict = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final static int len = 26+26+10;


    private static String generateRandomString(int length) {
        StringBuffer sb = new StringBuffer();
        if (length == 0) length = 5;
        for (int i = 0 ; i < length ; i++) {
            sb.append(dict.charAt((int)Math.round(Math.random() * (len - 1))));
        }
        return sb.toString();
    }

    private static void testInitialization(final int amount) {
        out.println("-----------------------Testing Dish Initialization---------------------------");
        out.println("-----------------------Dish Amount:"+ amount + "-------------------------------------");
        if (dishTestList == null) {
            dishTestList = new ArrayList<Dish>();
        }
        if (cuisineTestList == null) {
            cuisineTestList = new ArrayList<Cuisine>();
        }

        String dname;
        double dprice;
        String ddescription;
        Dish tempdish;

        for (int i = 0 ; i < amount ; i++) {
            dname = generateRandomString((int) Math.round(Math.random() * 17));
            ddescription = generateRandomString((int) Math.round(Math.random() * 100));
            dprice = Math.random() * 100;
            tempdish = new Dish(dname,dprice,ddescription);
            dishTestList.add(tempdish);
        }
        out.println("-----------------------Finished Dish Initialization--------------------------");

        out.println("-----------------------Testing Cuisine Initialization---------------------------");
        out.println("-----------------------Cuisine Amount:"+ amount + "-------------------------------------");
        boolean isAvailable;
        boolean isVIP;
        int remainQuantity;
        Cuisine tempCuisine;

        for (int i = 0 ; i < amount ; i++) {
            isAvailable = (Math.random() > 0.5);
            isVIP = (Math.random() < 0.2);
            if (isAvailable) {
                remainQuantity = (int) Math.round(Math.random() * 1000);
            } else {
                remainQuantity = 0;
            }
            tempCuisine = new Cuisine(dishTestList.get(i) , isAvailable , remainQuantity , isVIP);
            cuisineTestList.add(tempCuisine);
        }
        out.println("-----------------------Finished Cuisine Initialization---------------------------");
    }


    private static void testCreate(DishService ds , CuisineService cs) {
        out.println("-----------------------Testing Database Creation---------------------------");
        for (Cuisine cuisine: cuisineTestList) {
            out.print("Creating " + cuisine.toString());
            ds.create(cuisine.getDish());
            cs.create(cuisine);
            out.println("......Done!");
        }
        out.println("-----------------------Finished Database Creation--------------------------");
    }


    private static void testFindAll(CuisineService cs) {
        out.println("-----------------------Testing ALL Element Existence---------------------------");
        List<Cuisine> dis = cs.findAll();
        out.println("Successfully get all elements");
        for (Cuisine c:dis) {
            out.println("Found in database: " + c.toString());
            if (cuisineTestList.indexOf(c) == -1) {
                cuisineTestList.add(c);
                if (dishTestList.indexOf(c.getDish()) == -1) {
                    dishTestList.add(c.getDish());
                }
            }
        }
        out.println("-----------------------Finished ALL Element Existence--------------------------");
    }

    private static void testFindOne(CuisineService cs , final int TestCases) {
        out.println("-----------------------Testing Single Element Existence---------------------------");
        if (cuisineTestList.size() == 0) return;
        out.println("-----------------------------Testing Case: " + TestCases + "----------------------------------");
        for (int i = 0 ; i < TestCases ; i++) {
            int pos = (int) Math.round(Math.random() * (cuisineTestList.size() - 1));
            out.print("Testing on id:" + cuisineTestList.get(pos).getCid());
            out.println("  : " + cs.findOne(cuisineTestList.get(pos).getCid()));
        }
        out.println("-----------------------Finished Single Element Existence--------------------------");
    }

    private static void testUpdate(CuisineService cs , final int testCases) {
        out.println("-----------------------Testing Single Element Updation---------------------------");
        out.println("-----------------------------Testing Case: " + testCases + "----------------------------------");
        if (cuisineTestList.size() == 0) return;
        for (int i = 0 ; i < testCases ; i++) {
            int pos = (int) Math.round(Math.random() * (cuisineTestList.size() - 1));
//            Modify value by setting to 1000
            if (cuisineTestList.get(pos).isAvailable() == false) cuisineTestList.get(pos).setAvailable(true);
            cuisineTestList.get(pos).setRemainQuantity(1000);
            out.print("Updating " + cuisineTestList.get(pos).toString());
            cs.update(cuisineTestList.get(pos));
            out.println("...Done!");
        }
        out.println("-----------------------Finished Single Element Updation--------------------------");
    }

    public static ArrayList<Cuisine> ExecuteTest(DishService ds , CuisineService cs , final int amount) {
        out.println("-----------------------------------------------------------------");
        out.println("-----------------------Testing On Cuisine------------------------");
        testInitialization(amount);
        testCreate(ds,cs);
        testFindAll(cs);
        testFindOne(cs , (int)Math.round(Math.random() * (cuisineTestList.size() - 1)));
        testUpdate(cs , (int)Math.round(Math.random() * (cuisineTestList.size() - 1)));
        out.println("-----------------------Finished On Cuisine-----------------------");
        out.println("-----------------------------------------------------------------");

        return cuisineTestList;
    }



}
