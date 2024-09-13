package com.example.swager.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="student")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Students extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min=2 ,max=50,message = "tên phải có 2-50 ký tự")
    @NotBlank(message = "tên không được trống")
    private String name;
    @NotBlank(message = "thành phố không được trống")
    private String thanhPho;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Past(message = "phải là 1 ngày trong quá khứ")
    private LocalDate ngaySinh;

    @NotNull(message = "không dc trống")
    @Enumerated(EnumType.STRING)
    private XepLoai xepLoai;
}
