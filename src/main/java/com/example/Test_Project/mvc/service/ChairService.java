package com.example.Test_Project.mvc.service;

import com.example.Test_Project.mvc.entity.Chair;
import com.example.Test_Project.mvc.repository.ChairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChairService {
    @Autowired
    private ChairRepository chairRepository;

    public List<Chair> getAllChairs() {
        return chairRepository.findAll();
    }

    public Chair getChairById(int id) {
        return chairRepository.findById(id).orElse(null);
    }

    public void saveChair(Chair chair) {
        chairRepository.save(chair);
    }

    public void deleteChair(int id) {
        chairRepository.deleteById(id);
    }
}

