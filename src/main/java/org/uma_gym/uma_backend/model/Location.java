package org.uma_gym.uma_backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "locations")
@Data
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    private String address;
    private String city;
    private String phoneNumber;

    @Column(columnDefinition = "TEXT")
    private String openingHours;

    @CreationTimestamp
    private LocalDateTime createdAt;

    // Relationships
    @OneToMany(mappedBy = "homeLocation")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Membership> homeMemberships;

    @OneToMany(mappedBy = "location")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<StaffAssignment> staffAssignments;

    @OneToMany(mappedBy = "location")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<ClassSchedule> classSchedules;

    @OneToMany(mappedBy = "location")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<CheckIn> checkIns;
}