package com.db.tradestore.service;

import com.db.tradestore.entity.Trade;
import com.db.tradestore.exception.BadRequestException;

import java.util.List;

public interface ITradesService {

    public List<Trade> getAllTrades();

    public Trade saveTrade(Trade trade) throws BadRequestException;

    public void updateTradeExpiry();

    //public boolean isTradeValid(Trade trade);

}
