package com.fichosa.technicaltest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Stats {
    @JsonProperty("count_successful_escape")
    private long countSuccessfulEscape;

    @JsonProperty("count_unsuccessful_escape")
    private long countUnsuccessfulEscape;

    @JsonProperty("ratio")
    private BigDecimal ratio = BigDecimal.ZERO;

    public Stats(long countSuccessfulEscape, long countUnsuccessfulEscape) {
        this.countSuccessfulEscape = countSuccessfulEscape;
        this.countUnsuccessfulEscape = countUnsuccessfulEscape;

        if (countUnsuccessfulEscape != 0) {
            this.ratio = BigDecimal.valueOf(countSuccessfulEscape)
                    .divide(BigDecimal.valueOf(countUnsuccessfulEscape), 2, RoundingMode.HALF_UP);
        }
    }

    public long getCountSuccessfulEscape() {
        return countSuccessfulEscape;
    }

    public long getCountUnsuccessfulEscape() {
        return countUnsuccessfulEscape;
    }

    public BigDecimal getRatio() {
        return ratio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stats stats = (Stats) o;
        return ratio.doubleValue() == stats.ratio.doubleValue();
    }
}
