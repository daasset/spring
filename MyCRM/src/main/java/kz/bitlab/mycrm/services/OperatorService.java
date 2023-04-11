package kz.bitlab.mycrm.services;

import kz.bitlab.mycrm.entities.Operator;

import java.util.List;

public interface OperatorService {

    /*
        Getters
     */
    Operator getOperatorById(Long id);
    List<Operator> getAllOperators();


    /*
        Modifiers
     */
    List<Operator> getAllOperatorsByIds(List<Long> ids);
}
