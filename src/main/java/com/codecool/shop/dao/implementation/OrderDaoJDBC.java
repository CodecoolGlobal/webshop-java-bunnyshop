package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.database.dbCreator;
import com.codecool.shop.model.OrderList;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.customer.CustomerAddress;
import com.codecool.shop.customer.CustomerContactInfo;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderDaoJDBC implements OrderDao {
    private static OrderDaoJDBC instance = null;

    public OrderDaoJDBC() {

    }

    public static OrderDaoJDBC getInstance() {
        if (instance == null) {
            instance = new OrderDaoJDBC();
        }
        return instance;
    }

    public void add(CustomerContactInfo contactInfo) {
        String contactInfoQuery = " INSERT INTO contact_info (name, email, phone_number, billing_address, " +
                " shipping_address)" +
                " VALUES (?,?,?,?,?) RETURNING id;";

        try(Connection connection = dbCreator.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(contactInfoQuery)

        ) {
            preparedStatement.setString(1, contactInfo.getName());
            preparedStatement.setString(2, contactInfo.getEmail());
            preparedStatement.setString(3, contactInfo.getPhoneNumber());
            preparedStatement.setString(4, contactInfo.getBillingAddressString());
            preparedStatement.setString(5, contactInfo.getShippingAddressString());

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int contactInfoId = resultSet.getInt("id");
            OrderDao orderMemDataStore = OrderDaoMem.getInstance();
            Order order = ((OrderDaoMem) orderMemDataStore).findLast();
            order.setContactInfoId(contactInfoId);


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void add(Order order) {
        String orderQuery = " INSERT INTO order_info (name, description, total_sum, currency, " +
                " contact_info_id)" +
                " VALUES (?,?,?,?,?);";

        try(Connection connection = dbCreator.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(orderQuery)

        ) {
            preparedStatement.setString(1, order.getName());
            preparedStatement.setString(2, order.getDescription());
            preparedStatement.setFloat(3, order.getTotalSum());
            preparedStatement.setString(4, order.getCURRENCY());
            preparedStatement.setInt(5, order.getContactInfoId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public List<OrderList> getLineItems(int orderId) {
        List<OrderList> lineItems = new ArrayList<>();

        String lineItemQuery = "SELECT * FROM order_line" +
                " WHERE order_info_id = " + orderId + ";";

        try (Connection connection = dbCreator.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(lineItemQuery)
        ){
            while (resultSet.next()) {
                int lineItemId = resultSet.getInt("id");
                int numberOfProducts = resultSet.getInt("number_of_products");
                String lineItemName = resultSet.getString("name");
                String lineItemDescription = resultSet.getString("description");
                int productId = resultSet.getInt("product_id");

                ProductDaoJDBC productDataStore = ProductDaoJDBC.getInstance();
                Product product = productDataStore.find(productId);

                OrderList orderList = new OrderList(product, lineItemId, numberOfProducts, lineItemName, lineItemDescription, productId);
                lineItems.add(orderList);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lineItems;
    }

    @Override
    public Order find(int orderId) {
        String orderQuery = "SELECT * FROM order_info" +
                " WHERE id = " + orderId + ";";

        return getSingleOrderFromQuery(orderQuery);
    }

    private Order getSingleOrderFromQuery(String orderQuery) {
        try (Connection connection = dbCreator.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(orderQuery)
        ) {
            Order order = getOrderObject(resultSet);


            return order;
        } catch (SQLException e1) {
            e1.printStackTrace();
            return null;
        }
    }

    private Order getOrderObject(ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            return null;
        } else {
            resultSet.next();
            int orderIdFromDB = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            int totalSum = resultSet.getInt("total_sum");
            String currency = resultSet.getString("currency");

            OrderDaoJDBC orderDataStore = OrderDaoJDBC.getInstance();
            CustomerContactInfo contactInfo = orderDataStore.getContactInfo(orderIdFromDB);
            List<OrderList> orderList = orderDataStore.getLineItems(orderIdFromDB);

            return new Order(orderIdFromDB, name, description, totalSum, currency, contactInfo, orderList);
        }
    }

    public Order findLast() {
        String orderQuery = "SELECT * from order_info ORDER BY id DESC LIMIT 1;";

        return getSingleOrderFromQuery(orderQuery);
    }


    public CustomerContactInfo getContactInfo(int orderId) {
        String contactInfoQuery = "SELECT * FROM contact_info" +
                " WHERE order_info_id = " + orderId + ";";

        try (Connection connection = dbCreator.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(contactInfoQuery)
        ){
            resultSet.next();
            int contactInfoId = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String phoneNumber = resultSet.getString("phone_number");
            String billingAddress = resultSet.getString("billing_address");
            CustomerAddress billingAddressObj = new CustomerAddress(CustomerAddress.getAddressFields(billingAddress));
            String shippingAddress = resultSet.getString("shipping_address");
            CustomerAddress shippingAddressObj = new CustomerAddress(CustomerAddress.getAddressFields(shippingAddress));

            CustomerContactInfo customerContactInfo = new CustomerContactInfo(contactInfoId, name, email, phoneNumber, billingAddressObj, shippingAddressObj);

            return customerContactInfo;
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Order> getAll() {
        return null;
    }
}
