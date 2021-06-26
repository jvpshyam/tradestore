package com.db.tradestore.controller;

import com.db.tradestore.entity.Trade;
import com.db.tradestore.exception.BadRequestException;
import com.db.tradestore.service.ITradesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TradesControllerTest {

    @Mock
    private ITradesService tradesService;

    @InjectMocks
    private TradesController tradesController;

    private Trade getTrade() {
        Trade trade = new Trade(1,"T1" , 1 , "B1" , "CP-1" , LocalDate.of(2021 , 8 , 01) , LocalDate.now(), "N");
        return trade;
    }

    @Test
    public void testSaveTrades() throws Exception {
        ResponseEntity<Trade> response  = null;
        when(tradesService.saveTrade(any(Trade.class))).thenReturn(getTrade());
        response = tradesController.saveTrade(getTrade());

        // To check if this method is called or not
        verify(tradesService ,atLeastOnce()).saveTrade(any(Trade.class));
        assertNotNull(response);
        assertTrue(response.getStatusCode().equals(HttpStatus.CREATED));
    }

    @Test
    public void testSaveTradesFailure() throws Exception {
        ResponseEntity<Trade> response  = null;
        when(tradesService.saveTrade(any(Trade.class))).thenThrow(BadRequestException.class);
        Exception exception = null;
        try {
            response = tradesController.saveTrade(getTrade());
        }catch(Exception e){
            exception = e;
        }
        assertNotNull(exception);
        // To check if this method is called or not
        verify(tradesService , times(1)).saveTrade(any(Trade.class));

    }

    @Test
    public void testGetAllTrades() throws Exception {
        ResponseEntity<List<Trade>> response  = null;
        when(tradesService.getAllTrades()).thenReturn(getTrades());
        response = tradesController.getAllTrades();

        // To check if this method is called or not
        verify(tradesService ,atLeastOnce()).getAllTrades();
        assertNotNull(response);
        assertTrue(response.getStatusCode().equals(HttpStatus.OK));
    }

    private List<Trade> getTrades(){
        List<Trade> trades = new ArrayList<Trade>();
        trades.add(getTrade());
        return trades;
    }



}
