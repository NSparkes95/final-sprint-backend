package com.keyin.finalsprint.api.service;

import com.keyin.finalsprint.api.entity.Gate;
import com.keyin.finalsprint.api.repository.GateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GateService {
    private final GateRepository gateRepository;

    @Autowired
    public GateService(GateRepository gateRepository) {
        this.gateRepository = gateRepository;
    }

    public List<Gate> getAllGates() {
        return gateRepository.findAll();
    }

    public Optional<Gate> getGateById(Long id) {
        return gateRepository.findById(id);
    }

    public Gate createGate(Gate gate) {
        return gateRepository.save(gate);
    }

    public Gate updateGate(Long id, Gate updatedGate) {
        return gateRepository.findById(id)
                .map(gate -> {
                    gate.setCode(updatedGate.getCode());
                    gate.setAirport(updatedGate.getAirport());
                    return gateRepository.save(gate);
                })
                .orElseThrow(() -> new RuntimeException("Gate not found"));
    }

    public void deleteGate(Long id) {
        gateRepository.deleteById(id);
    }
}
