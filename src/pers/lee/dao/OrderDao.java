package pers.lee.dao;

import pers.lee.entity.Food;
import pers.lee.entity.Orders;
import pers.lee.utils.DBUtil;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class OrderDao {
    public static List<Map<String, Object>> getFoodByfId(int fId) {
        List<Map<String, Object>> foods = null;
        Map<String, Object> whereMap = new HashMap<>();
        whereMap.put("fId", fId);
        try {
            foods = DBUtil.query("food", whereMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return foods;
    }

    public static List<Orders> getOrdersByuId(int uId) {
        List<Map<String, Object>> listOrders = null;
        Map<String, Object> whereMap = new HashMap<>();
        whereMap.put("uId", uId);
        try {
            listOrders = DBUtil.query("orders", whereMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Orders> orders = new ArrayList<>();
        for (Map<String, Object> order : listOrders) {
            System.out.println(order);


            // 获取订单信息，并封装到Order类中

            // localDateTime 转 String
            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
            String oTime = ((LocalDateTime) order.get("oTime")).format(formatter);
            int oId = (int) order.get("oId");
            String oItems = (String) order.get("oItems");
            System.out.println("oTime=" + oTime);
            Orders odr = new Orders();
            odr.setoId(oId);
            odr.setuId(uId);
            odr.setoTime(oTime);


            // 分割字符串oItems，获取各种食物
            // 将各种food拼接成一个字符串
            double totalPrice = 0;
            StringBuilder nameArr = new StringBuilder();
            for (int i = 0; i < oItems.length(); i++) {
                int j = i;
                while (j < oItems.length() && oItems.charAt(j) != ',') {
                    j++;
                }
                int t = Integer.parseInt(oItems.substring(i, j));
                i = j;
                if (t > 173) {  //debug
                    t = 150;
                }
                List<Map<String, Object>> food = getFoodByfId(t);
                if (food != null) {
                    System.out.println(food);
                    int fId = (int) food.get(0).get("fId");
                    String fName = (String) food.get(0).get("fName");
                    double fPrice = Double.parseDouble(food.get(0).get("fPrice").toString());
                    String fType = (String) food.get(0).get("fType");
                    totalPrice += fPrice;
                    nameArr.append("," + fName);
                }
            }
            nameArr = new StringBuilder(nameArr.substring(1));
            odr.setTotalPrice(totalPrice);
            odr.setoItems(nameArr);
            orders.add(odr);
        }
//        for (Orders o : orders) {
//            System.out.println("开始循环！！！");
//            System.out.println(o.getoId());
//            System.out.println(o.getuId());
//            System.out.println(o.getoItems());
//            System.out.println(o.getoTime());
//            System.out.println(o.getTotalPrice());
//        }
        return orders;
    }

    public static void delOrder(int oId) {
        Map<String, Object> whereMap = new HashMap<>();
        whereMap.put("oId", oId);
        try {
            int count = DBUtil.delete("orders", whereMap);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
