// Agenda Of the Project 
// In this code we are going to let user add stocks in the array
// User can add stocks
// User can search stocks
// User can buy stocks and sell stocks
// User can see all stocks based on ascending order

import java.util.*;

// import StockManagement.MergeSortStocks;  // Invalid import, removed

class Stock {
    String stockName;
    double stockPrice;
    int stockQuantity;

    Stock(String stockName, double stockPrice, int stockQuantity) {
        this.stockName = stockName;
        this.stockPrice = stockPrice;
        this.stockQuantity = stockQuantity;
    }
}

// Stocks are adding in the portfolio here

class StockManagement {
    Stock[] StocksInArray = new Stock[0];
    Scanner sc = new Scanner(System.in);

    public void AddStockToPortfolio() {
        while (true) {
            String stockName = "";
            double stockPrice = 0;
            int stockQuantity = 0;
            try {
                System.out.println("Enter Stock Name: ");
                stockName = sc.next();
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
                sc.nextLine(); // Clear buffer
                continue;
            }
            try {
                System.out.println("Enter Stock Price: ");
                stockPrice = sc.nextDouble();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid price.");
                sc.nextLine(); // Clear buffer
                continue;
            }
            try {
                System.out.println("Enter Stock Quantity: ");
                stockQuantity = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid quantity.");
                sc.nextLine(); // Clear buffer
                continue;
            }

            System.out.println("Type done to finish adding stocks");
            String done = sc.next();

            // Creating a new Stock Object
            Stock newStock = new Stock(stockName, stockPrice, stockQuantity);
            Stock[] tempArray = Arrays.copyOf(StocksInArray, StocksInArray.length + 1);
            tempArray[tempArray.length - 1] = newStock;
            StocksInArray = tempArray;
            System.out.println("Stock Added Successfully!");

            if (done.equals("done")) {
                break;
            }
        }
    }

    // All Searching Algorithms Are implemented here
    // Searching by linear search

    public void SearchStock() {
        System.out.println("Which stock you want to search Enter Name of the stock: ");
        String stockSearched = sc.next().toLowerCase();
        boolean found = false;
        for (int i = 0; i < StocksInArray.length; i++) {
            // Searching by linear Search
            if (StocksInArray[i].stockName.toLowerCase().equals(stockSearched)) {
                System.out.println("Stock Found: " + StocksInArray[i].stockName);
                System.out.println("Stock Price: " + StocksInArray[i].stockPrice);
                System.out.println("Stock Quantity: " + StocksInArray[i].stockQuantity);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Stock not found.");
        }
    }

    public void SearchStockBinary() {
        System.out.println("Which stock you want to search Enter Name of the stock: ");
        String stockSearched = sc.next();
        int index = BinarySearchStock.binarySearch(StocksInArray, stockSearched);
        if (index != -1) {
            Stock[] sortedArr = Arrays.copyOf(StocksInArray, StocksInArray.length);
            Arrays.sort(sortedArr, (a, b) -> a.stockName.compareToIgnoreCase(b.stockName));
            System.out.println("Stock Found: " + sortedArr[index].stockName);
            System.out.println("Stock Price: " + sortedArr[index].stockPrice);
            System.out.println("Stock Quantity: " + sortedArr[index].stockQuantity);
        } else {
            System.out.println("Stock not found.");
        }
    }

    // Searching by mergeSort
    // Lowest to Highest price sorting

    class MergeSortStocks {

        // CONQUER: The merging process for Stock array by price
        public static void conquer(Stock[] arr, int si, int mid, int ei) {

            Stock[] mergedArray = new Stock[ei - si + 1];

            int indx1 = si;
            int indx2 = mid + 1;
            int x = 0;

            // Compare elements from both halves by stockPrice
            while (indx1 <= mid && indx2 <= ei) {
                if (arr[indx1].stockPrice <= arr[indx2].stockPrice) {
                    mergedArray[x++] = arr[indx1++];
                } else {
                    mergedArray[x++] = arr[indx2++];
                }
            }

            // Copy remaining elements of the first half
            while (indx1 <= mid) {
                mergedArray[x++] = arr[indx1++];
            }

            // Copy remaining elements of the second half
            while (indx2 <= ei) {
                mergedArray[x++] = arr[indx2++];
            }

            // Copy mergedArray back into the ORIGINAL array
            for (int i = 0, j = si; i < mergedArray.length; i++, j++) {
                arr[j] = mergedArray[i];
            }
        }

        // DIVIDE
        public static void divide(Stock[] arr, int si, int ei) {
            if (si >= ei) {
                return;
            }

            // Correct mid calculation to avoid overflow
            int mid = si + (ei - si) / 2;

            divide(arr, si, mid);
            divide(arr, mid + 1, ei);
            conquer(arr, si, mid, ei);
        }
    }

    // Sorting by bubble sort
    class BubbleSortStock {
        public static void bubble(Stock[] arr) {
            int n = arr.length;
            boolean swapped;

            for (int i = 0; i < n - 1; i++) {
                swapped = false;
                for (int j = 0; j < n - i - 1; j++) {
                    if (arr[j].stockPrice > arr[j + 1].stockPrice) {
                        // Swap Stock objects
                        Stock temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                        swapped = true;
                    }
                }
                // If no swaps in inner loop, array is sorted
                if (!swapped) {
                    break;
                }
            }
        }
    }

    // Sorting by Selection Sort
    class SelectionSort {
        public static void selection(Stock[] arr) {
            int n = arr.length;

            for (int i = 0; i < n - 1; i++) {
                int minIndex = i;
                for (int j = i + 1; j < n; j++) {
                    if (arr[j].stockPrice < arr[minIndex].stockPrice) {
                        minIndex = j;
                    }
                }
                // Swap Stock objects
                Stock temp = arr[minIndex];
                arr[minIndex] = arr[i];
                arr[i] = temp;
            }
        }
    }

    class QuickSortStock {
        public static int partition(Stock[] arr, int low, int high) {
            double pivot = arr[high].stockPrice;
            int i = low - 1;

            for (int j = low; j < high; j++) {
                if (arr[j].stockPrice < pivot) {
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

        public static void quick(Stock[] arr, int low, int high) {
            if (low < high) {
                int pi = partition(arr, low, high);
                quick(arr, low, pi - 1);
                quick(arr, pi + 1, high);
            }
        }
    }

    class BinarySearchStock {
        public static int binarySearch(Stock[] arr, String stockName) {
            Stock[] sortedArr = Arrays.copyOf(arr, arr.length);
            Arrays.sort(sortedArr, (a, b) -> a.stockName.compareToIgnoreCase(b.stockName));

            int left = 0;
            int right = sortedArr.length - 1;

            while (left <= right) {
                int mid = left + (right - left) / 2;
                int comparison = sortedArr[mid].stockName.compareToIgnoreCase(stockName);

                if (comparison == 0) {
                    return mid;
                }
                if (comparison < 0) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return -1;
        }
    }

    class StockNode {
        Stock stock;
        StockNode next;

        StockNode(Stock stock) {
            this.stock = stock;
            this.next = null;
        }
    }

    class LinkedListStock {
        StockNode head;

        public void append(Stock stock) {
            StockNode newNode = new StockNode(stock);
            if (head == null) {
                head = newNode;
                return;
            }
            StockNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }

        public void display() {
            StockNode current = head;
            while (current != null) {
                System.out.println("Stock Name: " + current.stock.stockName);
                System.out.println("Stock Price: " + current.stock.stockPrice);
                System.out.println("Stock Quantity: " + current.stock.stockQuantity);
                System.out.println("---");
                current = current.next;
            }
        }

        public Stock search(String stockName) {
            StockNode current = head;
            while (current != null) {
                if (current.stock.stockName.equalsIgnoreCase(stockName)) {
                    return current.stock;
                }
                current = current.next;
            }
            return null;
        }

        public void delete(String stockName) {
            if (head == null)
                return;

            if (head.stock.stockName.equalsIgnoreCase(stockName)) {
                head = head.next;
                return;
            }

            StockNode current = head;
            while (current.next != null) {
                if (current.next.stock.stockName.equalsIgnoreCase(stockName)) {
                    current.next = current.next.next;
                    return;
                }
                current = current.next;
            }
        }
    }

}

// Main class and method is here to implement the functions
public class app {
    public static void main(String[] args) {
        StockManagement sm = new StockManagement();
        sm.AddStockToPortfolio();
        for (int i = 0; i < sm.StocksInArray.length; i++) {
            System.out.println("Stock Name: " + sm.StocksInArray[i].stockName);
            System.out.println("Stock Price: " + sm.StocksInArray[i].stockPrice);
            System.out.println("Stock Quantity: " + sm.StocksInArray[i].stockQuantity);
        }
        while (true) {
            System.out.println("Do you want to add more stocks? ( yes/no )");
            String resp1 = sm.sc.next();
            if (resp1.equals("yes")) {
                sm.AddStockToPortfolio();
            } else {
                System.out.println("Do you want to search a stock? ( yes/no )");
                String resp2 = sm.sc.next();
                if (resp2.equals("yes")) {
                    System.out.println("Which Search Algorithm you want to use ' LinearSearch / BinarySearch '");
                    String searchChoice = sm.sc.next().toLowerCase();
                    if (searchChoice.equals("linear search") || searchChoice.equals("linearsearch")) {
                        sm.SearchStock();
                    } else if (searchChoice.equals("binary search") || searchChoice.equals("binarysearch")) {
                        sm.SearchStockBinary();
                    }
                }
                System.out.println("Do you want to sort stocks in ascending order by price? ( yes/no )");
                String resp3 = sm.sc.next();
                if (resp3.equals("yes")) {
                    System.out.println(
                            "Which Sorting Algorithm you want to use ' MergeSort / SelectionSort / QuickSort / BubbleSort '");
                    String resp4 = sm.sc.next().toLowerCase();
                    if (resp4.equals("merge sort") || resp4.equals("mergesort")) {
                        StockManagement.MergeSortStocks.divide(sm.StocksInArray, 0, sm.StocksInArray.length - 1);
                        System.out.println("Stocks sorted by price (ascending) using Merge Sort:");
                        for (int i = 0; i < sm.StocksInArray.length; i++) {
                            System.out.println("Stock Name: " + sm.StocksInArray[i].stockName);
                            System.out.println("Stock Price: " + sm.StocksInArray[i].stockPrice);
                            System.out.println("Stock Quantity: " + sm.StocksInArray[i].stockQuantity);
                        }
                    } else if (resp4.equals("bubble sort") || resp4.equals("bubblesort")) {
                        StockManagement.BubbleSortStock.bubble(sm.StocksInArray);
                        System.out.println("Stocks sorted by price (ascending) using Bubble Sort:");
                        for (int i = 0; i < sm.StocksInArray.length; i++) {
                            System.out.println("Stock Name: " + sm.StocksInArray[i].stockName);
                            System.out.println("Stock Price: " + sm.StocksInArray[i].stockPrice);
                            System.out.println("Stock Quantity: " + sm.StocksInArray[i].stockQuantity);
                        }
                    } else if (resp4.equals("selection sort") || resp4.equals("selectionsort")) {
                        StockManagement.SelectionSort.selection(sm.StocksInArray);
                        System.out.println("Stocks sorted by price (ascending) using Selection Sort:");
                        for (int i = 0; i < sm.StocksInArray.length; i++) {
                            System.out.println("Stock Name: " + sm.StocksInArray[i].stockName);
                            System.out.println("Stock Price: " + sm.StocksInArray[i].stockPrice);
                            System.out.println("Stock Quantity: " + sm.StocksInArray[i].stockQuantity);
                        }
                    } else if (resp4.equals("quick sort") || resp4.equals("quicksort")) {
                        StockManagement.QuickSortStock.quick(sm.StocksInArray, 0, sm.StocksInArray.length - 1);
                        System.out.println("Stocks sorted by price (ascending) using Quick Sort:");
                        for (int i = 0; i < sm.StocksInArray.length; i++) {
                            System.out.println("Stock Name: " + sm.StocksInArray[i].stockName);
                            System.out.println("Stock Price: " + sm.StocksInArray[i].stockPrice);
                            System.out.println("Stock Quantity: " + sm.StocksInArray[i].stockQuantity);
                        }
                    }
                }
                System.out.println("Thank you for using the Stock Management System.");
                break;
            }
        }
    }
}
