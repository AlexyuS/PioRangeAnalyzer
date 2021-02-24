module PioRangeAnalyzer {
	exports main.application;
	exports main.application.controller;
	exports main.application.stage;
	exports main.application.cards;
	exports main.application.fileParser;
	exports main.application.strategy;
	exports main.application.ui;
	exports main.application.strategy.calculator;
	exports test;
	
	requires java.logging;
	requires transitive javafx.base;
	requires transitive javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires org.apache.commons.lang3;
	requires spring.context;
	requires spring.core;
	requires spring.beans;
	requires java.desktop;
	requires java.annotation;
	requires junit;
	
	opens main.application to spring.core;
	opens main.application.strategy.calculator;
	opens main.application.stage to spring.core;
	opens main.application.controller to spring.core;

}