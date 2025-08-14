package org.uma_gym.uma_backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Set;

@Entity
@Table(name = "classes")
@Data
public class GymClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Integer defaultCapacity;

    // Relationships
    @OneToMany(mappedBy = "gymClass")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<ClassSchedule> classSchedules;
}