package dev.sdey.rtmm;

import dev.sdey.rtmm.data.DummyMarketDataProvider;
import dev.sdey.rtmm.engine.MarketDataMonitorEngine;

import java.time.Duration;
import java.util.List;

public class Main {
    private static final List<String> DEFAULT_SYMBOLS =
            List.of("AAPL", "TSLA", "MSFT", "GOOG", "AMZN");

    public static void main(String[] args) throws InterruptedException {
        var symbols = DEFAULT_SYMBOLS;
        var refresh = Duration.ofSeconds(3);

        var provider = new DummyMarketDataProvider(symbols);
        var engine = new MarketDataMonitorEngine(provider, symbols, refresh);
        engine.run();
    }
}
