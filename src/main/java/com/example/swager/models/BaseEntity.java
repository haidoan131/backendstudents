package com.example.swager.models;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity {
    @CreationTimestamp
    @Column(name="created_at",updatable = false)
    private LocalDateTime createdAt;
    @Column(name="updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

//    @PrePersist
//    protected void onCreate(){
//        this.createAt=LocalDateTime.now();
//        this.updatedAt= LocalDateTime.now();
//    }

}
