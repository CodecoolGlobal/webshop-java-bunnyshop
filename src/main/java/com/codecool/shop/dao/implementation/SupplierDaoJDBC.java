package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.SupplierDao;
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

public class SupplierDaoJDBC implements SupplierDao {

    public static SupplierDaoJDBC instance = null;

    public SupplierDaoJDBC() {}

    public static SupplierDaoJDBC getInstance() {
        if(instance==null) {
            instance = new SupplierDaoJDBC();
        }
        return instance;
    }

    @Override
    public void add(Supplier supplier) {}

    @Override
    public Supplier find(int id) throws IllegalArgumentException {

        if (id < 1) {
            System.out.println("Id cannot be smaller than 1");
            throw new IllegalArgumentException("id cannot be lower than 1");
        }

        String query = "SELECT * FROM supplier " +
                        "WHERE id= " + id + ";";

        try(Connection connection = dbCreator.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query)
        ) {
            resultSet.next();
            Supplier supplier = getSupplierObj(resultSet);
            return supplier;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void remove(int id) {}

    @Override
    public List<Supplier> getAll() {
        String query = "SELECT * FROM supplier;";

        List<Supplier> resultList = new ArrayList<>();

        try(Connection connection = dbCreator.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                Supplier supplier = getSupplierObj(resultSet);

                resultList.add(supplier);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    private Supplier getSupplierObj(ResultSet resultSet) throws SQLException {
        int supplierId = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String description = resultSet.getString("description");
        return new Supplier(supplierId, name, description);
    }
}
