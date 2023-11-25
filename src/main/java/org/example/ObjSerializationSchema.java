package org.example;
import org.apache.flink.api.common.serialization.SerializationSchema;
import org.apache.flink.connector.kafka.sink.KafkaRecordSerializationSchema;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.flink.streaming.connectors.kafka.KafkaSerializationSchema;
import org.apache.kafka.clients.producer.ProducerRecord;

import javax.annotation.Nullable;


public class ObjSerializationSchema implements  KafkaRecordSerializationSchema<StockData> {

    private String topic;
    private ObjectMapper mapper;

    public ObjSerializationSchema(String topic) {
        super();
        this.topic = topic;
    }


    @Override
    public void open(SerializationSchema.InitializationContext context, KafkaSinkContext sinkContext) throws Exception {
        KafkaRecordSerializationSchema.super.open(context, sinkContext);
    }

    @Nullable
    @Override
    public ProducerRecord<byte[], byte[]> serialize(StockData stockData, KafkaSinkContext kafkaSinkContext, Long aLong) {
        byte[] b = null;
        if (mapper == null) {
            mapper = new ObjectMapper();
        }
        try {
            b= mapper.writeValueAsBytes(stockData);
        } catch (JsonProcessingException e) {
            // TODO
        }
        return new ProducerRecord<byte[], byte[]>(topic, b);
    }
}