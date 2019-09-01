package com.designpattern.chapter14_mediator_pattern.command02;

import java.util.Random;

public class Sale extends AbstractColleague {

    public Sale(AbstractMediator mediator) {
        super(mediator);
    }

    /**
     * sell computer
     */
    public void sellIbmComputer(int number) {
        super.mediator.execute("sale.sell", number);
        System.out.println("sell IBM computers : " + number);
    }

    /**
     * sales feedback, between 0 to 100, '0'means nobody's buying, '100' means sell vary sell, sell on at a time
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
        super.mediator.execute("sale.offsell");
    }
}
