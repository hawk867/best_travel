package com.danielesteban.best_travel.util;

import java.time.LocalDateTime;
import java.util.Random;

public class BestTravelUtil {

    private static final Random RANDOM = new Random();

    public static LocalDateTime getRandomSoon() {
        var randoHours = RANDOM.nextInt(5-2) + 2;
        var now = LocalDateTime.now();
        return now.plusHours(randoHours);
    }
    public static LocalDateTime getRandomLater() {
        var randoHours = RANDOM.nextInt(12-6) + 6;
        var now = LocalDateTime.now();
        return now.plusHours(randoHours);
    }
}
