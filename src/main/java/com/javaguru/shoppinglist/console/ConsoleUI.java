package com.javaguru.shoppinglist.console;

import com.javaguru.shoppinglist.console.action.Action;
import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.DefaultProductService;
import com.javaguru.shoppinglist.service.validation.ProductValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

@Component
public class ConsoleUI {

    private final List<Action> actions;

    @Autowired
    public ConsoleUI(List<Action> actions) {
        this.actions = actions;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int response = 0;

        while (response >= 0) {
            printMenu();
            try {
                response = scanner.nextInt();
                actions.get(response).execute();
            } catch (Exception e) {
                System.out.println("Error! Please try again.");
                e.printStackTrace();
            }
        }
    }

    private void printMenu() {
        for (int i = 0; i < actions.size(); i++) {
            System.out.println(i + ". " + actions.get(i));
        }
    }
}
