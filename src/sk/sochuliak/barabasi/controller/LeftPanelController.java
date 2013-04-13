package sk.sochuliak.barabasi.controller;

import sk.sochuliak.barabasi.graph.LeftPanel;

public class LeftPanelController {

	private LeftPanel leftPanel = null;
	
	private int pointSetterCounter = 0;
	
	public LeftPanelController(LeftPanel leftPanel) {
		this.leftPanel = leftPanel;
	}
	
	public void setPoint(double x, double y) {
		if (this.pointSetterCounter == 0) {
			this.setStartPoint(x, y);
			this.pointSetterCounter++;
		} else {
			this.setEndPoint(x, y);
			this.pointSetterCounter = 0;
		}
	}

	private void setStartPoint(double x, double y) {
		leftPanel.setStartPoint(x, y);
	}
	
	private void setEndPoint(double x, double y) {
		leftPanel.setEndPoint(x, y);
	}

	public void setResult(double k) {
		leftPanel.setK(k);
	}
}
