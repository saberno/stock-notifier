package com.example.streamer.streamer;

import com.example.streamer.model.CombinedStock;
import com.example.streamer.model.Stock;
import com.example.streamer.model.UserInterest;
import com.example.streamer.serialization.json.JsonSerdes;
import com.example.streamer.util.StockTimeExtractor;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Properties;

public class Streamer {

    public void startStreaming(){
        StreamsBuilder streamsBuilder = new StreamsBuilder();

        //consume stocks
        Consumed<String, Stock> stockConsumerOption = Consumed.with(Serdes.String(), JsonSerdes.Stock())
                        .withTimestampExtractor(new StockTimeExtractor());

        KStream<String, Stock> stockStream = streamsBuilder.stream("stocks", stockConsumerOption);
        TimeWindows tumblingWindow = TimeWindows.of(Duration.ofSeconds(60L)).grace(Duration.ofSeconds(5L));

        ValueMapper<Stock, CombinedStock> mapStockToCombinedStock = value -> new CombinedStock(value.getStockValue(), BigDecimal.ZERO, value.getSymbol());
        Reducer<CombinedStock> combinedStockReducer = (combinedStock1 , combinedStock2) -> new CombinedStock(
                combinedStock2.getCurrentValue(),
                combinedStock1.getDifferenceValue().add(combinedStock2.getCurrentValue().min(combinedStock1.getCurrentValue())),
                combinedStock2.getSymbol()
        );
        KTable<Windowed<String>, CombinedStock> combinedStockTable =
                stockStream.mapValues(mapStockToCombinedStock).groupByKey().windowedBy(tumblingWindow)
                        .reduce(combinedStockReducer)
                        .suppress(Suppressed.untilWindowCloses(Suppressed.BufferConfig.unbounded().shutDownWhenFull()));

        // consume user interests
        Consumed<String, UserInterest> userInterestConsumerOption = Consumed.with(Serdes.String(), JsonSerdes.UserInterest());
        KTable<String, UserInterest> userInterestKTable = streamsBuilder.table("user_interest", userInterestConsumerOption);
    }

    private Properties getProperties(){
        Properties properties = new Properties();
        properties.setProperty(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.setProperty(StreamsConfig.APPLICATION_ID_CONFIG, "notifier");
        return properties;
    }
}
