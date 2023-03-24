package com.example.demo.entity.timekeeping;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimekeepingRepository extends CrudRepository<Timekeeping, String> {

}
