import com.codecool.shop.dao.implementation.ProductCategoryDaoJDBC;
import com.codecool.shop.dao.implementation.ProductDaoJDBC;
import com.codecool.shop.dao.implementation.SupplierDaoJDBC;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class DaoJdbcTest {


    @Test
    void productGetAllTest() {
        ProductDaoJDBC productDaoJDBC = ProductDaoJDBC.getInstance();
        List<Product> products = productDaoJDBC.getAll();
        assertEquals(9, products.size());
    }

    @Test
    void supplierGetAllTest() {
        SupplierDaoJDBC supplierDaoJDBC = SupplierDaoJDBC.getInstance();
        List<Supplier> suppliers = supplierDaoJDBC.getAll();
        assertEquals(5, suppliers.size());
    }

    @Test
    void productCategoryGetAllTest() {
        ProductCategoryDaoJDBC productCategoryDaoJDBC = ProductCategoryDaoJDBC.getInstance();
        List<ProductCategory> productCategories = productCategoryDaoJDBC.getAll();
        assertEquals(5, productCategories.size());
    }

    @Test
    void getproductByIdTest() {
        ProductDaoJDBC productDaoJDBC = ProductDaoJDBC.getInstance();
        Product product = productDaoJDBC.find(1);
        assertEquals("Black Alaska", product.getName());
        assertEquals(49f, product.getDefaultPrice());
        assertEquals("USD", product.getDefaultCurrency().toString());
        assertEquals("Despite its name, the Alaska Rabbit originates in Germany, rather than Alaska.", product.getDescription());
    }

    @Test
    void getSupplierByIdTest() {
        SupplierDaoJDBC supplierDaoJDBC = SupplierDaoJDBC.getInstance();
        Supplier supplier = supplierDaoJDBC.find(1);
        assertEquals("Perfect Bunnies", supplier.getName());
        assertEquals("I am a hobbyist breeder of these wonderful little creatures. Our rabbits that we use for breeding are our family pets and it is purely for enjoyment that we do it.", supplier.getDescription());
    }

    @Test
    void getProductCategoryById() {
        ProductCategoryDaoJDBC productCategoryDaoJDBC = ProductCategoryDaoJDBC.getInstance();
        ProductCategory productCategory = productCategoryDaoJDBC.find(1);
        assertEquals("Alaska", productCategory.getName());
        assertEquals("animal", productCategory.getDepartment());
        assertEquals("Despite its name, the Alaska Rabbit originates in Germany, rather than Alaska.", productCategory.getDescription());
    }

    @Test
    void invalidProductFindParameter() throws IllegalArgumentException {
        ProductDaoJDBC productDaoJDBC = ProductDaoJDBC.getInstance();
        assertThrows(IllegalArgumentException.class, () -> {
            productDaoJDBC.find(-1);
        });
    }

    @Test
    void invalidSupplierFindParameter() throws IllegalArgumentException {
        SupplierDaoJDBC supplierDaoJDBC = SupplierDaoJDBC.getInstance();
        assertThrows(IllegalArgumentException.class, () -> {
            supplierDaoJDBC.find(-1);
        });
    }

    @Test
    void invalidProductCategoryFindParameter() throws IllegalArgumentException {
        ProductCategoryDaoJDBC productCategoryDaoJDBC = ProductCategoryDaoJDBC.getInstance();
        assertThrows(IllegalArgumentException.class, () -> {
            productCategoryDaoJDBC.find(-1);
        });
    }


}