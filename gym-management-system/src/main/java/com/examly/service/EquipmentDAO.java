package com.examly.service;

import com.examly.entity.Equipment;
import java.util.List;

public interface EquipmentDAO {
    boolean addEquipment(Equipment eq);
    List<Equipment> getAllEquipment();
}