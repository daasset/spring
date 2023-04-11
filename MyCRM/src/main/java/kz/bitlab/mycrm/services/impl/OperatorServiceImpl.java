package kz.bitlab.mycrm.services.impl;

import kz.bitlab.mycrm.entities.Operator;
import kz.bitlab.mycrm.repository.OperatorRepository;
import kz.bitlab.mycrm.services.OperatorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OperatorServiceImpl implements OperatorService {
    private OperatorRepository operatorRepository;
    @Override
    public Operator getOperatorById(Long id) {
        return operatorRepository.findById(id).get();
    }
    @Override
    public List<Operator> getAllOperators() {
        return operatorRepository.findAll();
    }

    @Override
    public List<Operator> getAllOperatorsByIds(List<Long> ids) {
        return operatorRepository.findAllById(ids);
    }
}
