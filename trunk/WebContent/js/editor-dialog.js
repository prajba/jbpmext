window.editorSaveClicked = function() {
};

window.editorCancelClicked = function() {
	close();
};

function doEditorLayout() {
	$("body").wrapInner("<div id='edt-dlg-center' region='center' style='border:none;'></div>")
	.append("<div id='edt-dlg-south' region='south' style='border:none; text-align: right; height: 40px; margin: 5px;'>"
		+ "<a id='edt-dlg-save' href='javascript:void(0);' iconCls='icon-save' onclick='editorSaveClicked()'>" + edtorMessages.buttons.save + "</a>"
		+ "<a id='edt-dlg-cancel' href='javascript:void(0);' iconCls='icon-cancel' onclick='editorCancelClicked()'>" + edtorMessages.buttons.cancel + "</a>"
		+ "</div>").layout();
	$("#edt-dlg-save").linkbutton();
	$("#edt-dlg-cancel").linkbutton();
}
