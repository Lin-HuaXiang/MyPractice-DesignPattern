package com.designpattern.chapter09_AbstractFactoryPattern.command_01;

public class MaleFactory implements HumanFactory {

    public Human createBlackHuman() {
        return new MaleBlackHuman();
    }

    public Human createWhiteHuman() {
        return new MaleWhiteHuman();
    }

    public Human createYellowHuman() {
        return new MaleYellowHuman();
    }
}
