package com.examly.entity;

import java.util.Objects;

public class WorkoutPlan {
    private int planId;
    private int trainerId;
    private String planName;
    private int durationWeeks;
    private String description;

    // Default constructor
    public WorkoutPlan() {}

    // Constructor for test: (int, String, int, String)
    public WorkoutPlan(int trainerId, String planName, int durationWeeks, String description) {
        this.trainerId = trainerId;
        this.planName = planName;
        this.durationWeeks = durationWeeks;
        this.description = description;
    }

    // Constructor with all parameters
    public WorkoutPlan(int planId, int trainerId, String planName, int durationWeeks, String description) {
        this.planId = planId;
        this.trainerId = trainerId;
        this.planName = planName;
        this.durationWeeks = durationWeeks;
        this.description = description;
    }

    // Getters and Setters
    public int getPlanId() { return planId; }
    public void setPlanId(int planId) { this.planId = planId; }
    public int getTrainerId() { return trainerId; }
    public void setTrainerId(int trainerId) { this.trainerId = trainerId; }
    public String getPlanName() { return planName; }
    public void setPlanName(String planName) { this.planName = planName; }
    public int getDurationWeeks() { return durationWeeks; }
    public void setDurationWeeks(int durationWeeks) { this.durationWeeks = durationWeeks; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkoutPlan that = (WorkoutPlan) o;
        return planId == that.planId;
    }

    @Override
    public int hashCode() { return Objects.hash(planId); }

    @Override
    public String toString() {
        return "WorkoutPlan{" +
                "planId=" + planId +
                ", trainerId=" + trainerId +
                ", planName='" + planName + '\'' +
                ", durationWeeks=" + durationWeeks +
                ", description='" + description + '\'' +
                '}';
    }
}