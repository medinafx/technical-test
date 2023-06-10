package com.fichosa.technicaltest.dfs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PrisonerDFSTest {
    private PrisonerDFS prisioner = new PrisonerDFS();

    @Test
    void whenNoGuardVisibleAndExitExists_ThenReturnsTrue() {
        String[] prision = {
                "|||||||S||",
                "|P ||   |",
                "||  | | |",
                "|v| | < |",
                "| |   | |",
                "|   |   |",
                "|||||||||"
        };

        assertTrue(prisioner.canEscape(prision));
    }

    @Test
    void whenGuardIsVisibleFromRight_thenReturnsFalse() {
        String[] prision = {
                "|||||||S<|",
                "|P ||   |",
                "||  | | |",
                "|v| | < |",
                "| |   | |",
                "|   |   |",
                "|||||||||"
        };

        assertFalse(prisioner.canEscape(prision));
    }

    @Test
    void whenGuardIsVisibleFromLeft_thenReturnsFalse() {
        String[] prision = {
                "||||||>S||",
                "|P ||   |",
                "||  | | |",
                "|v| | < |",
                "| |   | |",
                "|   |   |",
                "|||||||||"
        };

        assertFalse(prisioner.canEscape(prision));
    }

    @Test
    void whenGuardIsVisibleFromBelow_thenReturnsFalse() {
        String[] prision = {
                "|||||||S||",
                "|P ||   |",
                "||  | | |",
                "|v| | < |",
                "| |   | |",
                "|   |   |",
                "|||||||^|"
        };

        assertFalse(prisioner.canEscape(prision));
    }

    @Test
    void whenNoPrisonIsProvided_thenReturnsFalse() {
        String[] prision = {"", ""};
        assertFalse(prisioner.canEscape(prision));
    }
}