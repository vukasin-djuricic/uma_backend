package org.uma_gym.uma_backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.uma_gym.uma_backend.model.enums.MembershipStatus;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "memberships")
@Data
public class Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "membership_id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id", nullable = false)
    private MembershipPlan plan;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MembershipStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_location_id")
    private Location homeLocation;

    // Relationships
    @OneToMany(mappedBy = "membership", cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Payment> payments;
}