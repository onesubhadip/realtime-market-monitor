package dev.sdey.rtmm.data;

import dev.sdey.rtmm.model.Quote;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public final class DummyMarketDataProvider implements MarketDataProvider{

    private final Map<String, Double> lastPrices = new HashMap<>();
    private final Map<String, Double> openPrices = new HashMap<>();
    private final Random random = new Random();

    public DummyMarketDataProvider(List<String> symbols) {
        for (String symbol: symbols) {
            double base = 50 +  random.nextDouble(150);
            lastPrices.put(symbol, base);
            openPrices.put(symbol, base);
        }
    }

    @Override
    public Map<String, Quote> getQuotes(List<String> symbols) {
        Map<String, Quote> quotes = new HashMap<>();
        for(var symbol : symbols) {
            double previous = lastPrices.get(symbol);
            double factor = 0.995 * random.nextDouble() * 0.01;
            double last = previous + factor;
            lastPrices.put(symbol, last);

            long volume = 1000 + random.nextInt(99000);

            var quote = new Quote(
                    symbol,
                    roundToCents(last),
                    roundToCents(openPrices.get(symbol)),
                    volume,
                    Instant.now()
            );

            quotes.put(symbol, quote);
        }
        return quotes;
    }

    private double roundToCents(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
