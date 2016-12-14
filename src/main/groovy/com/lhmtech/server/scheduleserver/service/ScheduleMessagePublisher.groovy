package com.lhmtech.server.scheduleserver.service

import com.lhmtech.integration.messaging.rabbit.BaseMessagePublisher

/**
 * Created by lihe on 16-12-14.
 */
class ScheduleMessagePublisher extends BaseMessagePublisher {
    @Override
    String getExchange() {
        return null
    }
}
