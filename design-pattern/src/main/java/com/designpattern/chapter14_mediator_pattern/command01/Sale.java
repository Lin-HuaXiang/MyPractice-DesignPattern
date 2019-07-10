package com.designpattern.chapter14_mediator_pattern.command01;

import java.util.Random;

public class Sale {

    /**
     * sell IBM computers
     */
    public void sellIbmComputer(int number) {
        // access to stock
        Stock stock = new Stock();
        // access to purchase staff
        Purchase purchase = new Purchase();
        // the stock number is not enough to sell
        if (stock.getStockNumber() < number) {
            purchase.buyIbmComputer(number);
        }
        System.out.println("sell IBM computers : " + number);
        stock.decrease(number);
    }

    /**
     * sales feedback, between 0 to 100, '0' means nobody's buying, '100' means sale very well, sell on at a time
     */
    public int getSaleStatus() {
        Random rand = new Random(System.currentTimeMillis());
        int saleStatus = rand.nextInt(100);
        System.out.println("IBM computer sales feedback : " + saleStatus);
        return saleStatus;
    }

    /**
     * discount processing
     */
    public void offSale() {
        // sell as many as you have in stock
        Stock stock = new Stock();
        System.out.println("with the condition of off sales, sales " + stock.getStockNumber() + "computers");
    }
}
