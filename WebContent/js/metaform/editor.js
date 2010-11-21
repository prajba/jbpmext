function formToObject() {
	var frm = {};
	frm.formName = $("#formName").val();
	frm.tableName = $("#tableName").val();
	frm.remarks = $("#remarks").val();
	frm.formHtml = $("#formHtml").val();
	return frm;
}

function objectToForm(frm) {
	$("#formName").val(frm.formName);
	$("#tableName").val(frm.tableName);
	$("#remarks").val(frm.remarks);
	$("#formHtml").val(frm.formHtml);
}

function initFormEditor() {
	window.editingForm = opener.__editorOpener.editingForm || {};
	objectToForm(window.editingForm);
}

window.editorSaveClicked = function() {
	var frm = formToObject();
	$.extend(true, window.editingForm, frm);
	opener.__editorOpener.saveForm(window.editingForm);
};

function initCKEditor() {
	$("#formHtml").ckeditor({
		resize_dir: "vertical",
		height: 600,
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
			['zTextField', 'zTextarea']
		],

		fontSize_sizes: '10/10px;12/12px;14/14px;16/16px;18/18px;20/20px;22/22px;24/24px;28/28px;32/32px;48/48px;',
		extraPlugins: "zforms"
	});
}

$(function() {
	doEditorLayout("#editor");
	//$(".inner-layout").addClass("filled").layout();
	initCKEditor();
	initFormEditor();
});
