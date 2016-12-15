package com.lhmtech.server.scheduleserver.service

import com.lhmtech.server.scheduleserver.domain.SupportedTasks
import com.lhmtech.server.scheduleserver.domain.Task
import groovy.json.JsonBuilder
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled

/**
 * Created by lihe on 16-12-14.
 */
class ScheduleService {
    Logger logger = LoggerFactory.getLogger(ScheduleService.class)

    @Autowired
    ScheduleMessagePublisher scheduleMessagePublisher

    void schedule(Task task) {
        String taskJson = new JsonBuilder(task).toPrettyString()
        scheduleMessagePublisher.publish(taskJson)
        logger.info("scheduled task: ${taskJson}")
    }

    Task createSimpleTask(String name) {
        new Task(
                id: UUID.randomUUID().toString(),
                taskName: name,
                dateCreated: new Date().format('yyyy-MM-dd HH:mm:ss'),
                creator: 'ScheduleService'
        )
    }
    @Scheduled(cron = "0 0 10 * * ?") //Every day at 10:00
    void updateCompanies() {
        schedule(createSimpleTask(SupportedTasks.UPDATE_COMPANY))
    }
/*
    @Scheduled(cron = "0 30 20 * * ?") //Every day at 20:30
    void requestUpdateDailyEods() {
        logger.info("Request update daily eod")
        requestService.createRequest(Constants.UPDATE_DAILY_EOD, scheduleCreator)
    }

    @Scheduled(cron = "0 30 21 * * ?") //Every day at 21:30
    void requestUpdateDailyIods() {
        logger.info("Request update daily iod")
        requestService.createRequest(Constants.UPDATE_DAILY_IOD, scheduleCreator)
    }

    @Scheduled(cron = "0 00 22 * * ?") //Every day at 22:00
    void requestPublishDailyIods() {
        logger.info("Request publish daily Iod")
        requestService.createRequest(Constants.PUBLISH_DAILY_IOD, scheduleCreator)
    }

    @Scheduled(cron = "0 30 10 * * SAT") //Every Sat at 10:30
    void requestUpdateWeeklyEods() {
        logger.info("Request update weekly eod")
        requestService.createRequest(Constants.UPDATE_WEEKLY_EOD, scheduleCreator)
    }

    @Scheduled(cron = "0 0 12 * * SAT") //Every Sat at 12:00
    void requestUpdateWeeklyIods() {
        logger.info("Request update weekly Iod")
        requestService.createRequest(Constants.UPDATE_WEEKLY_IOD, scheduleCreator)
    }

    @Scheduled(cron = "0 0 14 * * SAT") //Every Sat at 14:00
    void requestPublishWeeklyIods() {
        logger.info("Request publish weekly Iod")
        requestService.createRequest(Constants.PUBLISH_WEEKLY_IOD, scheduleCreator)
    }*/

}
