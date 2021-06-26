package com.db.tradestore.repository;

import com.db.tradestore.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradesRepository extends JpaRepository<Trade,String> {

    List<Trade> findByTradeId(String tradeId);
}