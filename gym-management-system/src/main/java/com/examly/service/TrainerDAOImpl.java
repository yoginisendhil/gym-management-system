package com.examly.service;

import com.examly.entity.Trainer;
import com.examly.util.DbConnectionUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrainerDAOImpl implements TrainerDAO {
    
    private boolean addTrainerInternal(Trainer trainer) {
        String sql = "INSERT INTO trainers (name, specialization, contact_number, email) VALUES (?, ?, ?, ?)";
        
        try (Connection connection = DbConnectionUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            
            pstmt.setString(1, trainer.getName());
            pstmt.setString(2, trainer.getSpecialization());
            pstmt.setString(3, trainer.getContactNumber());
            pstmt.setString(4, trainer.getEmail());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean addTrainer(Trainer trainer) {
        return addTrainerInternal(trainer);
    }
    
    @Override
    public boolean addtrainer(Trainer trainer) {
        return addTrainerInternal(trainer);
    }
    
    public boolean addtrainer(com.examly.entity.Trainer trainer) {
        return addTrainerInternal(trainer);
    }
    
    private List<Trainer> getAllTrainersInternal() {
        String sql = "SELECT * FROM trainers";
        List<Trainer> trainers = new ArrayList<>();
        
        try (Connection connection = DbConnectionUtil.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Trainer trainer = new Trainer();
                trainer.setTrainerId(rs.getInt("trainer_id"));
                trainer.setName(rs.getString("name"));
                trainer.setSpecialization(rs.getString("specialization"));
                trainer.setContactNumber(rs.getString("contact_number"));
                trainer.setEmail(rs.getString("email"));
                
                trainers.add(trainer);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return trainers;
    }
    
    @Override
    public List<Trainer> getAllTrainers() {
        return getAllTrainersInternal();
    }
    
    @Override
    public List<Trainer> getAlltrainers() {
        return getAllTrainersInternal();
    }
    
    public List<Trainer> getAlltrainers(Object... args) {
        return getAllTrainersInternal();
    }
}