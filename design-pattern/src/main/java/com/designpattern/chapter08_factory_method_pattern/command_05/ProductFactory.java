package com.designpattern.chapter08_factory_method_pattern.command_05;

import com.designpattern.chapter08_factory_method_pattern.command_02.ConcreteProduct1;
import com.designpattern.chapter08_factory_method_pattern.command_02.ConcreteProduct2;
import com.designpattern.chapter08_factory_method_pattern.command_02.Product;

import java.util.HashMap;
import java.util.Map;

public class ProductFactory {

    private static final Map<String, Product> prMap = new HashMap<>();
    public static synchronized Product createProduct(String type) throws Exception {
        Product product = null;
        // 如果Map中已经有这个对象
        if (prMap.containsKey(type)) {
            product = prMap.get(type);
        } else {
            if (type.equals("Product1")) {
                product = new ConcreteProduct1();
            } else {
                product = new ConcreteProduct2();
            }
            // 同时把对象放到缓存容器中
            prMap.put(type, product);
        }
        return product;
    }
}
