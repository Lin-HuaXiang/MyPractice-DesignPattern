package com.designpattern.chapter14_mediator_pattern.command01;

/**
 * @author linhuaxiang
 * @date   2019/7/9
 */
public class MainClient {

    public static void main(String[] args) {
        // purchasing staff purchase computer
        System.out.println("--- purchasing staff purchase computer ---");
        Purchase purchase = new Purchase();
        purchase.buyIbmComputer(100);
        // sales staff selling computer
        System.out.println("\n--- sales staff selling computer ---");
        Sale sale = new Sale();
        sale.sellIbmComputer(1);
        // stock staff manages stock number
        System.out.println("\n--- stock staff manages stock number");
        Stock stock = new Stock();
        stock.clearStock();
    }
}
