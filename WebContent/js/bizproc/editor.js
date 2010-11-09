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
	$("#designer").myflow({
		basePath: CONTEXT_ROOT + "/js/myflow/",
		tools: {
			save: {
				onclick: function(data) {
					$.ajax({
						type: "POST",
						url: CONTEXT_ROOT + "/bizproc/save.action",
						data: {
							data: data
						},
						success: function(msg) {
							alert(msg);
						}
					});
				}
			}
		}
	});
}

$(function() {
	initDesigner();
});
