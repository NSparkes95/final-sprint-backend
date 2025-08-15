package com.keyin.finalsprint.api.repository;

import com.keyin.finalsprint.api.entity.TreeSnapshot;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class TreeSnapshotRepositoryTest {

    @Autowired
    private TreeSnapshotRepository repository;

    @Test
    void saveAndFindAll_ShouldReturnSavedSnapshot() {
        TreeSnapshot snapshot = new TreeSnapshot();
        snapshot.setInputNumbers("7,2,9");
        snapshot.setTreeJson("{\"value\":7}");
        snapshot.setCreatedAt(LocalDateTime.now());

        repository.save(snapshot);

        List<TreeSnapshot> snapshots = repository.findAll();
        assertThat(snapshots).hasSize(1);
        assertThat(snapshots.get(0).getInputNumbers()).isEqualTo("7,2,9");
    }
}

