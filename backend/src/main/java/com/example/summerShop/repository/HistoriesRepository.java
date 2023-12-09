package com.example.summerShop.repository;

import com.example.summerShop.model.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoriesRepository extends JpaRepository<History, Long>, CustomHistoriesRepository {
}
