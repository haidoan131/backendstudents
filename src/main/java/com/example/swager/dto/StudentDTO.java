package com.example.swager.dto;

import com.example.swager.models.XepLoai;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StudentDTO {
    @NotBlank(message = "tên không được trống")
    private String name;
    @NotBlank(message = "thành phho không được trống")
    private String thanhPho;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Past(message = "phải là 1 ngày trong quá khứ")
    private LocalDate ngaySinh;

    @NotNull(message = "không dc trống")
    @Enumerated(EnumType.STRING)
    private String xepLoai;
}
