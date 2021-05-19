package Lesson_12;

import java.util.Random;
import java.util.TreeMap;

public class OperationService {
    private static final int CACHE_LIMIT = 5;
    private static final int SLEEP_LIMIT = 5000;

    private volatile TreeMap<Integer, CacheValue<Integer>> cache;
    private final Random random = new Random();

    public OperationService () {
        cache = new TreeMap<>();
    }

    public int performLongAngExpensiveOperation (int value) {
        System.out.println("RECEIVED " + value);
        if (!cache.containsKey(value)) {
            if (cache.size() == CACHE_LIMIT) {
                int firstKey = cache.firstKey();
                System.out.println("PAIR (" + firstKey + " ; " + cache.get(firstKey) + ") REMOVED FROM CACHE (MEMORY_LIMIT)");
                cache.remove(firstKey);
            }
            cache.put(
                    value,
                    new CacheValue<>(value * value)
            );
            CacheValue<Integer> addedValue = cache.get(value);
            if (addedValue != null) {
                System.out.println("PAIR (" + value + " ; " + addedValue + ") PUT IN CACHE");
            }
        }

        CacheValue<Integer> cacheValue = cache.get(value);
        int trueValue = Integer.MAX_VALUE;
        if (cacheValue != null) {
            trueValue = cacheValue.getValue();
            if (!cacheValue.isActual()) {
                cache.remove(value);
                String reason = cacheValue.getReason();
                System.out.println("PAIR (" + value + " ; " + trueValue + ") REMOVED FROM CACHE (" + reason + ")");
            }
        }

        int sleepTime = random.nextInt(SLEEP_LIMIT);
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (trueValue == Integer.MAX_VALUE) {
            trueValue = value * value;
        }

        System.out.println("VALUE " + trueValue + " RETURNED AFTER " + sleepTime + " MS");
        return trueValue;
    }
}
