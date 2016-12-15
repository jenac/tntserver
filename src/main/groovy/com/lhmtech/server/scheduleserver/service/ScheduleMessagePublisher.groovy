package com.lhmtech.server.scheduleserver.service

import com.lhmtech.integration.messaging.rabbit.BaseMessagePublisher
import org.springframework.beans.factory.annotation.Value

/**
 * Created by lihe on 16-12-14.
 */
class ScheduleMessagePublisher extends BaseMessagePublisher {
    @Value('${task.scheduleSimpleTask.publisher.exchange}')
    String exchangeName
    @Override
    String getExchange() {
        return exchangeName
    }
}
