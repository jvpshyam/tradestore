package com.db.tradestore.service;

import com.db.tradestore.dao.ITradesDAO;
import com.db.tradestore.entity.Trade;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TradesServiceImplTest {

    @Mock
    private ITradesDAO tradesDAO;

    @InjectMocks
    private TradesServiceImpl tradesService;

    private Trade getTrade() {
        Trade trade = new Trade(1,"T1" , 1 , "B1" , "CP-1" , LocalDate.of(2021 , 8 , 01) , LocalDate.now(), "N");
        return trade;
    }

    @Test
    public void testSaveTrade() throws Exception {
        when(tradesDAO.save(any(Trade.class))).thenReturn(getTrade());
        Trade trade = tradesService.saveTrade(getTrade());
        assertNotNull(trade.getBookId());
    }

    @Test
    public void testGetAllTrades() throws Exception {
        when(tradesDAO.findAll()).thenReturn(getTrades());
        List<Trade> trades = tradesService.getAllTrades();
        assertNotNull(trades.get(0).getBookId());
    }

   @Test
    public void testUpdateTradeExpiry() throws Exception {
        when(tradesDAO.findAll()).thenReturn(getTrades());
        tradesService.updateTradeExpiry();
        verify(tradesDAO ,atLeastOnce() ).findAll();
    }

    private List<Trade> getTrades(){
        List<Trade> trades = new ArrayList<Trade>();
        trades.add(getTrade());
        return trades;
    }
}
