package org.example;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

import java.util.Random;

public class HazelcastOps {
    private final HazelcastInstance hazelcastInstance;
    private final IMap<Integer, Integer> map;

    public HazelcastOps() {
        this.hazelcastInstance = Hazelcast.newHazelcastInstance();
        this.map = hazelcastInstance.getMap("numbers");
    }

    public long putRandomNumbers(int count) {
        long startTime = System.nanoTime();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            int randomNumber = random.nextInt(count);
            map.put(i, randomNumber);
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000;
    }

    public long getRandomNumbers(int count) {
        long startTime = System.nanoTime();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            int randomKey = random.nextInt(count);
            map.get(randomKey);
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000;
    }

    public void shutdown() {
        hazelcastInstance.shutdown();
    }
}