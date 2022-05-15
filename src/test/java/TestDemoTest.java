import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.*;

class TestDemoTest extends Object {
    private TestDemo test;

    @BeforeEach
    void setUp() {
        test = new TestDemo();
    }

    @ParameterizedTest
    @MethodSource("TestDemoTest#argumentsForAddPositive")
    void assertThatTwoPositiveNumbersAreAddedCorrectly(int a, int b, int expected, boolean expectException) {
        if(!expectException) {
            assertThat(test.addPositive(a, b)).isEqualTo(expected);
        } else {
            assertThatThrownBy(() -> test.addPositive(a,b)).isInstanceOf(IllegalArgumentException.class);
        }
    }

    static Stream<Arguments> argumentsForAddPositive() {
        return Stream.of(
                arguments(2,4,6,false),
                arguments(14134,3,14137,false),
                arguments(123123,-1,0,true),
                arguments(-2,-3,0,true),
                arguments(0,1,0,true));
    }

    @Test
    void assertThatNumberSquaredIsCorrect() {
        TestDemo mockDemo = Mockito.spy(test);
        doReturn(5).when(mockDemo).getRandomInt();
        int fiveSquared = mockDemo.randomNumberSquared();
        assertThat(fiveSquared).isEqualTo(25);
    }

}