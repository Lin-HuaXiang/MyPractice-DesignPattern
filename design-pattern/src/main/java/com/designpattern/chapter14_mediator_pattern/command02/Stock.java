package com.designpattern.chapter14_mediator_pattern.command02;

/**
 * @author linhuaxiang
 * @date   2019/7/10
 */
public class Stock extends AbstractColleague {

    public Stock(AbstractMediator mediator) {
        super(mediator);
    }

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
     * under great inventory pressure, it is necessary to inform the purchasing staff not buy computer
     * and the sales staff to sell as soon as possible
     */
    public void clearStock() {
        System.out.println("clear stock number : " + COMPUTER_NUMBER);
        super.mediator.execute("stock.clear");
    }

}
