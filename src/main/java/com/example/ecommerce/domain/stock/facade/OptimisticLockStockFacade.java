package com.example.ecommerce.domain.stock.facade;

import com.example.ecommerce.domain.stock.service.OptimisticLockStockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class OptimisticLockStockFacade {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final OptimisticLockStockService optimisticLockStockService;

    public OptimisticLockStockFacade(OptimisticLockStockService optimisticLockStockService) {
        this.optimisticLockStockService = optimisticLockStockService;
    }

    public void decrease(Long productId, Long quantity) throws InterruptedException {
        while(true) {
            try {
                optimisticLockStockService.decrease(productId, quantity);
                break;
            } catch (Exception e) {
                log.info("########## update exception");
                Thread.sleep(50); // 업데이트 실패 시 50ms 후 재시도
            }
        }
    }
}
