package com.lhmtech.server.scheduleserver.service

import com.lhmtech.server.scheduleserver.domain.SupportedTasks
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

    def "create simple task"() {
        given:
        ScheduleService scheduleService = new ScheduleService()

        when:
        Task task = scheduleService.createSimpleTask("WHAT")

        then:
        task
        task.id?.trim()
        task.taskName == "WHAT"
        task.dateCreated?.trim()
        task.creator == "ScheduleService"
    }

    def "request update companies"() {
        given:
        ScheduleService scheduleService = new ScheduleService()
        Task mockTask = Mock(Task)
        Boolean createSimpleTaskCalled = false
        Boolean scheduleCalled = false
        scheduleService.metaClass.createSimpleTask = {
            String name ->
                assert name == SupportedTasks.UPDATE_COMPANY
                createSimpleTaskCalled = true
                mockTask
        }
        scheduleService.metaClass.schedule = {
            Task task ->
                assert task == mockTask
                scheduleCalled = true
        }

        when:
        scheduleService.updateCompanies()

        then:
        createSimpleTaskCalled
        scheduleCalled

    }
}



