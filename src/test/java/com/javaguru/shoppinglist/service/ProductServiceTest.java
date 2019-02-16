package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductInMemoryRepository;
import com.javaguru.shoppinglist.service.validation.ProductValidationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    private ProductInMemoryRepository repository;

    @Mock
    private ProductValidationService validationService;

    @InjectMocks
    private ProductService victim;

    @Captor
    private ArgumentCaptor<Product> productCaptor;

    @Test
    public void shouldCreateProductSuccessfully() {
        Product product = product();
        when(repository.insert(product)).thenReturn(product);

        Long result = victim.createProduct(product);

        verify(validationService).validate(productCaptor.capture());
        Product captorResult = productCaptor.getValue();

        assertEquals(captorResult, product);
        assertEquals(product.getId(), result);
    }

    @Test
    public void shouldFindProductbyId() {
        when(repository.findProductById(2345L)).thenReturn(product());

        Product result = victim.findProductbyId(2345L);

        assertEquals(product(), result);
    }

    private Product product() {
        Product product = new Product();
        product.setId(2345L);
        product.setName("Rye bread");
        product.setCategory("Bakery product");
        product.setPrice(new BigDecimal(3.4));
        product.setDiscount(new BigDecimal(0));
        product.setDescription("Rye bread is extremely popular in Latvia and" +
                " there are plenty of different types, including dark or sweet" +
                " sourdough rye bread. ");
        return product;
    }

}