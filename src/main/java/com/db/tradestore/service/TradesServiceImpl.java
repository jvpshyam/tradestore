package com.db.tradestore.service;


import com.db.tradestore.dao.ITradesDAO;
import com.db.tradestore.entity.Trade;
import com.db.tradestore.exception.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TradesServiceImpl implements ITradesService {

    private static final Logger logger = LoggerFactory.getLogger(TradesServiceImpl.class);

    @Autowired
    ITradesDAO tradesDAO;

    /**
     * Retrieves all the trades from the Database
     * @return List of trades
     */
    public List<Trade> getAllTrades() {
        logger.info("In getAllTrades of TradesServiceImpl");
        return tradesDAO.findAll();
    }

    /**
     * Automatically update the Expiry flag if trade crosses Maturity date
     */
    public void updateTradeExpiry() {
        logger.info("In updateTradeExpiry of TradesServiceImpl");
        List<Trade> trades = tradesDAO.findAll();
        if(trades!=null && !trades.isEmpty()) {
            trades.stream().forEach(trade -> {
                if (!compareTradeMaturityDate(trade)) {
                    trade.setExpired("Y");
                    logger.info("Updating trade expiry flag and saving" + trade.getTradeId());
                    tradesDAO.save(trade);
                }
            });
        }
    }

    /**
     * Trades which has maturity date less than today's date should not be stored.
     *
     */
    public boolean compareTradeMaturityDate(Trade trade) {
         if(trade.getMaturityDate().isBefore(LocalDate.now())){
             return false;
         }
         return true;
    }


    /**
     * Saves Trade came in the request after validation
     * @param trade
     * @return trade
     */
    public Trade saveTrade(Trade trade) throws BadRequestException {
        logger.info("In saveTrade of TradesServiceImpl");
        trade.setCreatedDate(LocalDate.now());
        if (compareTradeMaturityDate(trade)) {
            List<Trade> trades = tradesDAO.findByTradeId(trade.getTradeId());
            int version = trade.getVersion();
            if (trades != null && !trades.isEmpty()) {
                Optional<Trade> tradeWithSameVersion = trades.stream().filter(t -> version < t.getVersion()).findAny();
                if (tradeWithSameVersion.isPresent()) {
                    throw new BadRequestException("Not a valid Trade , Less version no. in request");
                }
                Optional<Trade> tradeFromDB = trades.stream().filter(t -> version == t.getVersion()).findFirst();
                if (tradeFromDB.isPresent()) {
                    tradeFromDB.get().setBookId(trade.getBookId());
                    tradeFromDB.get().setCreatedDate(trade.getCreatedDate());
                    tradeFromDB.get().setExpired(trade.getExpired());
                    tradeFromDB.get().setCounterPartyId(trade.getCounterPartyId());
                    tradeFromDB.get().setMaturityDate(trade.getMaturityDate());
                    // Updating same version Trade
                    trade = tradesDAO.save(tradeFromDB.get());
                }else{
                    // Inserting new Trade record having same trade id
                    trade = tradesDAO.save(trade);
                }
            } else {
                // Inserting new trade
                trade = tradesDAO.save(trade);
            }
        }else{
            logger.error("Invalid Trade request with maturity date");
            throw new BadRequestException("Not a valid Trade , maturity date is less than today's date");
        }
        return trade;
    }

}