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
    @Column(name = "id")
    private Long id;
    @Column(name="X")
    private BigDecimal x;
    @Column(name="Y")
    private BigDecimal y;
    @Column(name="R")
    private BigDecimal r;
    @Column(name="success")
    private Boolean success;
    @Column(name="Time")
    private LocalDateTime timestamp;
    @JoinColumn(name = "User_id")
    @ManyToOne
    private User user;
}
