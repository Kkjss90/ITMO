package com.example.DB;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "result_4")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="X")
    private BigDecimal x;
    @Column(name="Y")
    private BigDecimal y;
    @Column(name="R")
    private BigDecimal r;
    @Column(name="Check")
    boolean isInArea;
    @Column(name="Time")
    LocalDateTime currentTime;
    @JoinColumn(name = "User_id")
    @ManyToOne
    private User user;
}
