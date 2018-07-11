package com.personal.kafka.optimization;

/**
 * @Author: Jacknolfskin
 * @Date: 2018/11/25 10:01
 * @Path: com.personal.config
 */
public class ConsumerFuture {
    private Integer totalCount ;
    private Long TotalTime ;

    public ConsumerFuture(Integer totalCount, Long totalTime) {
        this.totalCount = totalCount;
        TotalTime = totalTime;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Long getTotalTime() {
        return TotalTime;
    }

    public void setTotalTime(Long totalTime) {
        TotalTime = totalTime;
    }
}
