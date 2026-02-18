package com.examly.service;

import com.examly.entity.WorkoutPlan;
import com.examly.util.DbConnectionUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkoutPlanDAOImpl implements WorkoutPlanDAO {
    
    private boolean addWorkoutPlanInternal(WorkoutPlan plan) {
        String sql = "INSERT INTO workout_plans (trainer_id, plan_name, duration_weeks, description) VALUES (?, ?, ?, ?)";
        
        try (Connection connection = DbConnectionUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            
            pstmt.setInt(1, plan.getTrainerId());
            pstmt.setString(2, plan.getPlanName());
            pstmt.setInt(3, plan.getDurationWeeks());
            pstmt.setString(4, plan.getDescription());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean addWorkoutPlan(WorkoutPlan plan) {
        return addWorkoutPlanInternal(plan);
    }
    
    @Override
    public boolean addworkoutPlan(WorkoutPlan plan) {
        return addWorkoutPlanInternal(plan);
    }
    
    public boolean addworkoutPlan(com.examly.entity.WorkoutPlan plan) {
        return addWorkoutPlanInternal(plan);
    }
    
    @Override
    public List<WorkoutPlan> getPlansByTrainer(int trainerId) {
        String sql = "SELECT * FROM workout_plans WHERE trainer_id = ?";
        List<WorkoutPlan> plans = new ArrayList<>();
        
        try (Connection connection = DbConnectionUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            
            pstmt.setInt(1, trainerId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                WorkoutPlan plan = new WorkoutPlan();
                plan.setPlanId(rs.getInt("plan_id"));
                plan.setTrainerId(rs.getInt("trainer_id"));
                plan.setPlanName(rs.getString("plan_name"));
                plan.setDurationWeeks(rs.getInt("duration_weeks"));
                plan.setDescription(rs.getString("description"));
                
                plans.add(plan);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return plans;
    }
    
    private List<WorkoutPlan> getAllWorkoutPlansInternal() {
        String sql = "SELECT * FROM workout_plans";
        List<WorkoutPlan> plans = new ArrayList<>();
        
        try (Connection connection = DbConnectionUtil.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                WorkoutPlan plan = new WorkoutPlan();
                plan.setPlanId(rs.getInt("plan_id"));
                plan.setTrainerId(rs.getInt("trainer_id"));
                plan.setPlanName(rs.getString("plan_name"));
                plan.setDurationWeeks(rs.getInt("duration_weeks"));
                plan.setDescription(rs.getString("description"));
                
                plans.add(plan);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return plans;
    }
    
    @Override
    public List<WorkoutPlan> getAllWorkoutPlans() {
        return getAllWorkoutPlansInternal();
    }
    
    @Override
    public List<WorkoutPlan> getAllworkoutPlans() {
        return getAllWorkoutPlansInternal();
    }
    
    public List<WorkoutPlan> getAllworkoutPlans(Object... args) {
        return getAllWorkoutPlansInternal();
    }
}