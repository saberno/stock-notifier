package com.example.streamer.util;

import com.example.streamer.model.Stock;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.streams.processor.TimestampExtractor;
import java.time.ZoneOffset;

public class StockTimeExtractor implements TimestampExtractor {

    @Override
    public long extract(ConsumerRecord<Object, Object> consumerRecord, long l) {
        Stock stock = (Stock)consumerRecord.value();
        if (stock.getCreatedDate() != null){
            return stock.getCreatedDate().toInstant(ZoneOffset.UTC).toEpochMilli();
        }
        return l;
    }
}
