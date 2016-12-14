package com.lhmtech.server.scheduleserver.service

import com.lhmtech.server.scheduleserver.domain.Task
import groovy.json.JsonBuilder
import spock.lang.Specification

/**
 * Created by lihe on 16-12-14.
 */
class ScheduleServiceTest extends Specification {
    def "schedule task send message to rabbit"() {
        given:
        ScheduleService scheduleService = new ScheduleService()
        Task mockTask = Mock(Task)
        ScheduleMessagePublisher mockPublisher = Mock(ScheduleMessagePublisher)
        scheduleService.scheduleMessagePublisher = mockPublisher
        JsonBuilder mockJsonBuilder = GroovyMock(JsonBuilder, global: true)
        String taskJson = 'task-json'

        when:
        scheduleService.schedule(mockTask)

        then:
        1 * new JsonBuilder(mockTask) >> mockJsonBuilder
        1 * mockJsonBuilder.toPrettyString() >> taskJson
        1 * mockPublisher.publish(taskJson)
    }
}
