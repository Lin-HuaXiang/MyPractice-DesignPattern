package com.designpattern.chapter14_mediator_pattern.command02;

/**
 * @author linhuaxiang
 * @date   2019/7/10
 */
public class Purchase extends AbstractColleague {

    public Purchase(AbstractMediator mediator) {
        super(mediator);
    }

    /**
     * buy IBM computer
     */
    public void buyIbmComputer(int number) {
        super.mediator.execute("purchase.buy", number);
    }

    /**
     * refuse buy
     */
    public void refuseBuyIbm() {
        System.out.println("purchase refuse buy computer");
    }

}
