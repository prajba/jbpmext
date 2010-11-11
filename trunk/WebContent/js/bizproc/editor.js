INCLUDE([
	{type: "css", src: "/styles/procdesigner.css"},
	{type: "css", src: "/js/myflow/lib/jquery-ui-1.8.4.custom/css/smoothness/jquery-ui-1.8.4.custom.css"},
	{src: "/js/myflow/lib/raphael.js"},
	{src: "/js/myflow/lib/jquery-ui-1.8.4.custom/js/jquery-ui-1.8.4.custom.min.js"},
	{src: "/js/myflow/myflow.js"},
	{src: "/js/myflow/myflow.jpdl4.js"},
	{src: "/js/myflow/myflow.editors.js"}
]);

function initDesigner() {
	window.editingProc = opener.__editorOpener.editingProcess || {};
	eval("var d = " + window.editingProc.description + ";");
	$("#designer").myflow({
		basePath: CONTEXT_ROOT + "/js/myflow/",
		restore: d,
		tools: {
			save: {
				onclick: function(data) {
					opener.__editorOpener.saveProcess(window.editingProc, data, function(msg) {
						if (typeof msg == "string")
							alert(msg);
					});
				}
			}
		}
	});
}

$(function() {
	initDesigner();
});
