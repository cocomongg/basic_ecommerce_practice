package com.example.ecommerce.domain.stock.service;

import com.example.ecommerce.domain.stock.model.Stock;
import com.example.ecommerce.domain.stock.repository.StockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StockService {

//    private final Logger log = LoggerFactory.getLogger(getClass());

    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Transactional
    public void decrease(Long id, Long quantity) {
        // id에 해당하는 Stock조회
        // 재고를 감소
        // 갱신된 값으로 업데이트
        Stock stock = stockRepository.findById(id).orElseThrow();
        stock.decrease(quantity);

        stockRepository.saveAndFlush(stock);
    }
}
