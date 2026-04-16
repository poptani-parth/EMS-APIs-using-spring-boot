package com.EMS.Practice_Project.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "advancePayments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdvancePayment {

    @Id
    private String id;

    private String advanceId;

    private String employeeId;

    private Double amount;

    private LocalDate requestedDate;

    private LocalDate approvedDate;

    private AdvanceStatus status;

    private String remarks;

    public enum AdvanceStatus {
        PENDING,
        APPROVED,
        REJECTED,
        PAID
    }
}
