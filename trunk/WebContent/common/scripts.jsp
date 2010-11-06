<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.rloader.min.js"></script>
<script type="text/javascript">
var CURRENT_THEME = "${session.user_theme}" || "gray";
var CONTEXT_ROOT = "${ctx}";

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
	{type: "css", src: "/js/easyui/themes/" + CURRENT_THEME + "/easyui.css"},
	{type: "css", src: "/js/easyui/themes/icon.css"}
]);
</script>
<script type="text/javascript" src="${ctx}/js/easyui/jquery.easyui.min.js"></script>
