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
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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

        assertThat(captorResult).isEqualTo(product);
        assertThat(product.getId()).isEqualTo(result);
    }

    @Test
    public void shouldFindProductById() {
        when(repository.findProductById(666L)).thenReturn(Optional.ofNullable(product()));

        Product result = victim.findProductById(666L);

        assertThat(product()).isEqualTo(result);
    }

    @Test
    public void shouldThrowExceptionProductNotFound() {
        when(repository.findProductById(666L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> victim.findProductById(666L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Product not found, id: 666");
    }

    private Product product() {
        Product product = new Product();
        product.setName("TEST_NAME");
        product.setCategory("TEST_CATEGORY");
        product.setPrice(new BigDecimal(2345));
        product.setDiscount(new BigDecimal(30));
        product.setDescription("TEST_DESCRIPTION");
        product.setId(666L);
        return product;
    }
}