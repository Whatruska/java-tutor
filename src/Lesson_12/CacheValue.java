package Lesson_12;

public class CacheValue<T> {
    private static final int REQUEST_LIMIT = 3;
    private static final long TIME_LIMIT = 5000;

    private final T value;
    private int requestCount;
    private final long timestamp;

    public CacheValue (T value) {
        this.value = value;
        this.requestCount = 0;
        this.timestamp = System.currentTimeMillis();
    }

    public T getValue() {
        requestCount++;
        return value;
    }

    public boolean isActual () {
        long currentTimestamp = System.currentTimeMillis();
        return requestCount < REQUEST_LIMIT && (currentTimestamp - timestamp) < TIME_LIMIT;
    }

    public String getReason () {
        long currentTimestamp = System.currentTimeMillis();
        if (requestCount >= REQUEST_LIMIT) {
            return "REQUEST_LIMIT";
        } else if ((currentTimestamp - timestamp) >= TIME_LIMIT) {
            return "TIME_LIMIT";
        } else {
            return "ВСЕ ОК, ЧЕ ДОЕБАЛСЯ?";
        }
    }

    @Override
    public String toString() {
        return value + "";
    }
}
