CKEDITOR.dialog.add('ztextfield', function(editor) {
	var autoAttributes = {
		value: 1,
		size: 1,
		maxLength: 1
	};

	var acceptedTypes = {
		text: 1,
		password: 1
	};
	var lang = editor.lang.zforms;

	return {
		title: lang.ztextfield.title,
		minWidth: 350,
		minHeight: 150,
		onShow: function() {
			delete this.textField;

			var element = this.getParentEditor().getSelection().getSelectedElement();
			if (element && element.getName() == "input" &&
					(acceptedTypes[element.getAttribute('type')] || !element.getAttribute('type'))
				) {
				this.textField = element;
				this.setupContent(element);
			}
		},
		onOk: function() {
			var editor,
				element = this.textField,
				isInsertMode = !element;

			if (isInsertMode) {
				editor = this.getParentEditor();
				element = editor.document.createElement('input');
				element.setAttribute('type', 'text');
				element.setAttribute('display_type', 'text');
			}

			if (isInsertMode)
				editor.insertElement(element);
			this.commitContent({
				element: element
			});
		},
		onLoad: function() {
			var autoSetup = function(element) {
				var value = element.hasAttribute(this.id)
					&& element.getAttribute(this.id);
				this.setValue(value || '');
			};

			var autoCommit = function(data) {
				var element = data.element;
				var value = this.getValue();

				if (value) {
					element.setAttribute(this.id, value);
				} else {
					element.removeAttribute(this.id);
				}
			};

			this.foreach(function(contentObj) {
				if (autoAttributes[contentObj.id]) {
					contentObj.setup = autoSetup;
					contentObj.commit = autoCommit;
				}
			});
		},
		contents: [
		    CKEDITOR.zforms.mergeTab(CKEDITOR.zforms.settingTab, [{
		    	type: "hbox",
		    	widths: ["50%", "50%"],
		    	children: [{
					id: 'type',
					type: 'select',
					label: lang.ztextfield.type,
					'default': 'text',
					items: [
						[lang.ztextfield.typeText, 'text'],
						[lang.ztextfield.typePass, 'password']
					],
					setup: function(element) {
						this.setValue(element.getAttribute('type'));
					},
					commit: function(data) {
						var element = data.element;
	
						if (CKEDITOR.env.ie) {
							//如果是IE，必须重新写入标签
							var elementType = element.getAttribute( 'type' );
							var myType = this.getValue();
	
							if (elementType != myType) {
								var replace = CKEDITOR.dom.element.createFromHtml(
										'<input type="' + myType + '"></input>',
										editor.document);
								element.copyAttributes(replace, {
									type: 1
								});
								replace.replace(element);
								editor.getSelection().selectElement(replace);
								data.element = replace;
							}
						} else {
							//如果不是IE，可以直接更改input的type值
							element.setAttribute('type', this.getValue());
						}
					}
				}, {
					id: "dataType",
					type: "select",
					label: lang.ztextfield.dataType,
					"default": "text",
					items: [
						[lang.ztextfield.typeText, "text"],
						[lang.ztextfield.typeInt, "int"],
						[lang.ztextfield.typeFloat, "float"]
					],
					setup: function(element) {
						this.setValue(element.getAttribute("data_type"));
					},
					commit: function(data) {
						data.element.setAttribute("data_type", this.getValue());
					}
				}]
		    }]),
			CKEDITOR.zforms.mergeTab(CKEDITOR.zforms.validatorTab, [])
		]
	};
});