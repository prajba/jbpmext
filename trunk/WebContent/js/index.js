var showAlert = $.messager.alert;
var showConfirm = $.messager.confirm;
var showPrompt = $.messager.prompt;

this.title = $("head > title").text();

function showEditorDialog(conf, wnd) {
	var windowName = conf.name || "";
	var url = conf.url || "about:blank";
	var w = conf.width ? ",width=" + conf.width : "", h = conf.height ? ",height=" + conf.height : "";
	var nwnd = window.open(url, windowName, "scrollbars=no,toolbar=no,location=no,directories=no,status=no,menubar=no" + w + h);
	window.__editorWindow = nwnd;
	window.__editorData = conf.data;
	window.__editorOpener = wnd;
}

function closeEditorDialog() {
	if (window.__editorWindow)
		window.__editorWindow.close();
}

function openUrl(url, eventSrc) {
	if (url.charAt(0) == '/') url = CONTEXT_ROOT + url;
	var tabTitle = eventSrc.innerText;
	if ($("#tabpanes").tabs("exists", tabTitle)) {
		$("#tabpanes").tabs("select", tabTitle);
	} else {
		$("#tabpanes").tabs("add", {
			title: tabTitle,
			closable: true,
			content: "<iframe src='" + url + "' class='filled' frameborder='0' scrolling='auto'></iframe>"
		});
	}
}

function initBorderLayout() {
	$("#northContent").attr("region", "north").attr("border", false).height(40);
	$("#westContent").attr("region", "west").attr("border", false).attr("split", true).width(200);
	$("#workareaContent").attr("region", "center");
	$("#southContent").attr("region", "south").attr("border", false).height(25);
	$("body").layout();
}

function initTabs() {
	$("#tabpanes").attr("fit", true).tabs();
}

$(function() {
	initBorderLayout();
	initTabs();
});
