window.editorSaveClicked = function() {
	$.extend(true, window.editing, {
		displayName: $("#displayName").val(),
		valueType: $("#valueType").val(),
		tableName: $("#tableName").val(),
		remarks: $("#remarks").val()
	});
	opener.__editorOpener.saveEdited(window.editing);
};

function initEditor() {
	$("#displayName").val(window.editing.displayName);
	$("#valueType").val(window.editing.valueType);
	$("#tableName").val(window.editing.tableName);
	$("#remarks").val(window.editing.remarks);
	if (window.editing.id !== undefined) {
		//Disable fields that are not editable.
		$("#valueType").replaceWith("" + function(sel) {
			return sel.options[sel.selectedIndex].text;
		} ($("#valueType").get(0)));
		$("#tableName").replaceWith("" + $("#tableName").val());
	}
}

$(function() {
	doEditorLayout("#editor");
	window.editing = opener.__editorOpener.editing || {};
	initEditor();
});
