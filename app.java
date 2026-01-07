// Agenda Of the Project 
// In this code we are going to let user add stocks in the array
// User can add stocks
// User can search stocks
// User can buy stocks and sell stocks
// User can see all stocks based on ascending order




import java.util.*;

class Stock{
    String stockName;
    double stockPrice;
    int stockQuantity;

    Stock(String stockName, double stockPrice, int stockQuantity){
        this.stockName = stockName;
        this.stockPrice = stockPrice;
        this.stockQuantity = stockQuantity;
    }
}

// Stocks are adding in the portfolio here


class StockManagement{
    Stock[] StocksInArray = new Stock[0];
    Scanner sc = new Scanner(System.in);
    public void AddStockToPortfolio(){
        while(true){
        String stockName = "";
        double stockPrice = 0;
        int stockQuantity = 0;
        try {
        System.out.println("Enter Stock Name: ");
        stockName = sc.next();
        } catch (Exception e) {
            System.out.println("Invalid input. Please try again.");
            sc.next(); 
            continue;
        }
        try {
            System.out.println("Enter Stock Price: ");
            stockPrice = sc.nextDouble();
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid price.");
            sc.next(); 
            continue;
        }
        try {
            System.out.println("Enter Stock Quantity: ");
        stockQuantity = sc.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid quantity.");
            sc.next(); 
            continue;
        }
        
        System.out.println("Type done to finish adding stocks");
        String done = sc.next();

        //Creating a new Stock Object
        Stock newStock = new Stock(stockName, stockPrice , stockQuantity);
        Stock[] tempArray = Arrays.copyOf(StocksInArray, StocksInArray.length + 1);
        tempArray[tempArray.length - 1] = newStock;
        StocksInArray = tempArray;
        System.out.println("Stock Added Successfully!");

        if(done.equals("done")){
            break;
        }
    }
}




// All Searching Algorithms Are implemented here


    public void SearchStock(){
        System.out.println("Which stock you want to search Enter Name of the stock: ");
        String stockSearched = sc.next().toLowerCase();
        boolean found = false;
        for(int i = 0; i < StocksInArray.length; i++){
            // Searching by linear Search
            if(StocksInArray[i].stockName.toLowerCase().equals(stockSearched)){
                System.out.println("Stock Found: " + StocksInArray[i].stockName);
                System.out.println("Stock Price: " + StocksInArray[i].stockPrice);
                System.out.println("Stock Quantity: " + StocksInArray[i].stockQuantity);
                found = true;
                break;
            }
        }
        if(!found){
            System.out.println("Stock not found.");
        }
    }
}





// Main class and method is here to implement the functions
public class app{
    public static void main(String[] args) {
        StockManagement sm = new StockManagement();
        sm.AddStockToPortfolio();
        for( int i = 0 ; i < sm.StocksInArray.length; i ++){
            System.out.println("Stock Name: " + sm.StocksInArray[i].stockName);
            System.out.println("Stock Price: " + sm.StocksInArray[i].stockPrice);
            System.out.println("Stock Quantity: " + sm.StocksInArray[i].stockQuantity);
        }
        while(true){
        System.out.println("Do you want to add more stocks? ( yes/no )");
        String resp1 = sm.sc.next();
        if(resp1.equals("yes")){
            sm.AddStockToPortfolio();
        }
        else{
            System.out.println("Do you want to search a stock? ( yes/no )");
            String resp2 = sm.sc.next();
            if(resp2.equals("yes")){
                sm.SearchStock();
            }
            else{
                System.out.println("Thank you for using the Stock Management System.");
                break;
            }
        }
}
    }
}