package dev.paintilya.globaltaxcalculator.BLL.Control;

import dev.paintilya.globaltaxcalculator.BLL.Model.Transaction;
import dev.paintilya.globaltaxcalculator.DAL.ITransactionDAO;

import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

public class GlobalTaxController {

    private ITransactionDAO transactionDAO;

    private record Response(double tax, double highestRate) {
        @Override
        public String toString() {
            return "Response{" +
                    "tax=" + tax +
                    ", highestRate=" + highestRate +
                    '}';
        }
    }

    public GlobalTaxController(ITransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }

    public Map<String, Double> calculateGlobalTax(double netIncome) {
        Map<String, Double> responseData = new HashMap<>();

        WebClient webClientQuebec = WebClient.create("http://localhost:8082");
        WebClient webClientCanada = WebClient.create("http://localhost:8081");

        // Make the request and deserialize the response
        Response quebecResponse = webClientQuebec.get()
                .uri("/api/v1/qtc/calculate?netIncome=" + netIncome)
                .retrieve()
                .bodyToMono(Response.class)
                .block();


        Response canadaResponse = webClientCanada.get()
            .uri("/api/v1/ctc?netIncome=" + netIncome)
            .retrieve()
            .bodyToMono(Response.class)
            .block();

        responseData.put("taxQuebec", quebecResponse.tax);
        responseData.put("highestRateQuebec", quebecResponse.highestRate);
        responseData.put("taxCanada", canadaResponse.tax);
        responseData.put("highestRateCanada", canadaResponse.highestRate);

        // SAVE TO CSV TODO
        this.transactionDAO.addTransaction(new Transaction("2023-04-26", netIncome, quebecResponse.tax + canadaResponse.tax));
        return responseData;
    }

}