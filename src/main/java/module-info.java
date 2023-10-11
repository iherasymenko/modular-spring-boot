module demo.main {

    requires spring.boot.starter.web;
    requires spring.boot.starter.jdbc;

    opens com.example.demo to
            spring.core, spring.beans, spring.web, com.fasterxml.jackson.databind;
}