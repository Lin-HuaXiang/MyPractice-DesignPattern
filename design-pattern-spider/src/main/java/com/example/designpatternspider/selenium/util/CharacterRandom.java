package com.example.designpatternspider.selenium.util;

import java.util.Random;

public class CharacterRandom {
    
    private static Character[] character = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    
    public static String random(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int nextInt = random.nextInt(26);
            sb.append(character[nextInt]);
        }
        return sb.toString().toUpperCase();
    }

    public static void main(String[] args) {
        System.out.println(CharacterRandom.random(5));
    }
}
