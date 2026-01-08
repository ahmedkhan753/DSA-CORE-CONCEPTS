package com.stockapp.service;

import com.stockapp.model.Stock;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class StockService {

    private List<Stock> portfolio = new ArrayList<>();

    public void addStock(Stock stock) {
        portfolio.add(stock);
    }

    public List<Stock> getAllStocks() {
        return portfolio;
    }

    // --- Searching ---

    public List<Stock> linearSearch(String name) {
        List<Stock> result = new ArrayList<>();
        for (Stock stock : portfolio) {
            if (stock.getStockName().equalsIgnoreCase(name)) {
                result.add(stock);
            }
        }
        return result;
    }

    public Stock binarySearch(String name) {
        // Must sort first for binary search
        quickSort(portfolio, 0, portfolio.size() - 1);

        // Sort specifically by name for name-based binary search since quicksort did
        // price?
        // Actually, let's keep it simple: Binary Search usually implies the data is
        // sorted by the key we are searching.
        // The previous CLI code sorted by name before binary search.

        List<Stock> sortedByName = new ArrayList<>(portfolio);
        Collections.sort(sortedByName, (a, b) -> a.getStockName().compareToIgnoreCase(b.getStockName()));

        int left = 0;
        int right = sortedByName.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = sortedByName.get(mid).getStockName().compareToIgnoreCase(name);

            if (comparison == 0) {
                return sortedByName.get(mid);
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }

    // --- Sorting ---

    public void bubbleSort() {
        int n = portfolio.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (portfolio.get(j).getStockPrice() > portfolio.get(j + 1).getStockPrice()) {
                    Collections.swap(portfolio, j, j + 1);
                }
            }
        }
    }

    public void selectionSort() {
        int n = portfolio.size();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (portfolio.get(j).getStockPrice() < portfolio.get(minIndex).getStockPrice()) {
                    minIndex = j;
                }
            }
            Collections.swap(portfolio, minIndex, i);
        }
    }

    public void mergeSort() {
        // Helper to trigger merge sort on the list
        if (portfolio.size() > 1) {
            List<Stock> sorted = mergeSortHelper(portfolio);
            portfolio.clear();
            portfolio.addAll(sorted);
        }
    }

    private List<Stock> mergeSortHelper(List<Stock> list) {
        if (list.size() <= 1) {
            return list;
        }
        int mid = list.size() / 2;
        List<Stock> left = mergeSortHelper(new ArrayList<>(list.subList(0, mid)));
        List<Stock> right = mergeSortHelper(new ArrayList<>(list.subList(mid, list.size())));
        return merge(left, right);
    }

    private List<Stock> merge(List<Stock> left, List<Stock> right) {
        List<Stock> merged = new ArrayList<>();
        int i = 0, j = 0;
        while (i < left.size() && j < right.size()) {
            if (left.get(i).getStockPrice() <= right.get(j).getStockPrice()) {
                merged.add(left.get(i++));
            } else {
                merged.add(right.get(j++));
            }
        }
        while (i < left.size())
            merged.add(left.get(i++));
        while (j < right.size())
            merged.add(right.get(j++));
        return merged;
    }

    public void quickSort() {
        if (portfolio.size() > 0) {
            quickSortHelper(portfolio, 0, portfolio.size() - 1);
        }
    }

    // Using simple recursive Helper with existing List reference
    public void quickSortHelper(List<Stock> list, int low, int high) {
        if (low < high) {
            int pi = partition(list, low, high);
            quickSortHelper(list, low, pi - 1);
            quickSortHelper(list, pi + 1, high);
        }
    }

    private void quickSort(List<Stock> list, int low, int high) {
        quickSortHelper(list, low, high);
    }

    private int partition(List<Stock> list, int low, int high) {
        double pivot = list.get(high).getStockPrice();
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (list.get(j).getStockPrice() < pivot) {
                i++;
                Collections.swap(list, i, j);
            }
        }
        Collections.swap(list, i + 1, high);
        return i + 1;
    }
}
