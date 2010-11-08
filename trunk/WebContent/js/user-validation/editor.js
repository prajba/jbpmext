window.editorSaveClicked = function() {
	$.extend(true, window.editingValidator, {
		name: $("#valname").val(),
		remarks: $("#valremarks").val(),
		scriptSnippet: $("#valsnippet").val()
	});
	opener.__editorOpener.saveValidator(window.editingValidator);
};

$(function() {
	doEditorLayout("#editor");
	window.editingValidator = opener.__editorOpener.editingValidator || {};
});
