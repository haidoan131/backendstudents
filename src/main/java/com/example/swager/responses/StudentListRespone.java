package com.example.swager.responses;

import lombok.*;

import java.util.List;

@Builder
@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentListRespone {
    private List<StudentRespone> studentResponeList;
    private int totalPages;

}
