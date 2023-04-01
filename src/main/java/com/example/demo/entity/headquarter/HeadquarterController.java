package com.example.demo.entity.headquarter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/headquarter")
public class HeadquarterController {
    @Autowired
    private HeadquarterService headquarterService;

    @GetMapping("/")
    public ResponseEntity<List<Headquarter>> getAllHeadquarter() {
        return new ResponseEntity<>(headquarterService.getAllRecord(), HttpStatus.OK);
    }

    @PostMapping("/store")
    public ResponseEntity<Headquarter> store(@RequestBody Headquarter headquarter) {
        return headquarterService.storeHeadquater(headquarter);
    }
}
