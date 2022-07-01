package com.java.family.shardingjdbc004.algorithm;

import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author 公众号：码猿技术专栏
 * @url: www.java-family.cn
 * @description hint分片算法，强制路由，需要结合HintManager使用
 */
public class TablesHintShardingAlgorithm implements HintShardingAlgorithm<Integer> {
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, HintShardingValue<Integer> shardingValue) {
        //逻辑表，t_order
        String logicTableName = shardingValue.getLogicTableName();

        //分片值 values=[1]
        Collection<Integer> values = shardingValue.getValues();
        //t_order_1/t_order_2,t_order_1 and t_order_2   -》t_order_1
        return values.stream().map(value-> MessageFormat.format(logicTableName+"_{0}",value)).collect(Collectors.toSet());
    }
}
