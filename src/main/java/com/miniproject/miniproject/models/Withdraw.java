package com.miniproject.miniproject.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "[Withdraw]")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Withdraw {

    @Id
    @Column(name = "WithdrawID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer withdrawId;

    @Column(name = "Fund")
    private Long fund;

    @Column(name = "Date")
    private LocalDate date;

    @Column(name = "Status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "UserID")
    private User user;

    public Withdraw(Long fund, LocalDate date, String status, User user) {
        this.fund = fund;
        this.date = date;
        this.status = status;
        this.user = user;
    }
}
