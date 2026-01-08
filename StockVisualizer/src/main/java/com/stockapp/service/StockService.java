package com.stockapp.service;

import com.stockapp.model.Stock;
import org.springframework.stereotype.Service;

@Service
public class StockService {

    // RAW Array implementation as per user request
    private Stock[] portfolio = new Stock[0];

    // Added new stock by manually resizing array (O(N))
    public void addStock(Stock stock) {
        Stock[] temp = new Stock[portfolio.length + 1];
        // Manual copy
        for (int i = 0; i < portfolio.length; i++) {
            temp[i] = portfolio[i];
        }
        temp[portfolio.length] = stock;
        portfolio = temp;
    }

    public Stock[] getAllStocks() {
        return portfolio;
    }

    // --- Searching ---

    public Stock[] linearSearch(String name) {
        // First count matches to create exact size array
        int count = 0;
        for (int i = 0; i < portfolio.length; i++) {
            if (portfolio[i].getStockName().equalsIgnoreCase(name)) {
                count++;
            }
        }

        Stock[] results = new Stock[count];
        int index = 0;
        for (int i = 0; i < portfolio.length; i++) {
            if (portfolio[i].getStockName().equalsIgnoreCase(name)) {
                results[index++] = portfolio[i];
            }
        }
        return results;
    }

    public Stock binarySearch(String name) {
        sortByName();

        int left = 0;
        int right = portfolio.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = portfolio[mid].getStockName().compareToIgnoreCase(name);

            if (comparison == 0) {
                return portfolio[mid];
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }

    // --- Helper Sort by Name for Binary Search ---
    private void sortByName() {
        int n = portfolio.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (portfolio[j].getStockName().compareToIgnoreCase(portfolio[j + 1].getStockName()) > 0) {
                    Stock temp = portfolio[j];
                    portfolio[j] = portfolio[j + 1];
                    portfolio[j + 1] = temp;
                }
            }
        }
    }

    // --- Sorting Algorithms (By Price) ---

    public void bubbleSort() {
        int n = portfolio.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (portfolio[j].getStockPrice() > portfolio[j + 1].getStockPrice()) {
                    Stock temp = portfolio[j];
                    portfolio[j] = portfolio[j + 1];
                    portfolio[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped)
                break;
        }
    }

    public void selectionSort() {
        int n = portfolio.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (portfolio[j].getStockPrice() < portfolio[minIndex].getStockPrice()) {
                    minIndex = j;
                }
            }
            Stock temp = portfolio[minIndex];
            portfolio[minIndex] = portfolio[i];
            portfolio[i] = temp;
        }
    }

    // Merge Sort
    public void mergeSort() {
        divide(portfolio, 0, portfolio.length - 1);
    }

    private void divide(Stock[] arr, int si, int ei) {
        if (si >= ei) {
            return;
        }
        int mid = si + (ei - si) / 2;
        divide(arr, si, mid);
        divide(arr, mid + 1, ei);
        conquer(arr, si, mid, ei);
    }

    private void conquer(Stock[] arr, int si, int mid, int ei) {
        Stock[] mergedArray = new Stock[ei - si + 1];
        int indx1 = si;
        int indx2 = mid + 1;
        int x = 0;

        while (indx1 <= mid && indx2 <= ei) {
            if (arr[indx1].getStockPrice() <= arr[indx2].getStockPrice()) {
                mergedArray[x++] = arr[indx1++];
            } else {
                mergedArray[x++] = arr[indx2++];
            }
        }

        while (indx1 <= mid) {
            mergedArray[x++] = arr[indx1++];
        }

        while (indx2 <= ei) {
            mergedArray[x++] = arr[indx2++];
        }

        for (int i = 0, j = si; i < mergedArray.length; i++, j++) {
            arr[j] = mergedArray[i];
        }
    }

    // Quick Sort
    public void quickSort() {
        if (portfolio.length > 0) {
            quick(portfolio, 0, portfolio.length - 1);
        }
    }

    private void quick(Stock[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quick(arr, low, pi - 1);
            quick(arr, pi + 1, high);
        }
    }

    private int partition(Stock[] arr, int low, int high) {
        double pivot = arr[high].getStockPrice();
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j].getStockPrice() < pivot) {
                i++;
                Stock temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        Stock temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }
}
