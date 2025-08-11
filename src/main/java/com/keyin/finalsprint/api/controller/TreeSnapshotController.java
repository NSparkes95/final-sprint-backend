package com.keyin.finalsprint.api.controller;

import com.keyin.finalsprint.api.entity.TreeSnapshot;
import com.keyin.finalsprint.api.service.TreeSnapshotService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/tree-snapshots")
public class TreeSnapshotController {

    private final TreeSnapshotService service;

    public TreeSnapshotController(TreeSnapshotService service) {
        this.service = service;
    }

    @GetMapping("/previous-snapshots")
    public List<TreeSnapshot> previousSnapshots() {
        return service.getAllSnapshots();
    }

    @PostMapping("/process-numbers")
    public TreeSnapshot processNumbers(@RequestParam("numbers") String numbers) {
        TreeSnapshot snapshot = new TreeSnapshot();
        snapshot.setInputNumbers(numbers);
        snapshot.setTreeJson(generateTreeJson(numbers));
        snapshot.setCreatedAt(LocalDateTime.now());
        return service.createTreeSnapshot(snapshot);
    }

    private String generateTreeJson(String numbers) {
        return "{\"numbers\": " + Arrays.toString(numbers.split(",")) + "}";
    }
}
