package com.alta.miniprojectcheckpoint.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "departement")
public class Departement {
    @Id
    @GeneratedValue(strategy =GenerationType.SEQUENCE, generator = "departement_generator")
    @SequenceGenerator(name="departement_generator", sequenceName = "departement_seq", allocationSize = 1)
    @Column(name = "id_departement")
    private Integer id_departement;

    @Column(name = "name_departement")
    private String name_departement;

    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;

//    @JsonIgnore
//    @OneToMany(mappedBy = "departement")
//    private List<User> user;
}
