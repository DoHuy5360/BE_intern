package com.example.demo.entity.headquarter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.KIT.RES.Response;

@RestController
@RequestMapping("/api/v2/headquarter")
@CrossOrigin("*")
public class HeadquarterController {
    @Autowired
    private HeadquarterService headquarterService;

    @GetMapping("/")
    public Response getAllHeadquarter() {
        return headquarterService.getAllRecord();
    }

    @PostMapping("/store")
    public Response store(@RequestBody Headquarter headquarter) {
        return headquarterService.storeHeadquater(headquarter);
    }

    @PutMapping("/{id}/update")
    public Response update(@PathVariable("id") String id, @RequestBody Headquarter headquarter) {
        return headquarterService.updateHeadquarter(id, headquarter);
    }

    @DeleteMapping("/{id}/delete")
    public Response delete(@PathVariable("id") String id) {
        return headquarterService.deleteHeadquarter(id);
    }
}
