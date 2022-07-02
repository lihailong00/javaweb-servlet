package pers.lee.service;

import pers.lee.dao.OrderDao;
import pers.lee.entity.Orders;

import java.util.List;
import java.util.Map;

public class OrderService {
    public static List<Orders> getOrdersByuId(int uId) {
        return OrderDao.getOrdersByuId(uId);
    }
    public static void delOrder(int oId) {
        OrderDao.delOrder(oId);
    }
}
