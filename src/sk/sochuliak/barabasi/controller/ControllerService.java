package sk.sochuliak.barabasi.controller;

import org.jfree.chart.JFreeChart;

import sk.sochuliak.barabasi.graph.LeftPanel;

public class ControllerService {

	private static LeftPanelController leftPanelController;
	
	private static GraphController graphController;
	
	public static void registerLeftPanelController(LeftPanel leftPanel) {
		ControllerService.leftPanelController = new LeftPanelController(leftPanel);
	}
	
	public static void registerGraphPanelController(JFreeChart chart) {
		ControllerService.graphController = new GraphController(chart);
	}

	public static LeftPanelController getLeftPanelController() {
		return leftPanelController;
	}

	public static GraphController getGraphController() {
		return graphController;
	}
	
	
}
