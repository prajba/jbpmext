if (!CKEDITOR.zforms) {
	CKEDITOR.zforms = {};
}
if (!CKEDITOR.zforms.lang) {
	CKEDITOR.zforms.lang = {};
}

var lang = CKEDITOR.zforms.lang["en"] || {};
CKEDITOR.tools.extend(lang, {
	ztextfield: {
		title: "Text",
		type: "Type",
		typeText: "Text",
		typePass: "Password"
	},
	ztextarea: {
		title: "Rich text"
	}
});

CKEDITOR.zforms.lang["zh-cn"] = lang;