package com.example.demo.entity.headquarter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/headquarter")
public class HeadquarterController {
    @Autowired
    private HeadquarterService headquarterService;

    @GetMapping("/")
    public void getAllHeadquarter() {

    }
}
