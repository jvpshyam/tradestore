package com.db.tradestore.controller;

import com.db.tradestore.entity.Trade;
import com.db.tradestore.exception.BadRequestException;
import com.db.tradestore.service.ITradesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;

import java.util.List;


@RestController
@RequestMapping(value = "/v1/trades", produces = { "application/json" })
public class TradesController {

    private static final Logger logger = LoggerFactory.getLogger(TradesController.class);

    @Autowired
    private ITradesService tradeService;

    @PostMapping(value = "/save" , produces = "application/json" , consumes = "application/json")
    public ResponseEntity<Trade> saveTrade(@Valid @RequestBody Trade trade) throws BadRequestException {
        logger.info("In save method of TradesController");
        Trade tradeDB = null;
        try{
            tradeDB =  tradeService.saveTrade(trade);
        }catch (Exception e){
           logger.error("Exception thrown : BadRequestException:   " + e.getMessage());
           throw new BadRequestException("Not a valid Trade Request");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(tradeDB);
    }

    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<List<Trade>> getAllTrades(){
        logger.info("In getAllTrades method of TradesController");
        List<Trade> trades = tradeService.getAllTrades();
        return ResponseEntity.status(HttpStatus.OK).body(trades);
    }

    @ResponseStatus(value=HttpStatus.BAD_REQUEST,reason="This request is not a valid trade request")
    @ExceptionHandler(BadRequestException.class)
    public void exceptionHandler()
    {

    }
}