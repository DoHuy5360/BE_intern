package com.example.demo.entity.headquarter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class HeadquarterService {
    @Autowired
    private HeadquarterRepository headquarterRepository;

    public ResponseEntity<Headquarter> storeHeadquater(Headquarter headquarter) {
        headquarterRepository.save(headquarter);
        Optional<Headquarter> oneHq = headquarterRepository.findById(headquarter.getHeadquarterId());
        return (oneHq.isPresent())
                ? new ResponseEntity<>(oneHq.get(), HttpStatus.OK)
                : new ResponseEntity<Headquarter>(HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
