package org.uma_gym.uma_backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.uma_gym.uma_backend.model.enums.AccessLevel;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "membership_plans")
@Data
public class MembershipPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "plan_id")
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    private Integer durationDays;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccessLevel accessLevel;

    // Relationships
    @OneToMany(mappedBy = "plan")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Membership> memberships;
}