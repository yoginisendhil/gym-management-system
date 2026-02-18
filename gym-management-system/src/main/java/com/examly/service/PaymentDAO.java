package com.examly.service;

import com.examly.entity.Payment;
import java.util.List;

public interface PaymentDAO {
    boolean addPayment(Payment p);
    boolean addpayment(Payment p);
    List<Payment> getPaymentsByMembership(int membershipId);
}