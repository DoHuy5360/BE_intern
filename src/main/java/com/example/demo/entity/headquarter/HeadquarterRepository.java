package com.example.demo.entity.headquarter;


import com.example.demo.entity.timekeeping.Timekeeping;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;;;
public interface HeadquarterRepository extends JpaRepository<Timekeeping, String> {
    List<Timekeeping> findAll();

    List<Timekeeping> findAllByAccountRole(String accoundRole);
   

    List<Timekeeping> findAllByAccountId(String accoundId);

    List<Timekeeping> findAllByCreateAtBetween(Date start, Date end);

    List<Timekeeping> findAllByCreateAtBetweenAndAccountRole(Date start, Date end,String accoundRole );

    List<Timekeeping> findAllByCreateAtBetweenAndaccoundId(Date start, Date end, String accoundId);

    List<Timekeeping> findAllByCreateAtBetAweenAndAccountRoleAndaccoundId(Date start, Date end, String accoundRole, String accoundId);
}


