package com.examly.service;

import com.examly.entity.Trainer;
import java.util.List;

public interface TrainerDAO {
    boolean addTrainer(Trainer trainer);
    boolean addtrainer(Trainer trainer);
    List<Trainer> getAllTrainers();
    List<Trainer> getAlltrainers();
}