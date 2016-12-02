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

    private static void testFindAll(DealService es) {
        out.println("-----------------------Testing ALL Element Existence---------------------------");
        List<Deal> deals = es.findAll();
        out.println("Successfully get all elements");
        for (Deal d:deals) {
            out.println("Found in database: " + d.toString());
            if (dealTestList.indexOf(d) == -1) {
                dealTestList.add(d);
//                if (d.getUser().getDeals().indexOf(d) == -1) {
//                    d.getUser().getDeals().add(d);
//                }
            }
        }
        out.println("-----------------------Finished ALL Element Existence--------------------------");
    }

    private static void testFindOne(DealService ds , final int TestCases) {
        out.println("-----------------------Testing Single Element Existence---------------------------");
        if (dealTestList.size() == 0) return;
        out.println("-----------------------------Testing Case: " + TestCases + "----------------------------------");
        for (int i = 0 ; i < TestCases ; i++) {
            int pos = (int) Math.round(Math.random() * (dealTestList.size() - 1));
            out.print("Testing on id:" + dealTestList.get(pos).getOid());
            out.println("  : " + ds.findOne(dealTestList.get(pos).getOid()));
        }
        out.println("-----------------------Finished Single Element Existence--------------------------");
    }

    private static void testUpdate(DealService es , final int testCases) {
        out.println("-----------------------Testing Single Element Updation---------------------------");
        out.println("-----------------------------Testing Case: " + testCases + "----------------------------------");
        if (dealTestList.size() == 0) return;
        for (int i = 0 ; i < testCases ; i++) {
            int pos = (int) Math.round(Math.random() * (dealTestList.size() - 1));
//            Modify value by setting to 1000
            if (dealTestList.get(pos).getState() == Deal.ERROR) dealTestList.get(pos).setState(Deal.UNPAID);
            dealTestList.get(pos).setState(Deal.SUCCEED);
            out.print("Updating " + dealTestList.get(pos).toString());
            es.update(dealTestList.get(pos));
            out.println("...Done!");
        }
        out.println("-----------------------Finished Single Element Updation--------------------------");
    }


    public static List<Deal> ExecuteTest(UserService us , CuisineService cs , DishService ds , DealContentService dcs , DealService es , int amount) {
        testInitialization(us,ds,cs,amount);
        testCreate(es,dcs);
        testFindAll(es);
        testFindOne(es,10);
        testUpdate(es,10);
        return dealTestList;
    }
}
