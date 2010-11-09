window.editorSaveClicked = function() {
	$.extend(true, window.editingValidator, {
		name: $("#valname").val(),
		remarks: $("#valremarks").val(),
		scriptSnippet: $("#valsnippet").val()
	});
	opener.__editorOpener.saveValidator(window.editingValidator);
};

function initEditor() {
	$("#valname").val(window.editingValidator.name);
	$("#valremarks").val(window.editingValidator.remarks);
	$("#valsnippet").val(window.editingValidator.scriptSnippet);
}

$(function() {
	doEditorLayout("#editor");
	window.editingValidator = opener.__editorOpener.editingValidator || {};
	initEditor();
});
