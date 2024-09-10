package com.example.swager.service;

import com.example.swager.dto.StudentDTO;
import com.example.swager.models.Students;
import com.example.swager.models.XepLoai;
import com.example.swager.repository.StudentRepository;
import com.example.swager.responses.StudentRespone;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService {
    private final StudentRepository studentRepository;
 //   private final StudentRepo2 studentRepository2;
    @Override
    public Students getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(()->new RuntimeException("student không tìm thấy"));
    }

    @Override
    public Page<StudentRespone> getStudents(Pageable pageable) {
        return studentRepository.findAll(pageable).map(students -> {
            return StudentRespone.fromStudent(students);
        });
    }

    @Override
    public List<Students> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Students saveStudent(StudentDTO student) {
        Students student1=Students.builder()
                .name(student.getName())
                .thanhPho(student.getThanhPho())
                .ngaySinh(student.getNgaySinh())
                .xepLoai(XepLoai.fromTen( student.getXepLoai()))
                .build();

        return studentRepository.save(student1);
    }

    @Override
    public Students updateStudent(Long id, StudentDTO student) {
        Students student1=getStudentById(id);
        student1.setName(student.getName());
        student1.setThanhPho(student.getThanhPho());
        student1.setNgaySinh(student.getNgaySinh());
        student1.setXepLoai(XepLoai.fromTen( student.getXepLoai()));


        return studentRepository.save(student1);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Students> findByName(String name) {
        return studentRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Students> findByThanhPho(String name) {
        return studentRepository.findByThanhPho(name);
    }

    @Override
    public List<Students> findByThanhPhoAndTen(String name) {
        return studentRepository.findByThanhPhoAndName(name);
    }
}
