CKEDITOR.dialog.add( 'zselect', function( editor )
{
	// Add a new option to a SELECT object (combo or list).
	function addOption( combo, optionText, optionValue, documentObject, index )
	{
		combo = getSelect( combo );
		var oOption;
		if ( documentObject )
			oOption = documentObject.createElement( "OPTION" );
		else
			oOption = document.createElement( "OPTION" );

		if ( combo && oOption && oOption.getName() == 'option' )
		{
			if ( CKEDITOR.env.ie ) {
				if ( !isNaN( parseInt( index, 10) ) )
					combo.$.options.add( oOption.$, index );
				else
					combo.$.options.add( oOption.$ );

				oOption.$.innerHTML = optionText.length > 0 ? optionText : '';
				oOption.$.value     = optionValue;
			}
			else
			{
				if ( index !== null && index < combo.getChildCount() )
					combo.getChild( index < 0 ? 0 : index ).insertBeforeMe( oOption );
				else
					combo.append( oOption );

				oOption.setText( optionText.length > 0 ? optionText : '' );
				oOption.setValue( optionValue );
			}
		}
		else
			return false;

		return oOption;
	}
	// Remove all selected options from a SELECT object.
	function removeSelectedOptions( combo )
	{
		combo = getSelect( combo );

		// Save the selected index
		var iSelectedIndex = getSelectedIndex( combo );

		// Remove all selected options.
		for ( var i = combo.getChildren().count() - 1 ; i >= 0 ; i-- )
		{
			if ( combo.getChild( i ).$.selected )
				combo.getChild( i ).remove();
		}

		// Reset the selection based on the original selected index.
		setSelectedIndex( combo, iSelectedIndex );
	}
	//Modify option  from a SELECT object.
	function modifyOption( combo, index, title, value )
	{
		combo = getSelect( combo );
		if ( index < 0 )
			return false;
		var child = combo.getChild( index );
		child.setText( title );
		child.setValue( value );
		return child;
	}
	function removeAllOptions( combo )
	{
		combo = getSelect( combo );
		while ( combo.getChild( 0 ) && combo.getChild( 0 ).remove() )
		{ /*jsl:pass*/ }
	}
	// Moves the selected option by a number of steps (also negative).
	function changeOptionPosition( combo, steps, documentObject )
	{
		combo = getSelect( combo );
		var iActualIndex = getSelectedIndex( combo );
		if ( iActualIndex < 0 )
			return false;

		var iFinalIndex = iActualIndex + steps;
		iFinalIndex = ( iFinalIndex < 0 ) ? 0 : iFinalIndex;
		iFinalIndex = ( iFinalIndex >= combo.getChildCount() ) ? combo.getChildCount() - 1 : iFinalIndex;

		if ( iActualIndex == iFinalIndex )
			return false;

		var oOption = combo.getChild( iActualIndex ),
			sText	= oOption.getText(),
			sValue	= oOption.getValue();

		oOption.remove();

		oOption = addOption( combo, sText, sValue, ( !documentObject ) ? null : documentObject, iFinalIndex );
		setSelectedIndex( combo, iFinalIndex );
		return oOption;
	}
	function getSelectedIndex( combo )
	{
		combo = getSelect( combo );
		return combo ? combo.$.selectedIndex : -1;
	}
	function setSelectedIndex( combo, index )
	{
		combo = getSelect( combo );
		if ( index < 0 )
			return null;
		var count = combo.getChildren().count();
		combo.$.selectedIndex = ( index >= count ) ? ( count - 1 ) : index;
		return combo;
	}
	function getOptions( combo )
	{
		combo = getSelect( combo );
		return combo ? combo.getChildren() : false;
	}
	function getSelect( obj )
	{
		if ( obj && obj.domId && obj.getInputElement().$ )				// Dialog element.
			return  obj.getInputElement();
		else if ( obj && obj.$ )
			return obj;
		return false;
	}
	function bindCatsToCombo(cats, element) {
		//Add an empty option
		addOption(element, "", "", element.getDialog().getParentEditor().document);
		if (!cats || !cats.length) return;
		for (var i = 0; i < cats.length; i ++) {
			var c = cats[i];
			addOption(element, c.displayName, c.id, element.getDialog().getParentEditor().document);
		}
	}

	var lang = editor.lang.zforms;
	return {
		title : lang.zselect.title,
		minWidth : CKEDITOR.env.ie ? 460 : 395,
		minHeight : CKEDITOR.env.ie ? 320 : 300,
		onShow : function()
		{
			delete this.selectBox;
			this.setupContent( 'clear' );
			var element = this.getParentEditor().getSelection().getSelectedElement();
			if ( element && element.getName() == "select" )
			{
				this.selectBox = element;
				this.setupContent( element.getName(), element );

				// Load Options into dialog.
				var objOptions = getOptions( element );
				for ( var i = 0 ; i < objOptions.count() ; i++ )
					this.setupContent( 'option', objOptions.getItem( i ) );
			}
		},
		onOk : function()
		{
			var editor = this.getParentEditor(),
				element = this.selectBox,
				isInsertMode = !element;

			if ( isInsertMode )
				element = editor.document.createElement( 'select' );
			this.commitContent( element );

			if ( isInsertMode )
			{
				editor.insertElement(element);
				if ( CKEDITOR.env.ie )
				{
					var sel = editor.getSelection(),
						bms = sel.createBookmarks();
					setTimeout(function ()
					{
						sel.selectBookmarks( bms );
					}, 0 );
				}
			}
		},
		contents : [CKEDITOR.zforms.mergeTab(CKEDITOR.zforms.settingTab, [{
			type: "hbox",
			widths: ["75%", "25%"],
			children: [{
				id: "dictSelect",
				type: "select",
				label: lang.zselect.dict,
				items: [],
				setup: function(name, element) {
					if (name == "clear") {
						removeAllOptions(this);
						var sel = getSelect(this);
						var cats = loadDictCats();
						if (cats) {
							bindCatsToCombo(cats, this);
						}
					} else if (name == "option") {
						this.setValue(element.getAttribute("dict_category"));
					}
				},
				commit: function(data) {
					var element = data.element || data;
					element.setAttribute("dict_category", this.getValue());
					var c = getDictCatById(this.getValue());
					element.setAttribute("data_type", c.valueType);
				}
			}, {
				id: "newDict",
				type: "button",
				label: lang.zselect.newDict,
				title: lang.zselect.newDict,
				onClick: function() {
					jAlert(top.title, "Not implemented.");
					//TODO Create new dictionary category
				}
			}]
		}, {
			type: "hbox",
			widths: ["75%", "25%"],
			children: [{
				id: "displayType",
				type: "select",
				label: lang.zselect.displayType,
				items: [
					[lang.zselect.dispTypeList, "list"],
					[lang.zselect.dispTypeRow, "row"]
				],
				setup: function(name, element) {
					if (name == "clear") {
						
					} else if (name == "option") {
						this.setValue(element.getAttribute("disp_type"));
					}
				},
				commit: function(data) {
					var element = data.element || data;
					element.setAttribute("disp_type", this.getValue());
				}
			}, {
				id: "multiSel",
				type: "checkbox",
				label: lang.zselect.multiSel,
				setup: function(name, element) {
					if (name == "clear") {
						
					} else if (name == "select") {
						this.setValue(element.getAttribute("multi_sel"));
					}
				},
				commit: function(data) {
					var element = data.element || data;
					element.setAttribute("multi_sel", this.getValue());
				}
			}]
		}]),
			CKEDITOR.zforms.mergeTab(CKEDITOR.zforms.validatorTab, [])
		]
	};
});
