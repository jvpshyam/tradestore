package com.db.tradestore.dao;

import com.db.tradestore.entity.Trade;
import com.db.tradestore.repository.TradesRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TradesDAOImpTest {

    @InjectMocks
    private TradesDAOImpl tradesDAOImpl;

    @Mock
    TradesRepository tradesRepository;

    @Test
    public void testSave() {
        Trade tradeMock = getTrade();
        when(tradesRepository.save(any(Trade.class))).thenReturn(getTrade());
        Trade trade = tradesDAOImpl.save(tradeMock);
        assertNotNull(trade);
        assertThat(trade.getBookId(), is(tradeMock.getBookId()));
        assertThat(trade.getTradeId(), is(tradeMock.getTradeId()));
    }

    @Test
    public void testFindAll() {
        Trade tradeMock = getTrade();
        when(tradesRepository.findAll()).thenReturn(getTrades());
        List<Trade> trades = tradesDAOImpl.findAll();
        assertNotNull(trades);
        assertThat(trades.get(0).getBookId(), is(tradeMock.getBookId()));
        assertThat(trades.get(0).getTradeId(), is(tradeMock.getTradeId()));
    }

    @Test
    public void testFindByTradeId() {
        Trade tradeMock = getTrade();
        when(tradesRepository.findByTradeId(any(String.class))).thenReturn(getTrades());
        List<Trade> trades = tradesDAOImpl.findByTradeId("1");
        assertNotNull(trades);
        assertThat(trades.get(0).getBookId(), is(tradeMock.getBookId()));
        assertThat(trades.get(0).getTradeId(), is(tradeMock.getTradeId()));
    }

    private Trade getTrade() {
        Trade trade = new Trade(1,"T1" , 1 , "B1" , "CP-1" , LocalDate.of(2021 , 8 , 01) , LocalDate.now(), "N");
        return trade;
    }

    private List<Trade> getTrades(){
        List<Trade> trades = new ArrayList<Trade>();
        trades.add(getTrade());
        return trades;
    }
}
