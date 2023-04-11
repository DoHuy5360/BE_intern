package com.example.demo.entity.headquarter;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.kit.res.Message;
import com.example.demo.kit.res.Response;

@Service
public class HeadquarterService {
    @Autowired
    private HeadquarterRepository headquarterRepository;

    public Response getAllRecord() {
        List<Headquarter> headquarters = (List<Headquarter>) headquarterRepository.findAll();
        return new Response(HttpStatus.OK, Message.READ_SUCCESS, headquarters.size(), headquarters);
    }

    public Response storeHeadquater(Headquarter headquarter) {
        try {
            headquarterRepository.save(headquarter);
        } catch (Exception e) {
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, Message.CREATE_FAIL);

        }
        return new Response(HttpStatus.OK, Message.CREATE_SUCCESS, headquarter);
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
