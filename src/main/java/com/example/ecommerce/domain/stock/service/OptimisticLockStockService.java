package com.example.ecommerce.domain.stock.service;

import com.example.ecommerce.domain.stock.model.Stock;
import com.example.ecommerce.domain.stock.repository.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OptimisticLockStockService {

    private final StockRepository stockRepository;

    public OptimisticLockStockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Transactional
    public void decrease(Long productId, Long quantity) {
        Stock stock = stockRepository.findByproductIdWithOptimisticLock(productId);

        stock.decrease(quantity);

        stockRepository.save(stock);
    }
}
