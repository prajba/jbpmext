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
