package com.example.swager.controllers;

import com.example.swager.dto.StudentDTO;
import com.example.swager.exceptions.ResourceNotFoundException;
import com.example.swager.models.Students;
import com.example.swager.responses.ApiResponse;
import com.example.swager.responses.StudentListRespone;
import com.example.swager.responses.StudentRespone;
import com.example.swager.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/student")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {
    private final StudentService studentService;

//    @GetMapping("")
//    public ResponseEntity<List<Students>> index(){
//        return ResponseEntity.ok().body(studentService.getAllStudents());
//    }
    @GetMapping("")
    public ResponseEntity<ApiResponse> index(){
        ApiResponse apiResponse=ApiResponse.builder()
                .data(studentService.getAllStudents())
                .status(HttpStatus.OK.value())
                .message("OK")
                .build();
        return ResponseEntity.ok().body(apiResponse);
    }

//    @PostMapping("")
//    public Students postStudent(@RequestBody StudentDTO studentDTO){
//        return studentService.saveStudent(studentDTO);
//    }

//       @PostMapping()
//       public  ResponseEntity<?> create(@Valid @RequestBody StudentDTO studentDTO, BindingResult result){
//        if(result.hasErrors()){
//            List<String> errors=result.getFieldErrors().stream()
//                    .map(FieldError::getDefaultMessage).toList();
//            return ResponseEntity.badRequest().body(errors);
//        }
//        Students student=studentService.saveStudent(studentDTO);
//        return ResponseEntity.ok("Insert successfully "+student.toString());
//       }
@PostMapping()
public  ResponseEntity<ApiResponse> create(@Valid @RequestBody StudentDTO studentDTO, BindingResult result){
    if(result.hasErrors()){
        List<String> errors=result.getFieldErrors().stream()
                .map(FieldError::getDefaultMessage).toList();
        ApiResponse apiResponse=ApiResponse.builder()
                .data(errors)
                .status(HttpStatus.BAD_REQUEST.value())
                .message("Validation failed")
                .build();

        return ResponseEntity.badRequest().body(apiResponse);
    }
    Students student=studentService.saveStudent(studentDTO);
    ApiResponse apiResponse=ApiResponse.builder()
            .data(student)
            .status(HttpStatus.OK.value())
            .message("insert thanh cong")
            .build();
    return ResponseEntity.ok(apiResponse);
}
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> delete(@PathVariable Long id){
//        studentService.deleteStudent(id);
//        return ResponseEntity.ok("ok");
//    }
//    @PutMapping("/{id}")
//    public  ResponseEntity<?> create(@PathVariable Long id,@Valid @RequestBody StudentDTO studentDTO, BindingResult result){
//        if(result.hasErrors()){
//            List<String> errors=result.getFieldErrors().stream()
//                    .map(FieldError::getDefaultMessage).toList();
//            return ResponseEntity.badRequest().body(errors);
//        }
//        Students student=studentService.updateStudent(id,studentDTO);
//        return ResponseEntity.ok("update successfully "+student.toString());
//    }
@PutMapping("/{id}")
public  ResponseEntity<?> create(@PathVariable Long id,@Valid @RequestBody StudentDTO studentDTO, BindingResult result){
    if(result.hasErrors()){
        List<String> errors=result.getFieldErrors().stream()
                .map(FieldError::getDefaultMessage).toList();
        ApiResponse apiResponse=ApiResponse.builder()
                .data(errors)
                .status(HttpStatus.BAD_REQUEST.value())
                .message("Validation failed")
                .build();
        return ResponseEntity.badRequest().body(apiResponse);
    }

    Students student=studentService.updateStudent(id,studentDTO);
    if(student==null){
        throw new ResourceNotFoundException("student khong tim thay voi id:"+id);
    }
    ApiResponse apiResponse=ApiResponse.builder()
            .data(student)
            .status(HttpStatus.OK.value())
            .message("Update successfully")
            .build();

    return ResponseEntity.ok(apiResponse);
}

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
     Students student= studentService.getStudentById(id);
     if(student==null)
         throw new ResourceNotFoundException("student khong tim thay voi id "+id);

     studentService.deleteStudent(id);
        ApiResponse apiResponse=ApiResponse.builder()
                .data(null)
                .status(HttpStatus.OK.value())
                .message("delete successfully")
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/search1")
    public ResponseEntity<ApiResponse> search1(@RequestParam String name){
        ApiResponse apiResponse=ApiResponse.builder()
                .data(studentService.findByName(name))
                .status(HttpStatus.OK.value())
                .message("search successfully")
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/search2")
    public  ResponseEntity<ApiResponse> search2(@RequestParam String name){
        ApiResponse apiResponse=ApiResponse.builder()
                .data(studentService.findByThanhPho(name))
                .status(HttpStatus.OK.value())
                .message("search successfully")
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/search3")
    public  ResponseEntity<ApiResponse> search3(@RequestParam String name){
        ApiResponse apiResponse=ApiResponse.builder()
                .data(studentService.findByThanhPhoAndTen(name))
                .status(HttpStatus.OK.value())
                .message("search successfully")
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResponse> getStudents1(
            @RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "5") int size)
    {
        Pageable pageable= PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<StudentRespone> studentRespones=studentService.getStudents(pageable);
        int totalPages=studentRespones.getTotalPages();
        List<StudentRespone> responeList=studentRespones.getContent();
        StudentListRespone studentListRespone=StudentListRespone.builder()
                .studentResponeList(responeList)
                .totalPages(totalPages)
                .build();
        ApiResponse apiResponse=ApiResponse.builder()
                .data(studentListRespone)
                .status(HttpStatus.OK.value())
                .message("show students successfully")
                .build();
        return ResponseEntity.ok(apiResponse);
    }

}
