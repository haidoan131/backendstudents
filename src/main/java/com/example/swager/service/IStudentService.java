package com.example.swager.service;

import com.example.swager.dto.StudentDTO;
import com.example.swager.models.Students;
import com.example.swager.responses.StudentRespone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IStudentService {
    Students getStudentById(Long id);
   Page<StudentRespone> getStudents(Pageable pageable);
    List<Students> getAllStudents();
    Students saveStudent(StudentDTO student);
    Students updateStudent(Long id, StudentDTO student);
    void deleteStudent(Long id);
    List<Students> findByName(String name);
    List<Students> findByThanhPho(String name);
    List<Students> findByThanhPhoAndTen(String name);

}
