﻿CKEDITOR.dialog.add( 'ztextarea', function( editor ) {
	return {
		title: editor.lang.textarea.title,
		minWidth: 350,
		minHeight: 150,
		onShow: function() {
			delete this.textarea;

			var element = this.getParentEditor().getSelection().getSelectedElement();
			if (element && element.getName() == "textarea") {
				this.textarea = element;
				this.setupContent(element);
			}
		},
		onOk: function() {
			var editor,
				element = this.textarea,
				isInsertMode = !element;

			if (isInsertMode) {
				editor = this.getParentEditor();
				element = editor.document.createElement('textarea');
				element.setAttribute("style", "display:block;");
			}
			this.commitContent( element );

			if (isInsertMode)
				editor.insertElement(element);
		},
		contents: [
		    CKEDITOR.zforms.mergeTab(CKEDITOR.zforms.settingTab, []),
		    CKEDITOR.zforms.mergeTab(CKEDITOR.zforms.validatorTab, [])
		]
	};
});