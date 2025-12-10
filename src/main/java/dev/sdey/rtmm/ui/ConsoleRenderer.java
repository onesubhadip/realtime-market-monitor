package dev.sdey.rtmm.ui;

import dev.sdey.rtmm.model.Quote;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Map;

public final class ConsoleRenderer {

    private static final DateTimeFormatter TIME_FORMATTER =
            DateTimeFormatter.ISO_LOCAL_TIME.withZone(ZoneOffset.UTC);

    private ConsoleRenderer() {
    }

    public static void render(Map<String, Quote> quotes) {
        // clear screen
        System.out.print("\033[H\033[2J");
        System.out.flush();

        var now = quotes.values().stream()
                .findAny()
                .map(Quote::timestamp)
                .orElseGet(Instant::now);

        System.out.println("Real-Time Market Monitor - " + TIME_FORMATTER.format(now) + " UTC");
        System.out.println("----------------------------------------------------------------------");
        System.out.printf("%-8s %10s %10s %10s %10s%n",
                "SYMBOL", "LAST", "OPEN", "CHANGE%", "VOLUME");
        System.out.println("----------------------------------------------------------------------");

        quotes.values().stream()
                .sorted(Comparator.comparingDouble(Quote::pctChange).reversed())
                .forEach(ConsoleRenderer::printRow);

        System.out.println("----------------------------------------------------------------------");
    }

    private static void printRow(Quote q) {
        String changeFormatted = String.format("%+.2f%%", q.pctChange());
        System.out.printf("%-8s %10.2f %10.2f %10s %10d%n",
                q.symbol(),
                q.lastPrice(),
                q.openPrice(),
                changeFormatted,
                q.volume());
    }
}
