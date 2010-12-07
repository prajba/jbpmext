function generateColumnName() {
	return "col_" + Math.floor(Math.random() * 100000000);
}

function getColId(fldName) {
	if (!window.editingForm.cols) return null;
	for (var i = 0; i < window.editingForms.cols.length; i ++) {
		var c = window.editingForms.cols[i];
		if (c["field_name"] === fldName)
			return c["id"];
	}
	return "";
}

function getCols(jqobj) {
	var result = [];
	jqobj.find(":input").each(function() {
		var me = $(this);
		var col = {
			id: me.attr("col_id") || getColId(me.attr("field_name")) || "",
			fieldName: me.attr("field_name"),
			columnName: me.attr("column_name") || generateColumnName(),
			inputHint: me.attr("input_hint"),
			displayType: me.attr("display_type") || "text",
			dataType: me.attr("data_type") || "text",
			dictCategory: me.attr("dict_category"),
			//TODO Resolve multi-line remarks and validators
			remarks: me.attr("remarks"),
			validatorXml: me.attr("validator_xml")
		};
		result.push(col);
	});
	return result;
}

function formToObject() {
	var frm = {};
	frm.formName = $("#formName").val();
	frm.tableName = $("#tableName").val();
	frm.remarks = $("#remarks").val();
	frm.formHtml = $("#formHtml").val();
	frm.cols = getCols($(frm.formHtml));
	return frm;
}

function objectToForm(frm) {
	$("#formName").val(frm.formName);
	$("#tableName").val(frm.tableName);
	$("#remarks").val(frm.remarks);
	$("#formHtml").val(frm.formHtml);
}

function disableNoneditables() {
	$("#formName").replaceWith($("#formName").val());
	$("#tableName").replaceWith($("#tableName").val());
}

function initFormEditor() {
	window.editingForm = opener.__editorOpener.editingForm || {};
	objectToForm(window.editingForm);
	var id = window.editingForm.id;
	if (id !== undefined) {
		disableNoneditables();
	}
}

window.editorSaveClicked = function() {
	var frm = formToObject();
	$.extend(true, window.editingForm, frm);
	opener.__editorOpener.saveForm(window.editingForm);
};

function initCKEditor() {
	$("#formHtml").ckeditor({
		resize_dir: "vertical",
		height: 300,
		uiColor: '#ddd',

		toolbar: 'TB',
		toolbar_TB: [
			['Source'],
			['Cut','Copy','Paste','PasteText','PasteFromWord'],
			['Undo','Redo'],
			['Link','Unlink','Anchor'],
			['Image','Table','HorizontalRule'],
			['Bold','Italic','Underline'],
			['FontSize'],
			['TextColor','BGColor'],
			['NumberedList','BulletedList'],
			['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
			['zTextField', 'zSelect', 'zTextarea']
		],

		fontSize_sizes: '10/10px;12/12px;14/14px;16/16px;18/18px;20/20px;22/22px;24/24px;28/28px;32/32px;48/48px;',
		extraPlugins: "zforms"
	});
}

$(function() {
	doEditorLayout("#editor");
	initCKEditor();
	initFormEditor();
});
