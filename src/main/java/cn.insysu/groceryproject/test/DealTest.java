package cn.insysu.groceryproject.test;

import cn.insysu.groceryproject.persistence.entity.*;
import cn.insysu.groceryproject.persistence.service.*;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

/**
 * Created by Souler on 2016/12/2.
 */
public class DealTest {
    private static ArrayList<User> userTestList;
    private static ArrayList<Cuisine> cuisineTestList;
    private static ArrayList<Dish> dishTestList;
    private static ArrayList<DealContent> dealContentTestList;
    private static ArrayList<Deal> dealTestList;

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

    private static int stateGenerator() {
        return (int) Math.round(Math.random() * 8);
    }

    private static long timeGenerator() {
        return Math.round(Math.random() * Long.MAX_VALUE);
    }

    private static int quantityGenerator() {
        return (int) Math.round(Math.random() * 10);
    }

    private static void userGenerator(UserService us) {
        out.println("+++++++++++++++++++++++++++++++++++Generating Users for Test++++++++++++++++++++++++++++++++++++++");
        userTestList  = UserTest.ExecuteTest(us , 20);
        out.println("+++++++++++++++++++++++++++++++++++Finishedgen Users for Test+++++++++++++++++++++++++++++++++++++");
        out.println();
        out.println();
        out.println();
    }

    private static void cuisineGenerator(CuisineService cs , DishService ds) {
        out.println("+++++++++++++++++++++++++++++++++Generating Cuisine for Test+++++++++++++++++++++++++++++++++++++");
        cuisineTestList = CuisineTest.ExecuteTest(ds,cs,20);
        out.println("+++++++++++++++++++++++++++++++++FinishedGen Cuisine for Test++++++++++++++++++++++++++++++++++++");
        out.println();
        out.println();
        out.println();
    }

    private static void testInitialization(UserService us , DishService ds , CuisineService cs , int amount) {
        userGenerator(us);
        cuisineGenerator(cs,ds);
        out.println("-----------------------Testing Deal Initialization---------------------------");
        out.println("-----------------------Deal Amount:"+ amount + "-------------------------------------");
        if (dealContentTestList == null) {
            dealContentTestList = new ArrayList<DealContent>();
        }
        if (dealTestList == null) {
            dealTestList = new ArrayList<Deal>();
        }

        int state;
        long time;
        User tempUser;
        Deal tempDeal;
        for (int i = 0 ; i < amount ; i++) {
            state = stateGenerator();
            time = timeGenerator();
            tempUser = userTestList.get((int)Math.round(Math.random() * (userTestList.size() - 1)));
            tempDeal = new Deal(time , state , tempUser);
//            tempUser.getDeals().add(tempDeal);
            dealTestList.add(tempDeal);
        }
        out.println("-----------------------Finished Deal Initialization--------------------------");
        out.println();

        out.println("----------------------Testing DealContent Initialization------------------------");
        out.println("-----------------------DealContent Amount:"+ amount * 5 + "-------------------------------------");
        int quantity;
        Cuisine tempCuisine;
        Deal tempDealer;
        DealContent tempDealContent;
        for (int i = 0 ; i < amount ; i++) {
            tempDealer = dealTestList.get((int)Math.round(Math.random() * (dealTestList.size() - 1)));
            for (int ii = 0 ; ii < 5 ; ii++) {
                quantity = quantityGenerator();
                tempCuisine = cuisineTestList.get((int)Math.round(Math.random() * (cuisineTestList.size() - 1)));
                tempDealContent = new DealContent(quantity , tempDealer , tempCuisine);
                dealContentTestList.add(tempDealContent);
//                tempCuisine.getOrdercontents().add(tempDealContent);
//                tempDealer.getOrdercontents().add(tempDealContent);
            }
        }
        out.println("-----------------------Finished DealContent Initialization--------------------------");
        out.println();
        out.println();
    }

    private static void testCreate(DealService es , DealContentService dcs) {
        out.println("-----------------------Testing Database Creation---------------------------");
        for (Deal deal: dealTestList) {
            out.print("Creating " + deal.toString());
            es.create(deal);
            out.println("......Done!");
        }
        for (DealContent dealContent: dealContentTestList) {
            out.print("Creating " + dealContent.toString());
            dcs.create(dealContent);
            out.println("......Done!");
        }
        out.println("-----------------------Finished Database Creation--------------------------");
    }

    public static List<Deal> ExecuteTest(UserService us , CuisineService cs , DishService ds , DealContentService dcs , DealService es , int amount) {
        testInitialization(us,ds,cs,amount);
        testCreate(es,dcs);
        return dealTestList;
    }
}
