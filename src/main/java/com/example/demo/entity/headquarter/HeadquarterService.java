package com.example.demo.entity.headquarter;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.KIT.RES.Message;
import com.example.demo.KIT.RES.Response;

@Service
public class HeadquarterService {
    @Autowired
    private HeadquarterRepository headquarterRepository;

    public List<Headquarter> getAllRecord() {
        return headquarterRepository.findAll();
    }

    public ResponseEntity<Headquarter> storeHeadquater(Headquarter headquarter) {
        headquarterRepository.save(headquarter);
        Optional<Headquarter> oneHq = headquarterRepository.findById(headquarter.getHeadquarterId());
        return (oneHq.isPresent())
                ? new ResponseEntity<>(oneHq.get(), HttpStatus.OK)
                : new ResponseEntity<Headquarter>(HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Transactional
    public Response updateHeadquarter(String id, Headquarter headquarter) {
        Optional<Headquarter> oneH = headquarterRepository.findById(id);
        if (oneH.isPresent()) {
            try {
                Headquarter _Headquarter = oneH.get();
                _Headquarter.setHeadquarterName(headquarter.getHeadquarterName());
                _Headquarter.setHeadquarterAddress(headquarter.getHeadquarterAddress());
                headquarterRepository.save(_Headquarter);
            } catch (Exception e) {
                return new Response(HttpStatus.BAD_REQUEST, Message.UPDATE_FAIL);
            }
            return new Response(HttpStatus.OK, Message.UPDATE_SUCCESS, headquarter);
        } else {
            return new Response(HttpStatus.BAD_REQUEST, Message.NOT_FOUND);
        }
    }

    public Response deleteHeadquarter(String id) {
        Optional<Headquarter> oneH = headquarterRepository.findById(id);
        if (oneH.isPresent()) {
            try {
                headquarterRepository.deleteById(id);
            } catch (Exception e) {
                return new Response(HttpStatus.BAD_REQUEST, Message.DELETE_FAIL);
            }
            return new Response(HttpStatus.OK, Message.DELETE_SUCCESS);
        } else {
            return new Response(HttpStatus.BAD_REQUEST, Message.NOT_FOUND);
        }
    }
}
