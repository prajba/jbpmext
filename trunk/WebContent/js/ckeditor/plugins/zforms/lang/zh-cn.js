if (!CKEDITOR.zforms) {
	CKEDITOR.zforms = {};
}
if (!CKEDITOR.zforms.lang) {
	CKEDITOR.zforms.lang = {};
}

var lang = CKEDITOR.zforms.lang["zh-cn"] || {};
CKEDITOR.tools.extend(lang, {
	ztextfield: {
		title: "文字",
		type: "类型",
		typeText: "文字",
		typePass: "密码"
	},
	ztextarea: {
		title: "大段文字"
	}
});

CKEDITOR.zforms.lang["zh-cn"] = lang;