package com.alta.miniprojectcheckpoint.model;

import com.alta.miniprojectcheckpoint.domain.common.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.http.HttpStatus;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "fitnote")
public class Fitnote {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fitnote_generator")
    @SequenceGenerator(name = "fitnote_generator", sequenceName = "fitnote_seq", allocationSize = 1)
    @Column(name = "id_fitnote")
    private Long id_fitnote;

    @Column(name = "temperature")
    private Double temperature;

    @Column(name = "sleep_hour")
    private Integer sleep_hour;

    @Column(name = "note")
    private String note;

    @Column(name = "status")
    private String status;

    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @ManyToOne
    @JoinColumn(name = "id_employee")
    private Employee employee;

    public <T> Fitnote(ApiResponse<T> build, HttpStatus httpStatus) {
    }
}
