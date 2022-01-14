package com.example.streamer.serialization.json;

import com.example.streamer.model.Stock;
import com.example.streamer.model.UserInterest;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;

public class JsonSerdes {

    public static Serde<Stock> Stock(){
        JsonSerializer<Stock> jsonSerializer = new JsonSerializer<>();
        JsonDeserializer<Stock> jsonDeserializer = new JsonDeserializer<>(Stock.class);
        return Serdes.serdeFrom(jsonSerializer, jsonDeserializer);
    }

    public static Serde<UserInterest> UserInterest(){
        JsonSerializer<UserInterest> jsonSerializer = new JsonSerializer<>();
        JsonDeserializer<UserInterest> jsonDeserializer = new JsonDeserializer<>(UserInterest.class);
        return Serdes.serdeFrom(jsonSerializer, jsonDeserializer);
    }
}
