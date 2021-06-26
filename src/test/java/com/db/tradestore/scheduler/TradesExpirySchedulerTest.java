package com.db.tradestore.scheduler;


import org.awaitility.Awaitility;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;
import org.awaitility.Duration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
// Override the scheduling rate to something really short:
@TestPropertySource(properties = "cron.expression = 0 * * * * ?")
public class TradesExpirySchedulerTest {

    @SpyBean
    private TradesExpiryScheduler tradesExpiryScheduler;

    @Test
    public void testUpdateTradeExpiry(){
        Awaitility.await().atMost(Duration.FIVE_MINUTES).untilAsserted(() ->
                verify(tradesExpiryScheduler, Mockito.atLeast(1)).updateTradeExpiry()
        );
    }

}
