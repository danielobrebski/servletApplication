package pl.epoint.dobrebski.utils;

import pl.epoint.dobrebski.model.Product;

import java.sql.Connection;
import java.util.List;

/**
 * Created by dobrebski on 04.01.17.
 */
public class ProductDatabaseManagerImpl implements ProductManager {

    private Connection getDirectConnection() {
        return null;
    }

    @Override
    public List<Product> getProductsList() {
        return null;
    }

    @Override
    public Product getProductByPK(Integer productPK) {
        return null;
    }

    @Override
    public void insertProduct(Product product) {

    }

    @Override
    public void updateProduct(Product product) {

    }

    @Override
    public void deleteProduct(Product product) {

    }
}
