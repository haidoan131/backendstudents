package com.example.swager.repository;

import com.example.swager.models.Students;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Students,Long> {
    Page<Students> findAll(Pageable pageable);
    List<Students> findByNameContainingIgnoreCase(String name);
    @Query("SELECT s FROM Students s WHERE LOWER(s.thanhPho) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Students> findByThanhPho(String name);

    @Query("SELECT s FROM Students s WHERE LOWER(s.thanhPho) LIKE LOWER(CONCAT('%', :name, '%')) OR LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Students> findByThanhPhoAndName(String name);

}
