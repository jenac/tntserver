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
    def "schedule simple task"() {
        given:
        ScheduleService scheduleService = new ScheduleService()
        Logger mockLogger = Mock(Logger)
        ScheduleMessagePublisher mockPublisher = Mock(ScheduleMessagePublisher)
        scheduleService.scheduleMessagePublisher = mockPublisher
        scheduleService.logger = mockLogger
        Task mockTask = GroovyMock(Task)
        JsonBuilder mockJsonBuilder = GroovyMock(JsonBuilder, global: true)
        String taskJson = 'task-json'
        String taskName = 'the-task-name'
        boolean createSimpleTaskCalled = false
        scheduleService.metaClass.createSimpleTask = {
            String name ->
                assert name == taskName
                createSimpleTaskCalled = true
                mockTask
        }

        when:
        scheduleService.scheduleSimpleTask(taskName)

        then:
        createSimpleTaskCalled
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

    def "update companies"() {
        given:
        ScheduleService scheduleService = new ScheduleService()
        Boolean scheduleSimpleTaskCalled = false

        scheduleService.metaClass.scheduleSimpleTask = {
            String name ->
                assert name == SupportedTasks.UPDATE_COMPANY
                scheduleSimpleTaskCalled = true
        }

        when:
        scheduleService.updateCompanies()

        then:
        scheduleSimpleTaskCalled
    }

    def "update daily eods"() {
        given:
        ScheduleService scheduleService = new ScheduleService()
        Boolean scheduleSimpleTaskCalled = false

        scheduleService.metaClass.scheduleSimpleTask = {
            String name ->
                assert name == SupportedTasks.UPDATE_DAILY_EOD
                scheduleSimpleTaskCalled = true
        }

        when:
        scheduleService.updateDailyEods()

        then:
        scheduleSimpleTaskCalled
    }

    def "update weekly eods"() {
        given:
        ScheduleService scheduleService = new ScheduleService()
        Boolean scheduleSimpleTaskCalled = false

        scheduleService.metaClass.scheduleSimpleTask = {
            String name ->
                assert name == SupportedTasks.UPDATE_WEEKLY_EOD
                scheduleSimpleTaskCalled = true
        }

        when:
        scheduleService.updateWeeklyEods()

        then:
        scheduleSimpleTaskCalled
    }
}



