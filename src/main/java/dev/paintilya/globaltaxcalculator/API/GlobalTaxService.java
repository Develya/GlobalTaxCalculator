package dev.paintilya.globaltaxcalculator.API;

import dev.paintilya.globaltaxcalculator.BLL.Control.GlobalTaxController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/gtc")
public class GlobalTaxService {
    @Autowired
    private GlobalTaxController globalTaxController;

    @RequestMapping("/calculate")
    public @ResponseBody Object calculateTax(@RequestParam double netIncome) {
        Map<String, Double> response = globalTaxController.calculateGlobalTax(netIncome);
        return new Object() {
            public double tax = response.get("tax");
            public double quebecHighestRate = response.get("highestRateQuebec") * 100;
            //public double canadaHighestRate = response.get("highestRate") * 100;
        };
    }

}