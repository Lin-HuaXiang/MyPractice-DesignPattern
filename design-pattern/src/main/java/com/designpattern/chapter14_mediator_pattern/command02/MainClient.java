package com.designpattern.chapter14_mediator_pattern.command02;

public class MainClient {

    public static void main(String[] args) {
        AbstractMediator mediator = new Mediator();
        // purchase staff buy computer
        System.out.println("--- purchase staff buy computer ---");
        Purchase purchase = new Purchase(mediator);
        purchase.buyIbmComputer(100);
        // sale staff sell computer
        System.out.println("\n--- sale staff sell computer ---");
        Sale sale = new Sale(mediator);
        sale.sellIbmComputer(1);
        // stock manager manage stock
        System.out.println("\n--- stock need to clear ---");
        Stock stock = new Stock(mediator);
        stock.clearStock();

    }
}
