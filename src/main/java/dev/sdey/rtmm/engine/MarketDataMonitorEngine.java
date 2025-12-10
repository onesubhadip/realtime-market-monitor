package dev.sdey.rtmm.engine;

import dev.sdey.rtmm.data.MarketDataProvider;
import dev.sdey.rtmm.ui.ConsoleRenderer;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class MarketDataMonitorEngine {

    private final MarketDataProvider provider;
    private final List<String> symbols;
    private final Duration refreshInterval;

    public MarketDataMonitorEngine(
            MarketDataProvider provider,
            List<String> symbols,
            Duration refreshInterval
            ) {
        this.provider = provider;
        this.symbols = symbols;
        this.refreshInterval = refreshInterval;
    }

    public void run() throws InterruptedException {
        while(true) {
            try {
                var quotes = provider.getQuotes(symbols);
                ConsoleRenderer.render(quotes);
            }
            catch (IOException e) {
                System.err.println("Error while getting quotes" + e.getMessage());
            }
            Thread.sleep(refreshInterval.toMillis());
        }

    }


}
