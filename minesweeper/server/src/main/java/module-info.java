module minesweeper.server {

    requires spring.web;
    requires spring.beans;
    requires spring.boot;
    requires spring.context;
    requires spring.boot.autoconfigure;
    requires minesweeper.core;

    opens minesweeper.server to spring.beans, spring.context, spring.web;
} 
