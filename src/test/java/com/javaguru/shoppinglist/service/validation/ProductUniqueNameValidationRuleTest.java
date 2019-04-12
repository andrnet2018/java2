package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.dto.ProductDto;
import com.javaguru.shoppinglist.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductUniqueNameValidationRuleTest {

    @Mock
    private ProductRepository hibernateProductRepository;

    @Spy
    @InjectMocks
    private ProductUniqueNameValidationRule victim;

    private ProductDto productDto = productDto();

    @Test
    public void shouldThrowException() {
        when(hibernateProductRepository.existsByName(productDto.getName())).thenReturn(true);

        assertThatThrownBy(() -> victim.validate(productDto))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("Product name must be unique.");

        verify(victim).checkNotNull(productDto);
    }

    @Test
    public void shouldValidateSuccess() {
        when(hibernateProductRepository.existsByName(productDto.getName())).thenReturn(false);

        victim.validate(productDto);

        verify(victim).checkNotNull(productDto);
    }

    private ProductDto productDto() {
        ProductDto productDto = new ProductDto();
        productDto.setId(666L);
        productDto.setName("TEST_NAME");
        productDto.setCategory("TEST_CATEGORY");
        productDto.setPrice(new BigDecimal(666));
        productDto.setDiscount(new BigDecimal(99));
        productDto.setDescription("TEST_DESCRIPTION");
        return productDto;
    }
}
