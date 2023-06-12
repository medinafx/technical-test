package com.fichosa.technicaltest.repository;

import com.fichosa.technicaltest.model.ScapeResult;
import com.fichosa.technicaltest.model.Stats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

@Repository
public interface ScapeResultRepository extends JpaRepository<ScapeResult, Integer> {

    @Query("SELECT new com.fichosa.technicaltest.model.Stats(SUM(CASE WHEN tr.scape = TRUE THEN 1 ELSE 0 END)" +
            ",SUM(CASE WHEN tr.scape = FALSE THEN 1 ELSE 0 END)) " +
            "FROM ScapeResult tr")
    @QueryHints
    Stats getStats();

}
