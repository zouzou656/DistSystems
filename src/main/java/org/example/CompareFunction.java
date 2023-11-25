package org.example;

import org.apache.flink.api.common.functions.RichFlatMapFunction;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.util.Collector;

class CompareFunction extends RichFlatMapFunction<StockData, String> {
    private static final long serialVersionUID = 1L;

    private transient ValueState<Double> previousOpenState;

    @Override
    public void open(Configuration parameters) throws Exception {
        previousOpenState = getRuntimeContext().getState(new ValueStateDescriptor<>("previousOpen", Double.class));
    }

    @Override
    public void flatMap(StockData currentStockData, Collector<String> collector) throws Exception {
        Double prevOpen = previousOpenState.value();

        // Check if there is a previous value to compare
        if (prevOpen != null) {
            double currentOpen = Double.parseDouble(currentStockData.getOpen());
            double percentageChange = (currentOpen - prevOpen) / prevOpen * 100;

            // Compare and collect if the change is more than 10%
            if (percentageChange > 10.0) {
                collector.collect("Change in open value more than 10%: " + currentStockData.toString()+"\n" +
                                  "previous : "+prevOpen);

            }
        }

        // Update the state with the current open value
        previousOpenState.update(Double.parseDouble(currentStockData.getOpen()));
    }
}