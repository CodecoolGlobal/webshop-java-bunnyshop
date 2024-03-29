package com.codecool.shop.controller;

import com.codecool.shop.database.dbCreator;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoJDBC;
import com.codecool.shop.dao.implementation.ProductDaoJDBC;
import com.codecool.shop.dao.implementation.SupplierDaoJDBC;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.model.Order;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ProductDao productDataStore;
        ProductCategoryDao productCategoryDataStore;
        SupplierDao supplierDataStore;
        OrderDao orderDataStore;

        if(dbCreator.getConnection() != null) {
            productDataStore = ProductDaoJDBC.getInstance();
            productCategoryDataStore = ProductCategoryDaoJDBC.getInstance();
            supplierDataStore = SupplierDaoJDBC.getInstance();
            orderDataStore = OrderDaoMem.getInstance();
        } else {
            productDataStore = ProductDaoMem.getInstance();
            productCategoryDataStore = ProductCategoryDaoMem.getInstance();
            supplierDataStore = SupplierDaoMem.getInstance();
            orderDataStore = OrderDaoMem.getInstance();
        }


        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        int categoryIdFromUrl = req.getParameter("category") == null ? 0 : Integer.parseInt(req.getParameter("category"));
        int supplierIdFromUrl = req.getParameter("supplier") == null ? 0 : Integer.parseInt(req.getParameter("supplier"));

        context.setVariable("recipient", "World");
        context.setVariable("categories", productCategoryDataStore.getAll());
        context.setVariable("suppliers", supplierDataStore.getAll());
        context.setVariable("products", productDataStore.getAll());


        try {
            Order currentOrder = ((OrderDaoMem) orderDataStore).findLast();
            context.setVariable("numberOfProductsInCart", currentOrder.getTotalNumberOfOrderedProducts());
        }
        catch (IndexOutOfBoundsException e) {
            context.setVariable("numberOfProductsInCart", 0);
        }


        if (categoryIdFromUrl != 0 && supplierIdFromUrl != 0) {
            context.setVariable("products", productDataStore.getByComplex(categoryIdFromUrl, supplierIdFromUrl));
            context.setVariable("selectedCategory", productCategoryDataStore.find(categoryIdFromUrl));
            context.setVariable("selectedSupplier", supplierDataStore.find(supplierIdFromUrl));
        } else if (categoryIdFromUrl != 0) {
            context.setVariable("products",
                    productDataStore.getBy(productCategoryDataStore.find(categoryIdFromUrl)));
            context.setVariable("selectedCategory", productCategoryDataStore.find(categoryIdFromUrl));
        } else if (supplierIdFromUrl != 0) {
            context.setVariable("products",
                    productDataStore.getBy(supplierDataStore.find(supplierIdFromUrl)));
            context.setVariable("selectedSupplier", supplierDataStore.find(supplierIdFromUrl));
        } else {
            context.setVariable("products", productDataStore.getAll());
        }

        engine.process("product/index.html", context, resp.getWriter());
    }

}
