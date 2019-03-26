package com.javaguru.shoppinglist.console.action;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.ProductService;
import com.javaguru.shoppinglist.service.validation.ProductValidationException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Scanner;

@Component
public class CreateProductAction implements Action {

    private static final String ACTION_NAME = "Create Product";

    private final ProductService productService;

    public CreateProductAction(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product name:");
        String name = scanner.nextLine();
        System.out.println("Enter product price: ");
        String price = scanner.nextLine();
        System.out.println("Enter product category: ");
        String category = scanner.nextLine();
        System.out.println("Enter product discount: ");
        String discount = scanner.nextLine();
        System.out.println("Enter product description: ");
        String description = scanner.nextLine();

        Product product = new Product();
        product.setName(name);
        product.setPrice(new BigDecimal(price));
        product.setCategory(category);
        product.setDiscount(new BigDecimal(discount));
        product.setDescription(description);
        try {
            Long response = productService.createProduct(product);
            System.out.println("Response: " + response);
        } catch (ProductValidationException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error! Please try again.");
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return ACTION_NAME;
    }

}
