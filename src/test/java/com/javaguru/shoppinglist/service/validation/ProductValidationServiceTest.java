package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.dto.ProductDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProductValidationServiceTest {

    @Mock
    private ProductUniqueNameValidationRule uniqueNameValidationRule;

    @Mock
    private ProductNameValidationRule productNameValidationRule;

    @Mock
    private ProductDiscountValidationRule productDiscountValidationRule;

    @Mock
    private ProductPriceValidationRule productPriceValidationRule;

    @Captor
    private ArgumentCaptor<ProductDto> captor;

    private ProductValidationService victim;

    private ProductDto productDto = productDto();

    @Before
    public void setUp() {
        Set<ProductValidationRule> rules = new HashSet<>();
        rules.add(uniqueNameValidationRule);
        rules.add(productNameValidationRule);
        rules.add(productDiscountValidationRule);
        rules.add(productPriceValidationRule);

        victim = new ProductValidationService(rules);
    }

    @Test
    public void shouldValidate() {
        victim.validate(productDto);

        verify(uniqueNameValidationRule).validate(captor.capture());
        verify(productNameValidationRule).validate(captor.capture());
        verify(productDiscountValidationRule).validate(captor.capture());
        verify(productPriceValidationRule).validate(captor.capture());

        List<ProductDto> resultList = captor.getAllValues();
        assertThat(resultList).containsOnly(productDto);
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
