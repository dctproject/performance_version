package cn.insysu.groceryproject.test;

import cn.insysu.groceryproject.persistence.entity.User;
import cn.insysu.groceryproject.persistence.service.UserService;
import org.aspectj.weaver.ast.Test;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

/**
 * Created by Souler on 2016/12/1.
 */
public class UserTest {

    private static ArrayList<User> userTestList;
    private final static String dict = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final static int len = 26+26+10;

    private static String generateRandomString(int length) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0 ; i < length ; i++) {
            sb.append(dict.charAt((int)Math.round(Math.random() * (len - 1))));
        }
        return sb.toString();
    }

    public static void testInitialize(final int amount) {
        out.println("-----------------------Testing User Initialization---------------------------");
        out.println("-----------------------User Amount:"+ amount + "-------------------------------------");
        if (userTestList == null) {
            userTestList = new ArrayList<User>();
        }

        String username;
        String password;
        boolean isVIP;
        User tempuser;

        for (int i = 0 ; i < amount ; i++) {
            username = generateRandomString((int) Math.round(Math.random() * 17));
            password = generateRandomString((int) Math.round(Math.random() * 8));
            isVIP = (Math.random() > 0.5);
            tempuser = new User(username,password,isVIP);
            userTestList.add(tempuser);
        }
        out.println("-----------------------Finished User Initialization--------------------------");
    }

    private static void testCreate(UserService us) {
        out.println("-----------------------Testing Database Creation---------------------------");
        for (User user: userTestList) {
            out.print("Creating " + user.toString());
            us.create(user);
            out.println("......Done!");
        }
        out.println("-----------------------Finished Database Creation--------------------------");
    }

    private static void testFindAll(UserService us) {
        out.println("-----------------------Testing ALL Element Existence---------------------------");
        for (User u:us.findAll()) {
            out.println("Found in database: " + u.toString());
            if (userTestList.indexOf(u) == -1) {
                userTestList.add(u);
            }
        }
        out.println("-----------------------Finished ALL Element Existence--------------------------");
    }

    private static void testFindOne(UserService us , final int TestCases) {
        out.println("-----------------------Testing Single Element Existence---------------------------");
        if (userTestList.size() == 0) return;
        out.println("-----------------------------Testing Case: " + TestCases + "----------------------------------");
        for (int i = 0 ; i < TestCases ; i++) {
            int pos = (int) Math.round(Math.random() * (userTestList.size() - 1));
            out.print("Testing on id:" + userTestList.get(pos).getUid());
            out.println("  : " + us.findOne(userTestList.get(pos).getUid()));
        }
        out.println("-----------------------Finished Single Element Existence--------------------------");
    }

    private static void testRemoveUser(UserService us , final int testCases) {
        out.println("-----------------------Testing Single Element Removal---------------------------");
        for (int i =0 ; i < testCases / 2 ; i++) {
            int pos = (int) Math.round(Math.random() * (userTestList.size() - 1));
            out.print("Removing User [" + userTestList.get(pos).getUsername() + "] by Instance Reference");
            us.delete(userTestList.get(pos));
            userTestList.remove(pos);
            out.println("...Done");
        }
        for (int i = testCases / 2 ; i < testCases ; i++) {
            int pos = (int) Math.round(Math.random() * (userTestList.size() - 1));
            out.print("Removing User [" + userTestList.get(pos).getUsername() + "] by Id ");
            us.deleteById(userTestList.get(pos).getUid());
            userTestList.remove(pos);
            out.println("...Done");
        }

        out.println("-----------------------Finished Single Element Removal---------------------------");
    }

    public static void testFindByName(UserService us , int testCases) {
        out.println("-----------------------Testing Find By Name---------------------------");
        if (userTestList.size() == 0) return;
        out.println("-----------------------------Testing Case: " + testCases + "----------------------------------");
        for (int i = 0 ; i < testCases ; i++) {
            int pos = (int) Math.round(Math.random() * (userTestList.size() - 1));
            out.print("Testing on name:" + userTestList.get(pos).getUsername());
            User hisname = us.findByName(userTestList.get(pos).getUsername());
            if (hisname == null) {
                out.println("  : " + "NOT FOUND!");
            } else {
                out.println("  : " + hisname.toString());
            }
        }
        out.println("-----------------------Finished Find By Name--------------------------");

    }

    public static ArrayList<User> ExecuteTest(UserService us , final int amount) {
        out.println("-----------------------------------------------------------------");
        out.println("-----------------------Testing On User---------------------------");
        testInitialize(amount);
        testCreate(us);
        testFindAll(us);
        testFindOne(us , (int)Math.round(Math.random() * amount));
        testFindByName(us , 3);
//        testRemoveUser(us , 10);
        out.println("-----------------------Finished On User--------------------------");
        out.println("-----------------------------------------------------------------");
        out.println("");
        out.println("");
        out.println("");
        return userTestList;


    }
}
