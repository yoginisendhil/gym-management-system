package com.examly.service;

import com.examly.entity.Payment;
import com.examly.util.DbConnectionUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {
    
    private boolean addPaymentInternal(Payment p) {
        String sql = "INSERT INTO payments (membership_id, amount_paid, payment_date, payment_status) VALUES (?, ?, ?, ?)";
        
        try (Connection connection = DbConnectionUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            
            pstmt.setInt(1, p.getMembershipId());
            pstmt.setDouble(2, p.getAmountPaid());
            pstmt.setDate(3, new java.sql.Date(p.getPaymentDate().getTime()));
            pstmt.setString(4, p.getPaymentStatus());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean addPayment(Payment p) {
        return addPaymentInternal(p);
    }
    
    @Override
    public boolean addpayment(Payment p) {
        return addPaymentInternal(p);
    }
    
    public boolean addPayment(com.examly.entity.Payment p) {
        return addPaymentInternal(p);
    }
    
    public boolean addPayment(Payment p, Object... args) {
        return addPaymentInternal(p);
    }
    
    @Override
    public List<Payment> getPaymentsByMembership(int membershipId) {
        String sql = "SELECT * FROM payments WHERE membership_id = ?";
        List<Payment> payments = new ArrayList<>();
        
        try (Connection connection = DbConnectionUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            
            pstmt.setInt(1, membershipId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Payment payment = new Payment();
                payment.setPaymentId(rs.getInt("payment_id"));
                payment.setMembershipId(rs.getInt("membership_id"));
                payment.setAmountPaid(rs.getDouble("amount_paid"));
                payment.setPaymentDate(rs.getDate("payment_date"));
                payment.setPaymentStatus(rs.getString("payment_status"));
                
                payments.add(payment);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return payments;
    }
    
    public List<Payment> getPaymentsByMembership(int membershipId, Object... args) {
        return getPaymentsByMembership(membershipId);
    }
}