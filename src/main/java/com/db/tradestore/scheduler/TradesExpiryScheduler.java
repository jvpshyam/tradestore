package com.db.tradestore.scheduler;

import com.db.tradestore.service.ITradesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class TradesExpiryScheduler {

    private static final Logger logger = LoggerFactory.getLogger(TradesExpiryScheduler.class);

    @Autowired
    private ITradesService tradeService;

    // Scheduler Runs at 12 Midnight cron = 0 0 0 * * ?
    @Scheduled(cron = "${cron.expression}")
    public void updateTradeExpiry(){
        logger.info("In updateTradeExpiry method TradesExpiryScheduler");
        logger.info("Scheduler start time : " + LocalTime.now());
        try{
            tradeService.updateTradeExpiry();
        }catch (Exception e){
            logger.error( " Exception occurred while scheduler run " + e);
        }
        logger.info("Scheduler end time : " + LocalTime.now());

    }
}
