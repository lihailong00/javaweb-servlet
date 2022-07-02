package pers.lee.controller;

import pers.lee.entity.Orders;
import pers.lee.entity.User;
import pers.lee.service.OrderService;
import pers.lee.utils.Constant;
import pers.lee.utils.Variable;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@WebServlet(name = "OrderController", value = "/OrderController")
public class OrderController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         *  1. 各种决策由option变量接受，请求名和函数名相同
         *  2. 个人订单信息存放在orders(session)
         *  3. Variable.ack记录跳转信息
         *  4. 通过请求跳转到server，通过重定向跳转到client
         *  5. 传送到jsp的数据都是List<Map<String, Object>>类型
         *  6. 删除订单->delOrder
         *  7. 删除订单的成功失败检验消息为orderFailure(session)
         *  8. oItems必须是"123.123.12.3.2.1315.3"这样的形式
         *  9. 未撤销or已确认撤销的course放在cancelledOrders(session)(List<Orders>)中
        * */
        Variable.ack = true;
        String option = request.getParameter("option");
        System.out.println(option);
        if (option.equals("getOrdersByuId")) {
            getOrdersByuId(request, response);
            return;
        }
        if (option.equals("撤销订单") || option.equals("删除订单")) {
            checkOrder(request, response, option);
            return;
        }
        if (option.equals("确认删除")) {
            delOrder(request, response);
            return;
        }
        if (option.equals("确认撤销")) {
            cancelOrder(request, response);
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public static void getOrdersByuId(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute(Constant.USER_SESSION);
        int uId = user.getuId();
        List<Orders> orders = OrderService.getOrdersByuId(uId);
        request.getSession().setAttribute("orders", orders);
        System.out.println(orders);
        try {
            response.sendRedirect("jsp/allOrder.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void checkOrder(HttpServletRequest request, HttpServletResponse response, String option) {
        // 获取选中的条例
        List<Orders> orders = new ArrayList<>();
        String[] arr = request.getParameterValues("foodIds");
        if (arr != null) {
            for (String s : arr) {
                int cnt = 0;
                Orders order = new Orders();
                for (int low = 0, hi = 0; hi < s.length(); hi++) {
                    if (s.charAt(hi) == '+') {
                        String word = s.substring(low, hi);
                        if (cnt == 0) {
                            order.setoId(Integer.parseInt(word));
                        }
                        else if (cnt == 1) {
                            order.setoTime(word);
                        }
                        else if (cnt == 2) {
                            order.setoItems(new StringBuilder(word));
                        }
                        else if (cnt == 3) {
                            order.setTotalPrice(Double.parseDouble(word));
                        }
                        low = hi + 1;
                        cnt++;
                    }
                }
                orders.add(order);
            }
        }
        try {
            request.getSession().setAttribute("checkedOrders", orders);
            if (option.equals("撤销订单")) {
                response.sendRedirect("jsp/cancelOrder.jsp");
            }
            else if (option.equals("删除订单")) {
                response.sendRedirect("jsp/delOrder.jsp");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void delOrder(HttpServletRequest request, HttpServletResponse response) {
        List<Orders> orders = (List<Orders>) request.getSession().getAttribute("checkedOrders");
        int delCount = 0;
        for (Orders order : orders) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = null;
            Date now = new Date();
            String tempTime = order.getoTime();
            int idx = 0;
            while (tempTime.charAt(idx) != 'T') {
                idx++;
            }
            String oTime = tempTime.substring(0, idx) + " " + tempTime.substring(idx + 1);
            try {
                date = format.parse(oTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            long timeDiff = 0;
            if (date != null) {
                timeDiff = (now.getTime() - date.getTime()) / 1000;
            }
            if (timeDiff >= 86400) {
                delCount++;
                OrderService.delOrder(order.getoId());
            }
        }
        // 未被删除的订单总数
        request.getSession().setAttribute("orderFailure", orders.size() - delCount);
        try {
            response.sendRedirect("jsp/orderFailure.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void cancelOrder(HttpServletRequest request, HttpServletResponse response) {
        List<Orders> orders = (List<Orders>) request.getSession().getAttribute("checkedOrders");
        int delCount = 0;
        for (Orders order : orders) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = null;
            Date now = new Date();
            String tempTime = order.getoTime();
            int idx = 0;
            while (tempTime.charAt(idx) != 'T') {
                idx++;
            }
            String oTime = tempTime.substring(0, idx) + " " + tempTime.substring(idx + 1);
            try {
                date = format.parse(oTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            long timeDiff = 0;
            if (date != null) {
                timeDiff = (now.getTime() - date.getTime()) / 1000;
            }
            if (timeDiff < 600) {
                delCount++;
                OrderService.delOrder(order.getoId());
            }
        }
        // 未被删除的订单总数
        request.getSession().setAttribute("orderFailure", orders.size() - delCount);
        try {
            response.sendRedirect("jsp/orderFailure.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
