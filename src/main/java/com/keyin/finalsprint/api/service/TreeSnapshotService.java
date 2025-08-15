package com.keyin.finalsprint.api.service;

import com.keyin.finalsprint.api.entity.TreeSnapshot;
import com.keyin.finalsprint.api.repository.TreeSnapshotRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreeSnapshotService {

    private final TreeSnapshotRepository repository;

    public TreeSnapshotService(TreeSnapshotRepository repository) {
        this.repository = repository;
    }

    public List<TreeSnapshot> getAllSnapshots() {
        return repository.findAll();
    }

    public TreeSnapshot createTreeSnapshot(TreeSnapshot snapshot) {
        return repository.save(snapshot);
    }
}
