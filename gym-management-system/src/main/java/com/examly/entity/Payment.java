package com.examly.entity;

import java.util.Date;
import java.util.Objects;

public class Payment {
    private int paymentId;
    private int membershipId;
    private double amountPaid;
    private Date paymentDate;
    private String paymentStatus;

    // Default constructor
    public Payment() {}

    // Constructor for TEST: (int, int, java.sql.Date, String, double)
    public Payment(int paymentId, int membershipId, java.sql.Date paymentDate, String paymentStatus, double amountPaid) {
        this.paymentId = paymentId;
        this.membershipId = membershipId;
        this.paymentDate = new Date(paymentDate.getTime());
        this.paymentStatus = paymentStatus;
        this.amountPaid = amountPaid;
    }

    // Constructor for MainModule: (int, double, Date, String)
    public Payment(int membershipId, double amountPaid, Date paymentDate, String paymentStatus) {
        this.membershipId = membershipId;
        this.amountPaid = amountPaid;
        this.paymentDate = paymentDate;
        this.paymentStatus = paymentStatus;
    }

    // Getters and Setters
    public int getPaymentId() { return paymentId; }
    public void setPaymentId(int paymentId) { this.paymentId = paymentId; }
    public int getMembershipId() { return membershipId; }
    public void setMembershipId(int membershipId) { this.membershipId = membershipId; }
    public double getAmountPaid() { return amountPaid; }
    public void setAmountPaid(double amountPaid) { this.amountPaid = amountPaid; }
    public Date getPaymentDate() { return paymentDate; }
    public void setPaymentDate(Date paymentDate) { this.paymentDate = paymentDate; }
    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return paymentId == payment.paymentId;
    }

    @Override
    public int hashCode() { return Objects.hash(paymentId); }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", membershipId=" + membershipId +
                ", amountPaid=" + amountPaid +
                ", paymentDate=" + paymentDate +
                ", paymentStatus='" + paymentStatus + '\'' +
                '}';
    }
}