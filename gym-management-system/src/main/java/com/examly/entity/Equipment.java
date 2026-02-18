package com.examly.entity;

import java.util.Objects;

public class Equipment {
    private int equipmentId;
    private String name;
    private String type;
    private int quantity;
    private String maintenanceStatus;

    // Default constructor
    public Equipment() {}

    // Constructor for test: (String, String, int, String)
    public Equipment(String name, String type, int quantity, String maintenanceStatus) {
        this.name = name;
        this.type = type;
        this.quantity = quantity;
        this.maintenanceStatus = maintenanceStatus;
    }

    // Constructor with all parameters
    public Equipment(int equipmentId, String name, String type, int quantity, String maintenanceStatus) {
        this.equipmentId = equipmentId;
        this.name = name;
        this.type = type;
        this.quantity = quantity;
        this.maintenanceStatus = maintenanceStatus;
    }

    // Getters and Setters
    public int getEquipmentId() { return equipmentId; }
    public void setEquipmentId(int equipmentId) { this.equipmentId = equipmentId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public String getMaintenanceStatus() { return maintenanceStatus; }
    public void setMaintenanceStatus(String maintenanceStatus) { this.maintenanceStatus = maintenanceStatus; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipment equipment = (Equipment) o;
        return equipmentId == equipment.equipmentId;
    }

    @Override
    public int hashCode() { return Objects.hash(equipmentId); }

    @Override
    public String toString() {
        return "Equipment{" +
                "equipmentId=" + equipmentId +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", quantity=" + quantity +
                ", maintenanceStatus='" + maintenanceStatus + '\'' +
                '}';
    }
}