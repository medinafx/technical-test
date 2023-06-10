package com.fichosa.technicaltest.repository;

import com.fichosa.technicaltest.model.Stats;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;

@DataJpaTest
@Sql(scripts = "/test-data/create-data.sql")
@Sql(scripts = "/test-data/cleanup-data.sql", executionPhase = AFTER_TEST_METHOD)
class ScapeResultRepositoryTest {

    @Autowired
    private ScapeResultRepository scapeResultRepository;

    @Test
    public void getStats() {
        Stats statsExpected = new Stats(9, 5);

        Stats actualStats = scapeResultRepository.getStats();
        assertThat(actualStats).isEqualTo(statsExpected);
    }
}