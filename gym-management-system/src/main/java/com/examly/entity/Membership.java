package com.examly.entity;

import java.util.Date;
import java.util.Objects;

public class Membership {
    private int membershipId;
    private int memberId;
    private String planType;
    private Date startDate;
    private Date endDate;
    private double totalFee;

    // Default constructor
    public Membership() {}

    // Constructor for test: (int, int, String, Date, Date, double)
    public Membership(int membershipId, int memberId, String planType, Date startDate, Date endDate, double totalFee) {
        this.membershipId = membershipId;
        this.memberId = memberId;
        this.planType = planType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalFee = totalFee;
    }

    // Constructor for MainModule
    public Membership(int memberId, String planType, Date startDate, Date endDate, double totalFee) {
        this.memberId = memberId;
        this.planType = planType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalFee = totalFee;
    }

    // Getters and Setters
    public int getMembershipId() { return membershipId; }
    public void setMembershipId(int membershipId) { this.membershipId = membershipId; }
    public int getMemberId() { return memberId; }
    public void setMemberId(int memberId) { this.memberId = memberId; }
    public String getPlanType() { return planType; }
    public void setPlanType(String planType) { this.planType = planType; }
    public Date getStartDate() { return startDate; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }
    public Date getEndDate() { return endDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }
    public double getTotalFee() { return totalFee; }
    public void setTotalFee(double totalFee) { this.totalFee = totalFee; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Membership that = (Membership) o;
        return membershipId == that.membershipId;
    }

    @Override
    public int hashCode() { return Objects.hash(membershipId); }

    @Override
    public String toString() {
        return "Membership{" +
                "membershipId=" + membershipId +
                ", memberId=" + memberId +
                ", planType='" + planType + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", totalFee=" + totalFee +
                '}';
    }
}