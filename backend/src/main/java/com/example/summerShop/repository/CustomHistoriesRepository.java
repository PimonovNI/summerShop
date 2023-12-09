package com.example.summerShop.repository;

import com.example.summerShop.model.History;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomHistoriesRepository {
    List<History> findHistoryByUserId(Long id);
    List<History> findHistoryByUserIdFetchingProduct(Long id);
}
