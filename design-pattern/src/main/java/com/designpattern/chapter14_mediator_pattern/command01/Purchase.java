package com.designpattern.chapter14_mediator_pattern.command01;

public class Purchase {

    /**
     * buy IBM computes
     */
    public void buyIbmComputer(int number) {
        // assess to database
        Stock stock = new Stock();
        // assess sales
        Sale sale = new Sale();
        // sell feedback
        int saleStatus = sale.getSaleStatus();
        int goodSalesNumber = 50;
        if (saleStatus > goodSalesNumber) {
            // good sales
            System.out.println("purchasing computers : " + number);
            stock.increase(number);
        } else {
            // poor sales, binary purchase
            int buyNumber = number / 2;
            System.out.println("purchasing computers : " + buyNumber);
            stock.increase(buyNumber);
        }
    }

    /**
     * refuse buy IBM computer
     */
    public void refuseBuyIbm() {
        System.out.println("refuse buy IBM computer");
    }
}
