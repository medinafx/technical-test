package com.fichosa.technicaltest.controller;

import com.fichosa.technicaltest.dfs.PrisonerDFS;
import com.fichosa.technicaltest.model.ScapeResult;
import com.fichosa.technicaltest.model.Stats;
import com.fichosa.technicaltest.repository.ScapeResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class PrisonerController {

    private static final PrisonerDFS prisonerDFS = new PrisonerDFS();
    private final ScapeResultRepository scapeResultRepository;

    @Autowired
    public PrisonerController(ScapeResultRepository scapeResultRepository) {
        this.scapeResultRepository = scapeResultRepository;
    }

    @PostMapping("/prisoner")
    @ResponseStatus(HttpStatus.OK)
    void post(@RequestBody StringArrayWrapper prison) {
        boolean canScape = prisonerDFS.canEscape(prison.getPrison());
        this.scapeResultRepository.save(new ScapeResult(canScape));

        if (!canScape) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Prisoner can't scape");
        }
    }

    @GetMapping("/stats")
    @ResponseStatus(HttpStatus.OK)
    Stats get() {
        return this.scapeResultRepository.getStats();
    }
}
