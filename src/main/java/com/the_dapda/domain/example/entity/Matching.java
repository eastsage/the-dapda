package com.the_dapda.domain.example.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "matching", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"sender_member_id", "sender_active", "receiver_member_id", "receiver_active"})
})
public class Matching {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "matching_id", nullable = false, unique = true)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "sender_member_id", nullable = false)
    private Long senderMemberId;

    @Column(name = "sender_active", nullable = false)
    private Boolean senderActive;

    @Column(name = "receiver_member_id", nullable = false)
    private Long receiverMemberId;

    @Column(name = "receiver_active", nullable = false)
    private Boolean receiverActive;
}