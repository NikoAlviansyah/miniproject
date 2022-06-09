package com.miniproject.miniproject.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "[Transfer]")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Transfer {

    @Id
    @Column(name = "TransferID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transferId;

    @Column(name = "Fund")
    private Long fund;

    @Column(name = "Note")
    private String note;

    @Column(name = "Date")
    private LocalDate date;

    @Column(name = "Status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "SenderUserID")
    private User senderUser;

    @ManyToOne
    @JoinColumn(name = "RecipientUserID")
    private User recipientUser;

    public Transfer(Long fund, String note, LocalDate date, String status, User senderUser, User recipientUser) {
        this.fund = fund;
        this.note = note;
        this.date = date;
        this.status = status;
        this.senderUser = senderUser;
        this.recipientUser = recipientUser;
    }
}
