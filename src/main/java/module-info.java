module demo.main {
    requires spring.web;
    requires java.sql;
    requires spring.boot.autoconfigure;
    requires spring.boot;

    opens com.example.demo to spring.core, spring.beans;
}