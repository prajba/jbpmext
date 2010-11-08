var showAlert = top.showAlert, showConfirm = top.showConfirm;

function getSelectedValidator() {
	return $("#vallist").datagrid("getSelected");
}

function saveValidator(val) {
	var isNew = val.id === undefined;
	$.post(CONTEXT_ROOT + "/validation/save.action", {
		"validator.name": val.name,
		"validator.scriptSnippet": val.scriptSnippet,
		"validator.remarks": val.remarks
	}, function(nval) {
		var fn = isNew ? appendNewValidator : updateEditedValidator;
		fn(nval);
		top.closeEditorDialog();
	}, "json");
}

//------------Add validator----------
function showAddValidatorDialog() {
	top.showEditorDialog({
		url: CONTEXT_ROOT + "/validation/editor.action",
		width: 400,
		height: 500
	}, window);
}

function appendNewValidator(val) {
	$("#vallist").datagrid("appendRow", val);
}

function addValidator() {
	showAddValidatorDialog();
}
//------------End of add validator--------

//------------Edit validator----------
function showEditValidatorDialog(val) {
	top.showEditorDialog({
		url: CONTEXT_ROOT + "/validation/editor.action",
		data: val,
		width: 400,
		height: 500
	}, window);
}

function updateEditedValidator(val) {
	var ix = $("#vallist").datagrid("getRowIndex", val);
	if (ix >= 0) {
		$("#vallist").datagrid("selectRow", ix);
		$.extend(true, $("#vallist").datagrid("getSelected"), val);
		$("#vallist").datagrid("refreshRow", ix);
	}
}

function editValidator() {
	var val = getSelectedValidator();
	if (!val) {
		showAlert(top.title, valMessages.manager.msgs.noValSelected);
	} else {
		showEditValidatorDialog(val);
	}
}
//---------End of edit validator----------

//-----------Delete validator----------
function doDeleteValidator(val) {
	showAlert(top.title, "Not implemented.");
}

function removeValidatorNode(val) {
	showAlert(top.title, "Not implemented.");
}

function delValidator() {
	var val = getSelectedValidator();
	if (!val) {
		showAlert(top.title, valMessages.manager.msgs.noValSelected);
	} else {
		showConfirm(top.title, valMessages.manager.msgs.confirmValidatorDelete, function(toDel) {
			if (toDel) {
				doDeleteValidator(val);
			}
		});
	}
}
//---------End of delete validator--------

$(function() {
	$("#vallist").datagrid({
		title: valMessages.manager.gridTitle,
		iconCls: "icon-val-man",
		url: CONTEXT_ROOT + "/validation/list.action",
		striped: true,
		sortName: "name",
		sortOrder: "asc",
		idField: "id",
		frozenColumns: [[{
			title: valMessages.manager.columns.name,
			field: "name",
			sortable: true,
			width: 200
		}]],
		columns: [[{
			title: valMessages.manager.columns.paramCount,
			field: "parameters.length",
			width: 100
		}, {
			title: valMessages.manager.columns.remarks,
			field: "remarks",
			width: 300
		}]],
		pagination: true,
		rownumbers: true,
		toolbar: [{
			id: "addValidator",
			text: valMessages.manager.toolbar.addValidator,
			iconCls: "icon-add",
			handler: addValidator
		}, {
			id: "editValidator",
			text: valMessages.manager.toolbar.editValidator,
			iconCls: "icon-edit",
			handler: editValidator
		}, "-", {
			id: "delValidator",
			text: valMessages.manager.toolbar.delValidator,
			iconCls: "icon-remove",
			handler: delValidator
		}]
	});
});
