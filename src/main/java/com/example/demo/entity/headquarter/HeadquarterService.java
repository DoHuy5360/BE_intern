package com.example.demo.entity.headquarter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entity.timekeeping.Timekeeping;

public class HeadquarterService {
    @Autowired
    private HeadquarterRepository timekeepingRepository;

    public List<Timekeeping> getAllTimekeeping() {
        return timekeepingRepository.findAll();
    }

    public List<Timekeeping> getTimekeepingByAccountRole(String accoundRole) {
        return timekeepingRepository.findAllByAccountRole(accoundRole);
    }

    public List<Timekeeping> getTimekeepingByAccountId(String accoundId) {
        return timekeepingRepository.findAllByAccountId(accoundId);
    }

    public List<Timekeeping> getTimekeepingByTimeRange(Date start, Date end) {
        return timekeepingRepository.findAllByCreateAtBetween(start, end);
    }

    public List<Timekeeping> getTimekeepingByTimeRangeAndAccountRole(Date start, Date end, String accoundRole) {
        return timekeepingRepository.findAllByCreateAtBetweenAndAccountRole(start, end, accoundRole);
    }

    public List<Timekeeping> getTimekeepingByTimeRangeAndAccountId(Date start, Date end, String accoundId) {
        return timekeepingRepository.findAllByCreateAtBetweenAndaccoundId(start, end, accoundId);
    }

    public List<Timekeeping> getTimekeepingByTimeRangeAndAccountRoleAndAccountId(Date start, Date end, String AccountRole, String accoundId) {
        return timekeepingRepository.findAllByCreateAtBetAweenAndAccountRoleAndaccoundId(start, end, AccountRole, accoundId);
    }
}
