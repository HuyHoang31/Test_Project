package com.example.Test_Project.mvc.repository;

import com.example.Test_Project.mvc.entity.Chair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChairRepository extends JpaRepository<Chair,Integer> {
}
