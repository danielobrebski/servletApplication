package pl.epoint.dobrebski.utils;

import pl.epoint.dobrebski.model.Product;
import pl.epoint.dobrebski.model.ProductDatabase;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dobrebski on 04.01.17.
 */
@Named
public class ProductMemoryManagerImpl implements ProductManager {

    @Override
    public List<Product> getProductsList() {
        return new ArrayList<>(ProductDatabase.productsMap.values());
    }

    @Override
    public Product getProductByPK(Integer productPK) {
        return ProductDatabase.productsMap.get(productPK);
    }

    @Override
    public void insertProduct(Product product) {
        ProductDatabase.productsMap.put(product.getPK(), product);
    }

    @Override
    public void updateProduct(Product product) {
        ProductDatabase.productsMap.put(product.getPK(), product);
    }

    @Override
    public void deleteProduct(Product product) {
        ProductDatabase.productsMap.remove(product.getPK());
    }
}
