package pl.epoint.dobrebski.utils;

import pl.epoint.dobrebski.model.Product;

import java.util.List;

/**
 * Created by dobrebski on 04.01.17.
 */
public interface ProductManager {
    List<Product> getProductsList();
    Product getProductByPK(Integer productPK);
    void insertProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(Product product);
}
