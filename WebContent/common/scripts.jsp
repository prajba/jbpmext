<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.rloader.min.js"></script>
<script type="text/javascript">
var CURRENT_THEME = "${session.user_theme}" || "gray";
var CONTEXT_ROOT = "${ctx}";
/*
var DHTML_SUITE_THEME_FOLDER = '${ctx}/js/dhtml-suite/themes/';
var DHTML_SUITE_JS_FOLDER = '${ctx}/js/';
var DHTML_SUITE_THEME = CURRENT_THEME;
*/
function INCLUDE(files) {
	function setDefaults(f) {
		if (f.type != "css") f.type = "js";
		if (f.src.charAt(0) == "/") {
			f.src = CONTEXT_ROOT + f.src;
		}
	}
	if (files.src) {
		setDefaults(files);
	} else if (files.length) {
		for (var i = 0; i < files.length; i ++) {
			setDefaults(files[i]);
		}
	}
	$.rloader(files);
}
INCLUDE([
	{type: "css", src: "/js/easyui/themes/" + CURRENT_THEME + "/easyui.css"}
//{src:"/js/jquery-tools/jquery.tools.min.js"},
//         {src:"/js/dhtml-suite/dhtml-suite.js"}
]);
</script>
<script type="text/javascript" src="${ctx}/js/easyui/jquery.easyui.min.js"></script>
