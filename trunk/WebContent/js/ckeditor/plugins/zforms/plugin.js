CKEDITOR.zforms = CKEDITOR.zforms || {};

/**
 * 用来将某个表单类型独有的属性合并到属性页中<br/>
 * 例如，将textfield的属性type合并到默认属性页中<br/>
 * 默认属性页是CKEDITOR.zforms.settingTab，包含名称、提示文字两种属性<br/>
 * @param tab tab页模板
 * @param contents 要并入模板的内容，必须是一个element数组
 * @returns 新建的标签页，包含了模板和并入的内容
**/
function __mergeTab(tab, contents) {
	var t = $.extend({elements: []}, tab);
	t.elements = [];
	$.merge(t.elements, tab.elements);
	$.merge(t.elements, contents);
	return t;
};
CKEDITOR.zforms.mergeTab = __mergeTab;

CKEDITOR.plugins.add('zforms', {
	lang: ["zh-cn", "en"],
	
	init : function(editor) {
		var lang = editor.lang.zforms;
		var labels = lang.labels;
	
		var addButtonCommand = function(buttonName, commandName, dialogFile, iconFile) {
			editor.addCommand(commandName, new CKEDITOR.dialogCommand(commandName));
			
			editor.ui.addButton(buttonName, {
				label : labels[buttonName.toLowerCase()],
				command : commandName,
				className: "cke_button_" + commandName.slice(1)
			});
			CKEDITOR.dialog.add(commandName, dialogFile);
		};
	
		var dialogPath = this.path + 'dialogs/';
		addButtonCommand('zTextField', 'ztextfield', 
			dialogPath + 'ztextfield.js');
		addButtonCommand('zTextarea', 'ztextarea',
			dialogPath + 'ztextarea.js');
		
		if (editor.addMenuItems) {
			editor.addMenuItems({
				textarea: {
					label: labels.ztextarea,
					command: "ztextarea",
					group: "textarea"
				}
			});
			editor.addMenuItems({
				textfield: {
					label: labels.ztextfield,
					command: "ztextfield",
					group: "textfield"
				}
			});
		}
		
		editor.on('doubleclick', function(evt) {
			var element = evt.data.element;

			if (element.is('textarea'))
				evt.data.dialog = 'ztextarea';
			else if (element.is('input')) {
				var type = element.getAttribute('type');

				switch (type) 	{
				case 'text' : case 'password':
					evt.data.dialog = 'ztextfield';
					break;
				}
			}
		});

		var textInputDef = function(conf) {
			return {
				id: conf.id,
				type: "text",
				label: conf.label,
				"default": conf["default"] || "",
				setup: function(el) {
					this.setValue(element.getAttribute(conf.id));
				},
				commit: function(d) {
					var el = d.element || d;
					var v;
					if (v = this.getValue()) {
						el.setAttribute(conf.id, v);
					} else {
						el.removeAttribute(conf.id);
					}
				}
			}
		};
		//默认属性页
		CKEDITOR.zforms.settingTab = {
			id: 'settings',
			label: lang.labels.setting,
			title: lang.labels.setting,
			elements: [{
				type: 'hbox',
				widths: ['50%', '50%'],
				children: [
					textInputDef({id: "field_name", label: lang.labels.fieldName}),
					textInputDef({id: "column_name", label: lang.labels.columnName})
			]},
				textInputDef({id: "input_hint", label: lang.labels.hint})
			]
		};

		//验证器页
		CKEDITOR.zforms.validatorTab = {
			id: 'validators',
			label: lang.labels.validator,
			title: lang.labels.validator,
			elements: [{
				type: 'vbox',
				children: [{
					type: 'hbox',
					widths: ['50%', '50%'],
					children: [{
						//预置验证器
						id: 'presetValidators',
						type: 'select',
						multiple: true,
						label: lang.labels.presetValidator,
						items: window.presetValidators || []
					//TODO 编写setup和commit方法
					}, {
						//用户自定义验证器
						id: 'customValidators',
						type: 'select',
						multiple: true,
						label: lang.labels.customValidator,
						items: window.customValidators || []
					//TODO 编写setup和commit方法
					}]
				}, {
					//在这里单独编写的验证器
					id: 'script',
					type: 'textarea',
					label: lang.labels.validatorScript,
					'default': '',
					setup: function(element) {
						//TODO 显示脚本
					},
					commit: function(data) {
						var val = this.getValue();
						var el = data.element || data;
						//TODO 记录脚本
						if (val) {
							el.setAttribute("validator", val);
						} else {
							el.removeAttribute("validator");
						}
					}
				}]
			}]
		};

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