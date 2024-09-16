package com.example.ecommerce.domain.stock.service;

import com.example.ecommerce.domain.stock.model.Stock;
import com.example.ecommerce.domain.stock.repository.StockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StockService {

//    private final Logger log = LoggerFactory.getLogger(getClass());

    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void decrease(Long productId, Long quantity) {
        Stock stock = stockRepository.findByProductId(productId);
        stock.decrease(quantity);

        stockRepository.saveAndFlush(stock);
    }

//    public synchronized void decrease(Long productId, Long quantity) {
//        Stock stock = stockRepository.findByProductId(productId);
//        stock.decrease(quantity);
//
//        stockRepository.saveAndFlush(stock);
//    }
}
