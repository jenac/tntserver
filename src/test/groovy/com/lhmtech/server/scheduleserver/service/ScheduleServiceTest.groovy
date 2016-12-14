package com.lhmtech.server.scheduleserver.service

import com.lhmtech.server.scheduleserver.domain.Task
import groovy.json.JsonBuilder
import org.slf4j.Logger
import spock.lang.Specification

/**
 * Created by lihe on 16-12-14.
 */
class ScheduleServiceTest extends Specification {
    def "schedule task send message to rabbit"() {
        given:
        ScheduleService scheduleService = new ScheduleService()
        Logger mockLogger = Mock(Logger)
        ScheduleMessagePublisher mockPublisher = Mock(ScheduleMessagePublisher)
        scheduleService.scheduleMessagePublisher = mockPublisher
        scheduleService.logger = mockLogger
        Task mockTask = Mock(Task)
        JsonBuilder mockJsonBuilder = GroovyMock(JsonBuilder, global: true)
        String taskJson = 'task-json'

        when:
        scheduleService.schedule(mockTask)

        then:
        1 * new JsonBuilder(mockTask) >> mockJsonBuilder
        1 * mockJsonBuilder.toPrettyString() >> taskJson
        1 * mockPublisher.publish(taskJson)
        1 * mockLogger.info("scheduled task: task-json")
    }
}
