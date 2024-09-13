package com.example.swager.responses;

import com.example.swager.models.Students;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentRespone extends BaseRespone{
    private Long id;
    private String name;
    private String thanhPho;
    private LocalDate ngaySinh;
    private String xepLoai;
    public static StudentRespone fromStudent(Students student){
        StudentRespone studentRespone=StudentRespone.builder()
                .id(student.getId())
                .name(student.getName())
                .thanhPho(student.getThanhPho())
                .ngaySinh(student.getNgaySinh())
                .xepLoai(String.valueOf(student.getXepLoai()))
                .build();
        return studentRespone;
    }
}
