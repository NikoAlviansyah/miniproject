package com.miniproject.miniproject.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "[Deposit]")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Deposit {

    @Id
    @Column(name = "DepositID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer depositId;

    @Column(name = "Fund")
    private Long fund;

    @Column(name = "Date")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "UserID")
    private User user;

    public Deposit(Long fund, LocalDate date, User user) {
        this.fund = fund;
        this.date = date;
        this.user = user;
    }
}
