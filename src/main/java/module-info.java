module demo.main {
    requires spring.web;
    requires java.sql;
    requires spring.boot.autoconfigure;
    requires spring.boot;

    requires com.zaxxer.hikari;

    requires org.apache.tomcat.embed.core;

    opens com.example.demo to spring.core, spring.beans;
}