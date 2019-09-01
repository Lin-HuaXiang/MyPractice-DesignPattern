package com.designpattern.chapter14_mediator_pattern.command02;

/**
 * @author linhuaxiang
 * @date 2019/7/10
 */
public class Mediator extends AbstractMediator {

    /**
     * the mediator's most important method
     */
    @Override
    public void execute(String str, Object... objects) {
        String purchaseBuy = "purchase.buy";
        String saleSell = "sale.sell";
        String saleOffSell = "sale.offsell";
        String stockClear = "stock.clear";
        if (purchaseBuy.equalsIgnoreCase(str)) {
            this.buyComputer((Integer) objects[0]);
        } else if (saleSell.equalsIgnoreCase(str)) {
            this.sellComputer((Integer) objects[0]);
        } else if (saleOffSell.equalsIgnoreCase(str)) {
            this.offSale();
        } else if (stockClear.equalsIgnoreCase(str)) {
            this.clearStock();
        }
    }

    /**
     * buy computer
     */
    private void buyComputer(int number) {
        int saleStatus = super.sale.getSaleStatus();
        // good sales
        if (saleStatus > 80) {
            System.out.println("purchasing computers : " + number);
            super.stock.increase(number);
        } else {
            // poor sales, binary purchase
            int buyNumber = number / 2;
            super.stock.increase(buyNumber);
            System.out.println("purchasing computers : " + buyNumber);
        }
    }

    /**
     * sell computer
     */
    private void sellComputer(int number) {
        if (stock.getStockNumber() < number) {
            super.purchase.buyIbmComputer(number);
        }
        super.stock.decrease(number);
    }

    /**
     * offSell
     */
    public void offSale() {
        // sell as many as you have in stock
        System.out.println("with the condition of off sales, sales " + super.stock.getStockNumber() + "computers");
    }

    /**
     * clear stock number
     */
    private void clearStock() {
        // require clear stock to sell
        super.sale.offSale();
        // require purchase refuse buy
        super.purchase.refuseBuyIbm();
    }
}
