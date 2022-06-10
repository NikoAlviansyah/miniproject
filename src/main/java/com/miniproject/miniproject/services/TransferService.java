package com.miniproject.miniproject.services;

import com.miniproject.miniproject.dtos.transfer.CreateTransferDto;
import com.miniproject.miniproject.dtos.transfer.TransferDto;
import com.miniproject.miniproject.exceptions.EntityNotFoundException;
import com.miniproject.miniproject.models.Transfer;
import com.miniproject.miniproject.models.User;
import com.miniproject.miniproject.repositories.TransferRepository;
import com.miniproject.miniproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class TransferService {

    private TransferRepository transferRepository;
    private UserRepository userRepository;

    @Autowired
    public TransferService(TransferRepository transferRepository, UserRepository userRepository) {
        this.transferRepository = transferRepository;
        this.userRepository = userRepository;
    }

    public List<TransferDto> getAllTransferHistory() {
        List<TransferDto> transferDtos = new ArrayList<>();

        Stream<Transfer> transfers = transferRepository.findAll().stream();

        transfers.forEach(
                (transfer) -> {
                    transferDtos.add(
                            new TransferDto(
                                    transfer.getTransferId(),
                                    transfer.getFund(),
                                    transfer.getNote() == null ? "-" : transfer.getNote(),
                                    transfer.getDate(),
                                    transfer.getStatus() ? "Successfully" : "Failed",
                                    transfer.getSenderUser().getUserId(),
                                    transfer.getRecipientUser().getUserId()
                            )
                    );
                }
        );

        return transferDtos;
    }

    public List<TransferDto> getTransferHistoryBySenderUserId(String userId) {
        List<TransferDto> transferDtos = new ArrayList<>();

        Stream<Transfer> transfers = transferRepository.getTransferHistoryBySenderUserId(userId).stream();

        transfers.forEach(
                (transfer) -> {
                    transferDtos.add(
                            new TransferDto(
                                    transfer.getTransferId(),
                                    transfer.getFund(),
                                    transfer.getNote() == null ? "-" : transfer.getNote(),
                                    transfer.getDate(),
                                    transfer.getStatus() ? "Successfully" : "Failed",
                                    transfer.getSenderUser().getUserId(),
                                    transfer.getRecipientUser().getUserId()
                            )
                    );
                }
        );

        return transferDtos;
    }

    public List<TransferDto> getTransferHistoryByRecipientUserId(String userId) {
        List<TransferDto> transferDtos = new ArrayList<>();

        Stream<Transfer> transfers = transferRepository.getTransferHistoryByRecipientUserId(userId).stream();

        transfers.forEach(
                (transfer) -> {
                    transferDtos.add(
                            new TransferDto(
                                    transfer.getTransferId(),
                                    transfer.getFund(),
                                    transfer.getNote() == null ? "-" : transfer.getNote(),
                                    transfer.getDate(),
                                    transfer.getStatus() ? "Successfully" : "Failed",
                                    transfer.getSenderUser().getUserId(),
                                    transfer.getRecipientUser().getUserId()
                            )
                    );
                }
        );

        return transferDtos;
    }

    public TransferDto createTransfer(CreateTransferDto newTransfer) {
        User senderCustomer = userRepository.findCustomerByAccountNumber(newTransfer.getSenderAccountNumber(),
                        true)
                .orElseThrow(() -> new EntityNotFoundException("Sender customer not found."));

        User recipientCustomer = userRepository.findCustomerRecipientByAccountNumber(
                        newTransfer.getRecipientAccountNumber(),
                        senderCustomer.getAccountNumber(), true)
                .orElseThrow(() -> new EntityNotFoundException("Recipient customer not found."));

        senderCustomer.setFund(senderCustomer.getFund() - newTransfer.getFund());
        recipientCustomer.setFund(recipientCustomer.getFund() + newTransfer.getFund());

        if (newTransfer.getFund() > 0 &&
                senderCustomer.getFund() >= 0) {
            Transfer transfer = new Transfer(
                    newTransfer.getFund(),
                    newTransfer.getNote(),
                    LocalDate.now(),
                    true,
                    senderCustomer,
                    recipientCustomer
            );

            transferRepository.save(transfer);

            return new TransferDto(transfer.getTransferId(),
                    transfer.getFund(),
                    transfer.getNote() == null ? "" : transfer.getNote(),
                    transfer.getDate(),
                    transfer.getStatus() ? "Successfully" : "Failed",
                    transfer.getSenderUser().getAccountNumber(),
                    transfer.getRecipientUser().getAccountNumber());
        } else {
            senderCustomer.setFund(senderCustomer.getFund() + newTransfer.getFund());
            recipientCustomer.setFund(recipientCustomer.getFund() - newTransfer.getFund());

            Transfer transfer = new Transfer(
                    newTransfer.getFund(),
                    newTransfer.getNote(),
                    LocalDate.now(),
                    false,
                    senderCustomer,
                    recipientCustomer
            );

            transferRepository.save(transfer);

            return new TransferDto(transfer.getTransferId(),
                    transfer.getFund(),
                    transfer.getNote() == null ? "" : transfer.getNote(),
                    transfer.getDate(),
                    transfer.getStatus() ? "Successfully" : "Failed",
                    transfer.getSenderUser().getAccountNumber(),
                    transfer.getRecipientUser().getAccountNumber());
        }
    }
}
