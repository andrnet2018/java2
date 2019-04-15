package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.config.AppConfig;
import com.javaguru.shoppinglist.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import(value = {HibernateProductRepository.class, AppConfig.class})
public class ProductRepositoryIT {

    @Autowired
    private ProductRepository victim;

    @Test
    public void shouldSaveProduct() {
        Product product = new Product();
        product.setName("PRODUCT_NAME");
        product.setPrice(new BigDecimal(100));
        product.setCategory("PRODUCT_CATEGORY");
        product.setDiscount(new BigDecimal(5));
        product.setDescription("PRODUCT_DESCRIPTION");

        Long result = victim.save(product);

        assertThat(result).isNotNull();
    }

    @Test
    public void shouldFindProductByName() {
        Product product = new Product();
        product.setName("PRODUCT_NAME");
        product.setPrice(new BigDecimal(100));
        product.setCategory("PRODUCT_CATEGORY");
        product.setDiscount(new BigDecimal(5));
        product.setDescription("PRODUCT_DESCRIPTION");

        Long id = victim.save(product);

        Optional<Product> result = victim.findProductByName("PRODUCT_NAME");

        assertThat(result).hasValue(expectedProduct(id));
    }

    @Test
    public void shouldFindTaskById() {
        Product product = new Product();
        product.setName("PRODUCT_NAME");
        product.setPrice(new BigDecimal(100));
        product.setCategory("PRODUCT_CATEGORY");
        product.setDiscount(new BigDecimal(5));
        product.setDescription("PRODUCT_DESCRIPTION");

        Long id = victim.save(product);

        Optional<Product> result = victim.findProductById(id);
        assertThat(result).hasValue(expectedProduct(id));
    }

    @Test
    public void shouldExistsByName() {
        Product product = new Product();
        product.setName("PRODUCT_NAME");
        product.setPrice(new BigDecimal(100));
        product.setCategory("PRODUCT_CATEGORY");
        product.setDiscount(new BigDecimal(5));
        product.setDescription("PRODUCT_DESCRIPTION");

        victim.save(product);

        boolean result = victim.existsByName("PRODUCT_NAME");
        assertThat(result).isTrue();
    }


    public Product expectedProduct(Long id) {
        Product product = new Product();
        product.setName("PRODUCT_NAME");
        product.setPrice(new BigDecimal(100));
        product.setCategory("PRODUCT_CATEGORY");
        product.setDiscount(new BigDecimal(5));
        product.setDescription("PRODUCT_DESCRIPTION");
        product.setId(id);
        return product;

    }
}
