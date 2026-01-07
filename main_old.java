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
class StockManagement{
    Stock[] StocksInArray = new Stock[0];
    Scanner sc = new Scanner(System.in);
    public void AddStockToPortfolio(){
        while(true){
        System.out.println("Enter Stock Name: ");
        String stockName = sc.next();
        System.out.println("Enter Stock Price: ");
        double stockPrice = sc.nextDouble();
        System.out.println("Enter Stock Quantity: ");
        int stockQuantity = sc.nextInt();
        System.out.println("Type done to finish adding stocks");
        String done = sc.next();

        //Create a new Stock Object
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
public class main{
    public static void main(String[] args) {
        StockManagement sm = new StockManagement();
        //sm.AddStockToPortfolio();
        for( int i = 0 ; i < sm.StocksInArray.length; i ++){
            System.out.println("Stock Name: " + sm.StocksInArray[i].stockName);
            System.out.println("Stock Price: " + sm.StocksInArray[i].stockPrice);
            System.out.println("Stock Quantity: " + sm.StocksInArray[i].stockQuantity);
        }
    }
}