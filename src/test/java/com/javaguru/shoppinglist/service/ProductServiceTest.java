package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.dto.ProductDto;
import com.javaguru.shoppinglist.mapper.ProductConverter;
import com.javaguru.shoppinglist.repository.ProductRepository;
import com.javaguru.shoppinglist.service.validation.ProductValidationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    @Mock
    private ProductValidationService validationService;

    @Mock
    private ProductConverter productConverter;

    @Captor
    private ArgumentCaptor<ProductDto> productCaptor;

    private ProductService victim;

    @Before
    public void setUp() throws Exception {
        victim = new ProductService(repository, validationService, productConverter);
    }

    @Test
    public void shouldCreateProductSuccessfully() {
        ProductDto productDto = productDto();
        Product product = product();
        when(productConverter.convert(productDto)).thenReturn(product);
        when(repository.save(product)).thenReturn(product.getId());

        Long result = victim.createProduct(productDto);

        verify(validationService).validate(productCaptor.capture());
        ProductDto captorResult = productCaptor.getValue();

        assertThat(captorResult).isEqualTo(productDto);
        assertThat(product.getId()).isEqualTo(result);
    }

    @Test
    public void shouldFindProductById() {
        when(repository.findProductById(1002L)).thenReturn(Optional.ofNullable(product()));
        when(productConverter.convert(product())).thenReturn(productDto());

        ProductDto result = victim.findProductById(1002L);

        assertThat(productDto()).isEqualTo(result);
    }

    @Test
    public void shouldThrowExceptionProductNotFound() {
        when(repository.findProductById(any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> victim.findProductById(1002L))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("Product not found, id: 1002");
    }

    private ProductDto productDto() {
        ProductDto productDto = new ProductDto();
        productDto.setName("TEST_NAME");
        productDto.setCategory("TEST_CATEGORY");
        productDto.setPrice(new BigDecimal(2345));
        productDto.setDiscount(new BigDecimal(30));
        productDto.setDescription("TEST_DESCRIPTION");
        productDto.setId(1002L);
        return productDto;
    }

    private Product product() {
        Product product = new Product();
        product.setName("TEST_NAME");
        product.setCategory("TEST_CATEGORY");
        product.setPrice(new BigDecimal(2345));
        product.setDiscount(new BigDecimal(30));
        product.setDescription("TEST_DESCRIPTION");
        product.setId(1002L);
        return product;
    }
}
