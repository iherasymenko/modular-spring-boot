package com.example.demo;

import org.springframework.expression.EvaluationException;
import org.springframework.expression.ParseException;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.io.StringWriter;
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

    @PostMapping("/rce_demo")
    public String rce(@RequestBody String body) {
        try {
            var parser = new SpelExpressionParser();
            var out = parser.parseExpression(body).getValue();
            return String.valueOf(out);
        } catch (EvaluationException | ParseException e) {
            var sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw, true));
            return sw.toString();
        }
    }

}
