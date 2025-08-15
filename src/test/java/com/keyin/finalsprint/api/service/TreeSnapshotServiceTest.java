package com.keyin.finalsprint.api.service;

import com.keyin.finalsprint.api.entity.TreeSnapshot;
import com.keyin.finalsprint.api.repository.TreeSnapshotRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class TreeSnapshotServiceTest {

    @Mock
    private TreeSnapshotRepository repository;

    @InjectMocks
    private TreeSnapshotService service;

    private TreeSnapshot snapshot;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        snapshot = new TreeSnapshot();
        snapshot.setId(1L);
        snapshot.setInputNumbers("1,2,3");
        snapshot.setTreeJson("{\"value\":1}");
    }

    @Test
    void getAllSnapshots_ShouldReturnSnapshotsFromRepository() {
        when(repository.findAll()).thenReturn(Arrays.asList(snapshot));

        List<TreeSnapshot> result = service.getAllSnapshots();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getInputNumbers()).isEqualTo("1,2,3");
        verify(repository, times(1)).findAll();
    }
}

