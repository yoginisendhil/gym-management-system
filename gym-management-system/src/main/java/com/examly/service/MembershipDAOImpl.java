package com.examly.service;

import com.examly.entity.Membership;
import com.examly.util.DbConnectionUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MembershipDAOImpl implements MembershipDAO {
    
    @Override
    public boolean addMembership(Membership m) {
        String sql = "INSERT INTO memberships (member_id, plan_type, start_date, end_date, total_fee) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection connection = DbConnectionUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            
            pstmt.setInt(1, m.getMemberId());
            pstmt.setString(2, m.getPlanType());
            pstmt.setDate(3, new java.sql.Date(m.getStartDate().getTime()));
            pstmt.setDate(4, new java.sql.Date(m.getEndDate().getTime()));
            pstmt.setDouble(5, m.getTotalFee());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public List<Membership> getMembershipsByMember(int memberId) {
        String sql = "SELECT * FROM memberships WHERE member_id = ?";
        List<Membership> memberships = new ArrayList<>();
        
        try (Connection connection = DbConnectionUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            
            pstmt.setInt(1, memberId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Membership membership = new Membership();
                membership.setMembershipId(rs.getInt("membership_id"));
                membership.setMemberId(rs.getInt("member_id"));
                membership.setPlanType(rs.getString("plan_type"));
                membership.setStartDate(rs.getDate("start_date"));
                membership.setEndDate(rs.getDate("end_date"));
                membership.setTotalFee(rs.getDouble("total_fee"));
                
                memberships.add(membership);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return memberships;
    }
    
    public List<Membership> getMembershipsByMember(int memberId, Object... args) {
        return getMembershipsByMember(memberId);
    }
}