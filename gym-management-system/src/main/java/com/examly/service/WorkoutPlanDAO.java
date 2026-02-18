package com.examly.service;

import com.examly.entity.WorkoutPlan;
import java.util.List;

public interface WorkoutPlanDAO {
    boolean addWorkoutPlan(WorkoutPlan plan);
    boolean addworkoutPlan(WorkoutPlan plan);
    List<WorkoutPlan> getPlansByTrainer(int trainerId);
    List<WorkoutPlan> getAllWorkoutPlans();
    List<WorkoutPlan> getAllworkoutPlans();
}