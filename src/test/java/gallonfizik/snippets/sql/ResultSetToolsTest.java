package gallonfizik.snippets.sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ResultSetToolsTest {
    @Mock
    private ResultSet rs;

    private ResultSetTools instance;

    @BeforeEach
    void setUp() {
        instance = new ResultSetTools(rs);
    }

    @Test
    void givenLatestReadWasNullThenReturnNull() throws SQLException {
        when(rs.wasNull()).thenReturn(true);

        ExampleClass result = instance.getNullableValue("column", ExampleClass.class);

        assertThat(result).isNull();
    }

    @Test
    void givenLatestReadWasNotNullThenReturnValue() throws SQLException {
        when(rs.wasNull()).thenReturn(false);
        String column = "column";
        ExampleClass expected = new ExampleClass();
        when(rs.getObject(column, ExampleClass.class)).thenReturn(expected);
        ExampleClass result = instance.getNullableValue(column, ExampleClass.class);

        assertThat(result).isEqualTo(expected);
    }

    private record ExampleClass() implements Serializable {
    }
}
