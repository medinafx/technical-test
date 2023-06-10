package com.fichosa.technicaltest.repository;

import com.fichosa.technicaltest.model.ScapeResult;
import com.fichosa.technicaltest.model.Stats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

@Repository
public interface ScapeResultRepository extends JpaRepository<ScapeResult, Integer> {

    @Query("SELECT SUM(CASE WHEN tr.scape = TRUE THEN 1 ELSE 0 END) AS countSuccessfulEscape" +
            ",SUM(CASE WHEN tr.scape = FALSE THEN 1 ELSE 0 END) AS countUnsuccessfulEscape " +
            "FROM ScapeResult tr")
    @QueryHints
    Object[][] getStatsSum();

    default Stats getStats() {
        Object[][] sums = getStatsSum();
        long successfullEscape = 0l;
        long unsuccessfullEscape = 0l;

        if (sums != null && sums[0] != null && sums[0][0] != null) {
            successfullEscape = ((Number) sums[0][0]).longValue();
            unsuccessfullEscape = ((Number) sums[0][1]).longValue();
        }

        return new Stats(successfullEscape, unsuccessfullEscape);
    }
}
