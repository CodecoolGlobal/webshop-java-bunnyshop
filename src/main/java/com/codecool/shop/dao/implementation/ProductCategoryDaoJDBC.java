package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.database.dbCreator;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoJDBC implements ProductCategoryDao{

    private static ProductCategoryDaoJDBC instance = null;

    public ProductCategoryDaoJDBC() {}

    public static ProductCategoryDaoJDBC getInstance() {
        if (instance == null) {
            instance = new ProductCategoryDaoJDBC();
        }
        return instance;
    }

    @Override
    public void add(ProductCategory category) {

    }

    @Override
    public ProductCategory find(int id) throws IllegalArgumentException {

        if (id < 1) {
            System.out.println("Id cannot be smaller than 1");
            throw new IllegalArgumentException("id cannot be lower than 1");
        }

        String query = "SELECT * FROM product_category " +
                "WHERE id = " + id + ";";

        try(Connection connection = dbCreator.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query)
        ) {
            resultSet.next();
            ProductCategory productCategory = getProductCategoryObj(resultSet);
            return productCategory;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<ProductCategory> getAll() {
        String query = "SELECT * FROM product_category;";

        List<ProductCategory> resultList = new ArrayList<>();

        try(Connection connection = dbCreator.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                ProductCategory productCategory = getProductCategoryObj(resultSet);

                resultList.add(productCategory);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultList;

    }

    private ProductCategory getProductCategoryObj(ResultSet resultSet) throws SQLException {
        int productCategoryId = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String department = resultSet.getString("department");
        String description = resultSet.getString("description");
        return new ProductCategory(productCategoryId, name, department, description);
    }


}
