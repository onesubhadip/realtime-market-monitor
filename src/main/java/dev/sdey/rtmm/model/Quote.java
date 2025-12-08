package dev.sdey.rtmm.model;

import java.time.Instant;

public record Quote(
        String symbol, // The ticker name investors use to identify an asset (AAPL, TSLA, BTC-USD).
        double lastPrice, // The most recent traded price.
        double openPrice, // The price when the market opened on this trading day (used for intraday % change).
        long volume, // The total number of shares/contracts traded so far today. It measures activity/liquidity.
        Instant timestamp // When this quote snapshot was taken
) {
    public double pctChange() {
        if(openPrice == 0) return 0.0;
        return (lastPrice - openPrice) / openPrice * 100;
    }
}
