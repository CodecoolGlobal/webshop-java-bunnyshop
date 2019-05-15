package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.database.dbCreator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJDBC implements ProductDao {

    private static ProductDaoJDBC instance = null;

    public ProductDaoJDBC() {

    }

    public static ProductDaoJDBC getInstance() {
        if (instance == null) {
            instance = new ProductDaoJDBC();
        }
        return instance;
    }

    @Override
    public void add(Product product) {

    }

    @Override
    public Product find(int id) {
        String query = "SELECT * FROM product " +
                "WHERE id = " + id + ";";

        try(Connection connection = dbCreator.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query)
        ) {
            resultSet.next();
            Product product = getProductObj(resultSet);
            return product;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> getAll() {
        String query = "SELECT * FROM product;";

        List<Product> resultList = new ArrayList<>();


        try(Connection connection = dbCreator.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
        ) {

            while (resultSet.next()) {
                Product product = getProductObj(resultSet);
                resultList.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;

    }

    private Product getProductObj(ResultSet resultSet) throws SQLException {
        int productId = resultSet.getInt("id");
        String name = resultSet.getString("name");
        Float defaultPrice = resultSet.getFloat("default_price");
        String currencyString = resultSet.getString("currency_string");
        String description = resultSet.getString("description");
        int productCategoryId = resultSet.getInt("product_category_id");
        int supplierId = resultSet.getInt("supplier_id");

        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoJDBC.getInstance();
        SupplierDao supplierDataStore = SupplierDaoJDBC.getInstance();

        ProductCategory productCategory = productCategoryDataStore.find(productCategoryId);
        Supplier supplier = supplierDataStore.find(supplierId);

        return new Product(productId, name, description, defaultPrice, currencyString, productCategory, supplier);
    }


}
