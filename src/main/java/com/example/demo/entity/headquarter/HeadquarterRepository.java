package com.example.demo.entity.headquarter;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeadquarterRepository extends CrudRepository<Headquarter, String> {
    List<Headquarter> findAll();
}
