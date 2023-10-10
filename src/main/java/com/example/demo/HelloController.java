package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Objects;

@RestController
public class HelloController {

    public record Message(String body, String db, String javaVendor) {}

    private final DataSource dataSource;

    public HelloController(DataSource dataSource) {
        this.dataSource = Objects.requireNonNull(dataSource, "dataSource");
    }

    @GetMapping("/")
    public Message index() throws SQLException {
        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement("select concat(@@version_comment, ' ', @@version)");
             var rs = stmt.executeQuery()
        ) {
            rs.next();
            return new Message(
                    "Hello, my beautiful modular world!",
                    rs.getString(1),
                    System.getProperty("java.vendor")
            );
        }
    }

}
