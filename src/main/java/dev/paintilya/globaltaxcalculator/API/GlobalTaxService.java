package dev.paintilya.globaltaxcalculator.API;

import dev.paintilya.globaltaxcalculator.BLL.Control.GlobalTaxController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/gtc")
public class GlobalTaxService {
    @Autowired
    private GlobalTaxController globalTaxController;

    @RequestMapping("/calculate")
    public ResponseEntity<Object> calculateTax(@RequestParam double netIncome) {
        Map<String, Double> response = globalTaxController.calculateGlobalTax(netIncome);
        Object responseObject = new Object() {
            public double taxQuebec = response.get("taxQuebec");
            public double taxCanada = response.get("taxCanada");
            public double highestRateQuebec = response.get("highestRateQuebec");
            public double canadaHighestRate = response.get("highestRateCanada");
        };

        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        return new ResponseEntity<>(responseObject, headers, HttpStatus.OK);
    }

}