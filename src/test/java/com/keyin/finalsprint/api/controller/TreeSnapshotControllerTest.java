package com.keyin.finalsprint.api.controller;

import com.keyin.finalsprint.api.entity.TreeSnapshot;
import com.keyin.finalsprint.api.service.TreeSnapshotService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TreeSnapshotControllerTest {

    @Mock
    private TreeSnapshotService service;

    @InjectMocks
    private TreeSnapshotController controller;

    private MockMvc mockMvc;
    private TreeSnapshot snapshot;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        snapshot = new TreeSnapshot();
        snapshot.setInputNumbers("4,5,6");
        snapshot.setTreeJson("{\"value\":4}");
    }

    @Test
    void previousSnapshots_ShouldReturnSnapshotsList() throws Exception {
        when(service.getAllSnapshots()).thenReturn(Arrays.asList(snapshot));

        mockMvc.perform(get("/api/tree-snapshots/previous-snapshots")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].inputNumbers").value("4,5,6"))
                .andExpect(jsonPath("$[0].treeJson").value("{\"value\":4}"));
    }
}


