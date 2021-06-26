package com.db.tradestore.dao;

import com.db.tradestore.entity.Trade;
import com.db.tradestore.repository.TradesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TradesDAOImpl implements ITradesDAO {

    private static final Logger logger = LoggerFactory.getLogger(TradesDAOImpl.class);

    @Autowired
    TradesRepository tradesRepository;

    @Override
    public Trade save(Trade trade) {
        logger.info("Inside save method TradesDAOImpl");
         return tradesRepository.save(trade);
    }

    @Override
    public List<Trade> findAll() {
        logger.info("Inside findAll method TradesDAOImpl");
        return tradesRepository.findAll();
    }

    @Override
    public List<Trade> findByTradeId(String tradeId) {
        logger.info("Inside findByTradeId method TradesDAOImpl");
        return tradesRepository.findByTradeId(tradeId);
    }

}
