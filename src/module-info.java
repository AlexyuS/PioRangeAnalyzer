module PioRangeAnalyzer {
	exports application;
	exports application.controller;
	exports application.stage;
	
	requires java.logging;
	requires transitive javafx.base;
	requires transitive javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires org.apache.commons.lang3;
	requires spring.context;
	requires spring.core;
	requires spring.beans;
	
	opens application to spring.core;

}