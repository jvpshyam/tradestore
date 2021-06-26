package com.db.tradestore.dao;

import com.db.tradestore.entity.Trade;

import java.util.List;

public interface ITradesDAO {

    public Trade save(Trade trade);

    List<Trade> findAll();

    public List<Trade> findByTradeId(String tradeId);
}
