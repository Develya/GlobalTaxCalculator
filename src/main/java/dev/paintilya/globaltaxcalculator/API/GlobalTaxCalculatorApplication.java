package dev.paintilya.globaltaxcalculator.API;

import dev.paintilya.globaltaxcalculator.BLL.Control.GlobalTaxController;
import dev.paintilya.globaltaxcalculator.DAL.CsvTransactionDAO;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GlobalTaxCalculatorApplication {

    @Bean
    GlobalTaxController getQuebecTaxController() {
        return new GlobalTaxController(new CsvTransactionDAO("/home/kiyranya/IdeaProjects/TaxCalculator2.0/GlobalTaxCalculator/src/main/resources/transactions.csv"));
    }

    public static void main(String[] args) {
        SpringApplication.run(GlobalTaxCalculatorApplication.class, args);
    }

}