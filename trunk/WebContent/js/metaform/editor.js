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

function getDictCatById(id) {
	var cats = loadDictCats();
	for (var i = 0; i < cats.length; i ++) {
		var c = cats[i];
		if (c.id == id) return c;
	}
	return null;
}

function loadDictCats() {
	var cats = window.zselect_dictCats;
	if (!cats) {
		$.ajax({
			async: false,
			dataType: "json",
			url: CONTEXT_ROOT + "/dict/listCats.action",
			success: function(data, st, xhr) {
				cats = data.rows;
			}
		});
		window.zselect_dictCats = cats;
	}
	return cats;
}

function getCols(jqobj) {
	var result = [];
	jqobj.find(":input").each(function() {
		var me = $(this);
		var dispType = me.attr("display_type") || "text";
		var tagName = this.tagName.toLowerCase();
		if (tagName == "select") {
			if (me.attr("multi_sel") == "true") {
				if (me.attr("disp_type") == "list") {
					dispType = "list";
				} else {
					dispType = "check";
				}
			} else {
				if (me.attr("disp_type") == "list") {
					dispType = "dropdown";
				} else {
					dispType = "radio";
				}
			}
		}
		
		var col = {
			//id: me.attr("col_id") || getColId(me.attr("field_name")) || "",
			fieldName: me.attr("field_name"),
			columnName: me.attr("column_name") || generateColumnName(),
			inputHint: me.attr("input_hint"),
			displayType: dispType,
			dataType: me.attr("data_type") || "string",
			dictCategory: me.attr("dict_category"),
			//TODO Resolve multi-line remarks and validators
			remarks: me.attr("remarks"),
			validators: me.attr("validator_xml")
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
	$("#formName").addClass("hidden").parent().append($("#formName").val());
	$("#tableName").addClass("hidden").parent().append($("#tableName").val());
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
	//$.extend(true, window.editingForm, frm);
	opener.__editorOpener.saveForm(frm);
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
	loadDictCats();
	initCKEditor();
	initFormEditor();
});
