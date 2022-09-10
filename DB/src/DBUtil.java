import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public static void main(String[] args) throws Exception {
        String url = "jdbc:postgresql://localhost:5432/test";

        Class.forName("org.postgresql.Driver");
        Connection db = DriverManager.getConnection(url, "postgres", "root");

        System.out.println("Initializing database and creating tables");
        db.setAutoCommit(false);
        ScriptUtils.executeSqlScript(db, new ClassPathResource("data.sql"));
        db.setAutoCommit(true);

        db.close();

        System.out.println("Database initialized");
    }

}
