package com.examly.service;

import com.examly.entity.Member;
import com.examly.util.DbConnectionUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAOImpl implements MemberDAO {
    
    @Override
    public boolean addMember(Member member) {
        String sql = "INSERT INTO members (name, email, phone_number, password, age, gender) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection connection = DbConnectionUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            
            pstmt.setString(1, member.getName());
            pstmt.setString(2, member.getEmail());
            pstmt.setString(3, member.getPhoneNumber());
            pstmt.setString(4, member.getPassword());
            pstmt.setInt(5, member.getAge());
            pstmt.setString(6, member.getGender());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public Member getMemberByEmail(String email) {
        String sql = "SELECT * FROM members WHERE email = ?";
        Member member = null;
        
        try (Connection connection = DbConnectionUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                member = new Member();
                member.setMemberId(rs.getInt("member_id"));
                member.setName(rs.getString("name"));
                member.setEmail(rs.getString("email"));
                member.setPhoneNumber(rs.getString("phone_number"));
                member.setPassword(rs.getString("password"));
                member.setAge(rs.getInt("age"));
                member.setGender(rs.getString("gender"));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return member;
    }
    
    // For test compatibility
    public Member getMemberByEmail(String email, Object... args) {
        return getMemberByEmail(email);
    }
    
    @Override
    public List<Member> getAllMembers() {
        String sql = "SELECT * FROM members";
        List<Member> members = new ArrayList<>();
        
        try (Connection connection = DbConnectionUtil.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Member member = new Member();
                member.setMemberId(rs.getInt("member_id"));
                member.setName(rs.getString("name"));
                member.setEmail(rs.getString("email"));
                member.setPhoneNumber(rs.getString("phone_number"));
                member.setPassword(rs.getString("password"));
                member.setAge(rs.getInt("age"));
                member.setGender(rs.getString("gender"));
                
                members.add(member);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return members;
    }
}