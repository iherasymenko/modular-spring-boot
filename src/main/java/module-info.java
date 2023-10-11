module demo.main {
    requires spring.web;
    requires java.sql;
    requires spring.boot.autoconfigure;
    requires spring.boot;

    requires com.zaxxer.hikari;

    requires org.apache.tomcat.embed.core;
    requires com.fasterxml.jackson.databind;

    opens com.example.demo to
            spring.core, spring.beans, spring.web, com.fasterxml.jackson.databind;
}