package com.designpattern.chapter14_mediator_pattern.command01;

public class Stock {

    /**
     * we started with 100 computers
     */
    private static int COMPUTER_NUMBER = 100;

    /**
     * increase in stock number
     */
    public void increase(int number) {
        COMPUTER_NUMBER = COMPUTER_NUMBER + number;
        System.out.println("inventories : " + COMPUTER_NUMBER);
    }

    /**
     * reduce stock number
     */
    public void decrease(int number) {
        COMPUTER_NUMBER = COMPUTER_NUMBER - number;
        System.out.println("inventories : " + COMPUTER_NUMBER);
    }

    /**
     * get stock number
     */
    public int getStockNumber() {
        return COMPUTER_NUMBER;
    }

    /**
     * under great inventory pressure, it is necessary to inform the purchasing staff not to purchase
     * and the sales staff to sell as soon as possible
     */
    public void clearStock() {
        Purchase purchase = new Purchase();
        Sale sale = new Sale();
        System.out.println("clear stock number : " + COMPUTER_NUMBER);
        // ask for a discount
        sale.offSale();
        // ask the purchasing staff no to purchase
        purchase.refuseBuyIbm();
    }

}
