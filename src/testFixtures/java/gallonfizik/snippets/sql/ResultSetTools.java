package gallonfizik.snippets.sql;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetTools {
    private final ResultSet rs;

    public ResultSetTools(ResultSet rs) {
        this.rs = rs;
    }

    /**
     * {@link ResultSet} returns default values when requested type is a boxed builtin (e.g. Integer),
     * and DB result has NULL for value.
     */
    public <T> T getNullableValue(String column, Class<T> targetClass) throws SQLException {
        T object = rs.getObject(column, targetClass);

        if (rs.wasNull()) {
            return null;
        }

        return object;
    }
}
