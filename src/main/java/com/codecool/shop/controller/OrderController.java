package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.OrderList;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet (urlPatterns = {"/cart"})
public class OrderController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        OrderDao orderDataStore = OrderDaoMem.getInstance();

        ServletContext servletContext = req.getServletContext();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(servletContext);
        WebContext context = new WebContext(req,resp, servletContext);

        try {
            context.setVariable("order", ((OrderDaoMem) orderDataStore).findLast());
            engine.process("cart.html", context, resp.getWriter());
        }
        catch (IndexOutOfBoundsException e){
            resp.sendRedirect("/");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int productId =  Integer.parseInt(req.getParameter("productId"));

        ProductDaoMem productDataStore = ProductDaoMem.getInstance();
        Product product = productDataStore.find(productId);
        Order order = Order.getInstance();
        order.addProduct(product);

        OrderDao orderDataStore = OrderDaoMem.getInstance();
        if (orderDataStore.find(order.getId()) == null) {
            orderDataStore.add(order);
        }

        String dataAsJson = "{\"numberOfItemsInCart\": " + order.getTotalNumberOfOrderedProducts() + "}";

        resp.getWriter().write(dataAsJson);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int change = Integer.parseInt(req.getParameter("change"));
        int productId = Integer.parseInt(req.getParameter("productId"));

        OrderDao orderDataStore = OrderDaoMem.getInstance();
        Order currentOrder = ((OrderDaoMem) orderDataStore).findLast();

        ProductDao productDataStore = ProductDaoMem.getInstance();
        Product currentProduct = productDataStore.find(productId);

        OrderList orderListItem = currentOrder.getOrderListByProductId(productId);

        if (change > 0) {
            currentOrder.addProduct(currentProduct);
        } else if (change < 0) {
            currentOrder.removeProduct(currentProduct);
        }

        String dataToSend = "{\"numberOfProducts\": " + orderListItem.getNumberOfProducts() + "}";
        resp.getWriter().write(dataToSend);
    }
}