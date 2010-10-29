<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ include file="/common/taglibs.jsp"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><s:text name="index.title"/></title>
<%@ include file="/common/styles.jsp" %>
<%@ include file="/common/scripts.jsp" %>
<script type="text/javascript">
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
	 
	var paneWest = new DHTMLSuite.paneSplitterPaneModel({
		position: "west",
		id: "westPane",
		size: 200,
		minSize: 100,
		maxSize: 300,
		scrollbars: true
	});

	var paneSouth = new DHTMLSuite.paneSplitterPaneModel({
		position: "south",
		id: "southPane",
		size: 60,
		minSize: 50,
		maxSize: 130,
		resizable: false
	});
	 
	var paneContent = new DHTMLSuite.paneSplitterPaneModel({
		position: "center",
		id: "contentPane",
		size: 150,
		minSize: 100,
		maxSize: 200
	});

	paneModel.addPane(paneSouth);
	paneModel.addPane(paneNorth);
	paneModel.addPane(paneWest);
	paneModel.addPane(paneContent);

	var paneSplitter = new DHTMLSuite.paneSplitter();
	paneSplitter.addModel(paneModel);
	paneSplitter.init();
}

$(function() {
	initBorderLayout();
});
</script>
</head>

<body>
<div id="northPane">
<s:text name="index.projname"></s:text>
</div>
<div id="westPane">
</div>
<div id="contentPane">
</div>
<div id="southPane">
<s:text name="index.footer.content"></s:text>
</div>
</body>
</html>
