package dhurd.c195.clientdb.helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class dBStatement {

    private static PreparedStatement statement;

    public static void setPreparedStatement(Connection connection, String sql) throws SQLException {
        statement = connection.prepareStatement(sql);
    }

    public static PreparedStatement getPreparedStatement() {
        return statement;
    }
}
