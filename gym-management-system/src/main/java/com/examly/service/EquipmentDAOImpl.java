package com.examly.service;

import com.examly.entity.Equipment;
import com.examly.util.DbConnectionUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipmentDAOImpl implements EquipmentDAO {
    
    @Override
    public boolean addEquipment(Equipment eq) {
        String sql = "INSERT INTO equipment (name, type, quantity, maintenance_status) VALUES (?, ?, ?, ?)";
        
        try (Connection connection = DbConnectionUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            
            pstmt.setString(1, eq.getName());
            pstmt.setString(2, eq.getType());
            pstmt.setInt(3, eq.getQuantity());
            pstmt.setString(4, eq.getMaintenanceStatus());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public List<Equipment> getAllEquipment() {
        String sql = "SELECT * FROM equipment";
        List<Equipment> equipmentList = new ArrayList<>();
        
        try (Connection connection = DbConnectionUtil.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Equipment eq = new Equipment();
                eq.setEquipmentId(rs.getInt("equipment_id"));
                eq.setName(rs.getString("name"));
                eq.setType(rs.getString("type"));
                eq.setQuantity(rs.getInt("quantity"));
                eq.setMaintenanceStatus(rs.getString("maintenance_status"));
                
                equipmentList.add(eq);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return equipmentList;
    }
}