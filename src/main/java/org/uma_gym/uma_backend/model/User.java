package org.uma_gym.uma_backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.uma_gym.uma_backend.model.enums.Role;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private UUID id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    private String phoneNumber;
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private boolean passwordChangeRequired = true; //default TRUE za nove korisnike

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Membership> memberships;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<StaffAssignment> staffAssignments;

    @OneToMany(mappedBy = "trainer")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<ClassSchedule> ledClasses;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Booking> bookings;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<CheckIn> checkIns;
}