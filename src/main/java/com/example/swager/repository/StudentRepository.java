package com.example.swager.repository;

import com.example.swager.models.Students;
import com.example.swager.models.XepLoai;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Students,Long> {
    Page<Students> findAll(Pageable pageable);
    List<Students> findByNameContainingIgnoreCase(String name);
    @Query("SELECT s FROM Students s WHERE LOWER(s.thanhPho) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Students> findByThanhPho(String name);

    @Query("SELECT s FROM Students s WHERE LOWER(s.thanhPho) LIKE LOWER(CONCAT('%', :name, '%')) OR LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Students> findByThanhPhoAndName(String name);

    @Query("SELECT s FROM Students s WHERE year(s.ngaySinh) BETWEEN :startYear AND :endYear ")
    List<Students> findByNgaySinhBetweeb(int startYear,int endYear);

    List<Students> findByXepLoai(XepLoai xepLoai);

    @Query("SELECT s FROM Students s WHERE"+
    "(:xepLoai IS NULL OR s.xepLoai = :xepLoai) AND"+
     "(:name IS NULL OR s.name LIKE %:name%) AND"+
      "(:startYear IS NULL OR year(s.ngaySinh)>=:startYear) AND"+
      "(:endYear IS NULL OR year(s.ngaySinh)<= :endYear )")
    List<Students> searchAll(@Param("xepLoai")XepLoai xepLoai,
                             @Param("name") String name,
                             @Param("startYear") int startYear,
                             @Param("endYear") int endYear
                             );

}
