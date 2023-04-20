package dev.paintilya.globaltaxcalculator.API;

import dev.paintilya.globaltaxcalculator.BLL.Control.GlobalTaxController;
import dev.paintilya.globaltaxcalculator.DAL.CsvTransactionDAO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class GlobalTaxCalculatorApplication {

    @Bean
    @LoadBalanced
    public WebClient.Builder getWebClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() { return new RestTemplate(); }

    @Bean
    GlobalTaxController getQuebecTaxController() {
        return new GlobalTaxController(new CsvTransactionDAO("/home/kiyranya/IdeaProjects/TaxCalculator/GlobalTaxCalculator/src/main/resources/transactions.csv"));
    }

    public static void main(String[] args) {
        SpringApplication.run(GlobalTaxCalculatorApplication.class, args);
    }

}