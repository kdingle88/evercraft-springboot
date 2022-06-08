package com.kmd.evercraft.character;

import org.assertj.core.internal.bytebuddy.asm.Advice;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AbilityTest {
    @Test
    public void defaultScoreIsTen() {
        Ability ability = new Ability();

        assertEquals(10,ability.getScore());
    }

    @Test
    public void scoreCannotBeLessThanOne() {
        assertThrows(IllegalArgumentException.class, () -> new Ability(0));
    }

    @Test
    public void scoreCannotBeGreaterThanTwenty() {
        assertThrows(IllegalArgumentException.class, () -> new Ability(21));
    }

    @Nested
    @DisplayName("Ability Modifiers Test")
    class AbilityModifiersTest {
        private static Stream<Arguments> modifierParameters() {
            return Stream.of(
                    Arguments.of(10,0),
                    Arguments.of(12,1),
                    Arguments.of(14,2),
                    Arguments.of(16,3),
                    Arguments.of(18,4),
                    Arguments.of(20,5),
                    Arguments.of(8,-1),
                    Arguments.of(6,-2),
                    Arguments.of(4,-3),
                    Arguments.of(2,-4),
                    Arguments.of(1,-5)
            );
        }

        @ParameterizedTest
        @MethodSource("modifierParameters")
        public void whenProvidedScoreItReturnsTheModifier(int score, int expectedModifier) {
            Ability ability = new Ability(score);

            assertEquals(expectedModifier,ability.getModifier());
        }

    }

}
