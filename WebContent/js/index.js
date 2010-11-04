var __open_urls = {};

function openUrl(url) {
	var wnd = __open_urls[url];
	if (!wind) {
		wnd = asdfaf;
	}
}

function initBorderLayout() {
	var paneModel = new DHTMLSuite.paneSplitterModel();
	DHTMLSuite.commonObj.setCssCacheStatus(false);
	
	var paneNorth = new DHTMLSuite.paneSplitterPaneModel({
		position: "north",
		id: "northPane",
		size: 65,
		scrollbars: false,
		resizable: false,
		collapsable: false
	});
	paneNorth.addContent(
		new DHTMLSuite.paneSplitterContentModel({
			id: "northContent",
			htmlElementId: "northContent",
			tabTitle: "North pane" 
		}) 
	);
	 
	var paneWest = new DHTMLSuite.paneSplitterPaneModel({
		position: "west",
		id: "westPane",
		size: 200,
		minSize: 100,
		maxSize: 300,
		scrollbars: true
	});
	paneWest.addContent(
		new DHTMLSuite.paneSplitterContentModel({
			id: "westContent",
			htmlElementId: "westContent",
			tabTitle: "West pane" 
		}) 
	);

	var paneSouth = new DHTMLSuite.paneSplitterPaneModel({
		position: "south",
		id: "southPane",
		size: 40,
		scrollbars: false,
		resizable: false,
		collapsable: false
	});
	paneSouth.addContent(
		new DHTMLSuite.paneSplitterContentModel({
			id: "southContent",
			htmlElementId: "southContent",
			tabTitle: "South pane" 
		}) 
	);
	 
	var paneContent = new DHTMLSuite.paneSplitterPaneModel({
		position: "center",
		id: "contentPane",
		size: 150,
		scrollbars: false,
		closable: false
	});
	paneContent.addContent(
		new DHTMLSuite.paneSplitterContentModel({
			id: "workareaContent",
			htmlElementId: "workareaContent",
			tabTitle: "Workbench pane" 
		}) 
	);

	paneModel.addPane(paneSouth);
	paneModel.addPane(paneNorth);
	paneModel.addPane(paneWest);
	paneModel.addPane(paneContent);

	var paneSplitter = new DHTMLSuite.paneSplitter();
	paneSplitter.addModel(paneModel);
	paneSplitter.init();
}

function initTabs() {
	$("#tabs").tabs("div.tabpanes > div");
}

$(function() {
	initBorderLayout();
	initTabs();
});
