CKEDITOR.plugins.add('zforms', {
	availableLangCodes: {"zh-cn": "zh-cn", "en": "en"},
	
	loadLang: function(editor) {
		var langCode = editor.langCode;
		langCode = this.availableLangCodes[langCode] ? langCode : 'en';

		CKEDITOR.scriptLoader.load(
			CKEDITOR.getUrl( this.path + 'lang/' + langCode + '.js' ),
			function() {
				CKEDITOR.tools.extend( editor.lang, this.lang[langCode] );
			}
		);
	},
	
	init : function(editor) {
		this.loadLang(editor);
		
		var lang = editor.lang;
	
		var addButtonCommand = function(buttonName, commandName, dialogFile) {
			editor.addCommand(commandName, new CKEDITOR.dialogCommand(commandName));
			editor.ui.addButton(buttonName, {
				label : lang.common[buttonName.toLowerCase()],
				command : commandName
			});
			CKEDITOR.dialog.add(commandName, dialogFile);
		};
	
		var dialogPath = this.path + 'dialogs/';
		addButtonCommand('zTextField', 'ztextfield', 
			dialogPath + 'ztextfield.js');
		addButtonCommand('zTextarea', 'ztextarea',
			dialogPath + 'ztextarea.js');
	},

	afterInit : function(editor) {
		var dataProcessor = editor.dataProcessor,
			htmlFilter = dataProcessor && dataProcessor.htmlFilter,
			dataFilter = dataProcessor && dataProcessor.dataFilter;

		if (CKEDITOR.env.ie) {
			htmlFilter && htmlFilter.addRules({
				elements : {
					input : function(input) {
						var attrs = input.attributes, type = attrs.type;
						if (type == 'checkbox' || type == 'radio')
								attrs.value == 'on' && delete attrs.value;
					}
				}
			});
		}

		if (dataFilter) {
			dataFilter.addRules({
				elements : {
					input : function(element) {
						if (element.attributes.type == 'hidden')
							return editor.createFakeParserElement(
									element,
									'cke_hidden',
									'hiddenfield');
					}
				}
			});
		}
	},
	requires : [ 'image', 'fakeobjects' ]
});

if (CKEDITOR.env.ie) {
	CKEDITOR.dom.element.prototype.hasAttribute = function(name) {
		var $attr = this.$.attributes.getNamedItem(name);

		if (this.getName() == 'input') {
			switch (name) {
			case 'class':
				return this.$.className.length > 0;
			case 'checked':
				return !!this.$.checked;
			case 'value':
				var type = this.getAttribute('type');
				if (type == 'checkbox' || type == 'radio')
					return this.$.value != 'on';
				break;
			default:
			}
		}

		return !!($attr && $attr.specified);
	};
}
