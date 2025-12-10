package dev.sdey.rtmm.data;

import dev.sdey.rtmm.model.Quote;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public sealed interface MarketDataProvider permits DummyMarketDataProvider{

    Map<String, Quote> getQuotes(List<String> symbols) throws IOException;
}
