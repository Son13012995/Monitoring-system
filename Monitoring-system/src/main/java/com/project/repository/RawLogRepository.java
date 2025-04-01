package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.model.RawLog;

@Repository
public interface RawLogRepository extends JpaRepository<RawLog, Integer> {

    // Đếm số hàng
    @Query("SELECT COUNT(r) FROM RawLog r")
    long countAllRows();

    // Xóa tất cả
    @Modifying
    @Query("DELETE FROM RawLog r")
    void deleteAllRows();

    // Reset AUTO_INCREMENT (phải dùng Native Query)
    @Modifying
    @Query(value = "ALTER TABLE RawLog AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
}
