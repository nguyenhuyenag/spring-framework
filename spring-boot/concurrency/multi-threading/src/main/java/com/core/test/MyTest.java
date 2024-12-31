package com.core.test;

import org.junit.Test;

import java.util.*;

public class MyTest {

    @Test(expected = ConcurrentModificationException.class)
    public void whenRemoveAndAddOnHashMap_thenConcurrentModificationError() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "baeldung");
        map.put(2, "HashMap");
        Map<Integer, String> synchronizedMap = Collections.synchronizedMap(map);
        Iterator<Map.Entry<Integer, String>> iterator = synchronizedMap.entrySet().iterator();
        while (iterator.hasNext()) {
            synchronizedMap.put(3, "Modification");
            iterator.next();
        }
    }

}
