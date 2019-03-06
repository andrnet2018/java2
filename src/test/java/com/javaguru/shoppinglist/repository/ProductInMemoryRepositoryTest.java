package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Product;
import org.junit.Test;

import java.util.Optional;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductInMemoryRepositoryTest {

    private static final String PRODUCT_NAME = "TEST_NAME";
    private static final BigDecimal PRODUCT_PRICE = new BigDecimal(666L);
    private static final String PRODUCT_CATEGORY = "TEST_CATEGORY";
    private static final BigDecimal PRODUCT_DISCOUNT = new BigDecimal(20L);
    private static final String PRODUCT_DESCRIPTION = "TEST_DESCRIPTION";
    private static final long PRODUCT_ID = 0L;

    private ProductInMemoryRepository victim = new ProductInMemoryRepository();

    private Product product = product();

    @Test
    public void shouldInsert() {
        victim.insert(product);
        Product result = victim.getProducts().get(PRODUCT_ID);

        assertThat(result).isEqualTo(expectedProduct());
    }

    @Test
    public void shouldFindProductById() {
        victim.insert(product);

        Optional<Product> result = victim.findProductById(PRODUCT_ID);
        assertThat(result).isNotEmpty();
        assertThat(result).hasValue(expectedProduct());
    }

    @Test
    public void shouldExistsByName() {
        victim.insert(product);

        boolean result = victim.existsByName(PRODUCT_NAME);
        assertThat(result).isTrue();
    }

    @Test
    public void shouldFindProductByName() {
        victim.insert(product);

        Optional<Product> result = victim.findProductByName(PRODUCT_NAME);
        assertThat(result).isNotEmpty();
        assertThat(result).hasValue(expectedProduct());
    }

    private Product expectedProduct() {
        Product product = new Product();
        product.setName(PRODUCT_NAME);
        product.setCategory(PRODUCT_CATEGORY);
        product.setPrice(PRODUCT_PRICE);
        product.setDiscount(PRODUCT_DISCOUNT);
        product.setDescription(PRODUCT_DESCRIPTION);
        product.setId(PRODUCT_ID);
        return product;
    }

    private Product product() {
        Product product = new Product();
        product.setName(PRODUCT_NAME);
        product.setCategory(PRODUCT_CATEGORY);
        product.setPrice(PRODUCT_PRICE);
        product.setDiscount(PRODUCT_DISCOUNT);
        product.setDescription(PRODUCT_DESCRIPTION);
        return product;
    }
}
