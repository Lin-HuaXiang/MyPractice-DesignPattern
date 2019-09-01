package com.designpattern.chapter18_strategy_pattern.command03;

/**
 * @author linhuaxiang
 * @date 2019/7/16
 */
public enum Calculator {

    /**
     *
     */
    ADD("+") {
        @Override
        public int exec(int a, int b) {
            return a + b;
        }
    },

    /**
     *
     */
    SUB("-") {
        @Override
        public int exec(int a, int b) {
            return a - b;
        }
    };
    String value = "";

    private Calculator(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public abstract int exec(int a, int b);
}
