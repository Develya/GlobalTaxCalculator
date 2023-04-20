package dev.paintilya.globaltaxcalculator.BLL.Control;

import dev.paintilya.globaltaxcalculator.BLL.Model.Transaction;
import dev.paintilya.globaltaxcalculator.DAL.ITransactionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

public class GlobalTaxController {

    private ITransactionDAO transactionDAO;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private RestTemplate restTemplate;

    record Response(double tax, double highestRate) {
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
        // WebClientCanada = WebClient.create("httpL//localhost:PORT");

        // Make the request and deserialize the response
        Response quebecResponse = webClientQuebec.get()
                .uri("/api/v1/qtc/calculate?netIncome=" + netIncome)
                .retrieve()
                .bodyToMono(Response.class)
                .block();

        /*
          Response canadaResponse = webClientCanada.get()
            .uri("/api/v1/qtc/calculate?netIncome=" + netIncome)
            .retrieve()
            .bodyToMono(Response.class)
            .block();
        */

        // Do something with the response
        responseData.put("tax", quebecResponse.tax /*+ canadaResponse.tax*/);
        responseData.put("highestRateQuebec", quebecResponse.highestRate);
        // responseData.put("highestRateCanada", canadaResponse.highestRate);

        // SAVE TO CSV TODO
        //this.transactionDAO.addTransaction(new Transaction());
        return responseData;
    }

}