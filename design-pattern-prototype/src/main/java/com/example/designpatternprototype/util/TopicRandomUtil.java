package com.example.designpatternprototype.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TopicRandomUtil {

    public static Topic random(Map<String, String> option, String key)  {
        Set<String> keySet = option.keySet();
        List<String> keyList = new ArrayList<>(keySet);
        Collections.shuffle(keyList);
        Map<String, String> optionNew = new HashMap<>();
        int idx = 0;
        String keyNew = "";
        for (String next : keySet) {
            String randomKey = keyList.get(idx++);
            if (key.equals(next)) {
                keyNew = randomKey;
            }
            optionNew.put(randomKey, option.get(next));
        }
        return new Topic(optionNew, keyNew);
    }
    
}
