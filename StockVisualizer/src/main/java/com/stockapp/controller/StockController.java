package com.stockapp.controller;

import com.stockapp.model.Stock;
import com.stockapp.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("stocks", stockService.getAllStocks());
        model.addAttribute("newStock", new Stock());
        return "index";
    }

    @PostMapping("/addStock")
    public String addStock(@ModelAttribute Stock stock) {
        stockService.addStock(stock);
        return "redirect:/";
    }

    @GetMapping("/sort")
    public String sortStocks(@RequestParam("algorithm") String algorithm) {
        switch (algorithm.toLowerCase()) {
            case "bubble":
                stockService.bubbleSort();
                break;
            case "selection":
                stockService.selectionSort();
                break;
            case "merge":
                stockService.mergeSort();
                break;
            case "quick":
                stockService.quickSort();
                break;
        }
        return "redirect:/";
    }

    @GetMapping("/search")
    public String searchStock(@RequestParam("query") String query, @RequestParam("type") String type, Model model) {
        Stock[] results;
        if ("binary".equalsIgnoreCase(type)) {
            Stock s = stockService.binarySearch(query);
            if (s != null) {
                results = new Stock[] { s };
            } else {
                results = new Stock[0];
            }
        } else {
            results = stockService.linearSearch(query);
        }

        // Pass results back to view (different from full list)
        model.addAttribute("stocks", stockService.getAllStocks()); // Show all
        model.addAttribute("searchResults", results); // Show searched
        model.addAttribute("newStock", new Stock());
        model.addAttribute("searchQuery", query); // To keep input filled
        return "index";
    }
}
