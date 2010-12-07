window.editorSaveClicked = function() {
	$.extend(true, window.editing, {
		key: $("#key").val(),
		value: $("#value").val()
	});
	opener.__editorOpener.saveEdited(window.editing);
};

function initEditor() {
	$("#key").val(window.editing.key);
	$("#value").val("" + window.editing.value);
	if (window.editing.id !== undefined) {
		//Disable fields that are not editable.
		$("#value").replaceWith($("#value").val());
	}
}

$(function() {
	doEditorLayout("#editor");
	window.editing = opener.__editorOpener.editing || {key: "", value: ""};
	initEditor();
});
