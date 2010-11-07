var showAlert = top.showAlert, showConfirm = top.showConfirm;

function getSelectedValidator() {
	return $("#vallist").datagrid("getSelected");
}

//------------Add validator----------
function showAddValidatorDialog() {
	showAlert(top.title, "Not implemented.");
	return null;
}

function appendNewValidator(val) {
	showAlert(top.title, "Not implemented.");
}

function addValidator() {
	var val = showAddValidatorDialog();
	if (val) {
		appendNewValidator(val);
	}
}
//------------End of add validator--------

//------------Edit validator----------
function showEditValidatorDialog(val) {
	showAlert(top.title, "Not implemented.");
}

function updateEditedValidator(val) {
	showAlert(top.title, "Not implemented.");
}
function editValidator() {
	var val = getSelectedValidator();
	if (!val) {
		showAlert(top.title, valMessages.manager.msgs.noValSelected);
	} else {
		if (showEditValidatorDialog(val)) {
			updateEditedValidator(val);
		}
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
		striped: true,
		sortName: "name",
		sortOrder: "asc",
		idField: "id",
		frozenColumns: [[{
			title: valMessages.manager.columns.name,
			width: 200
		}]],
		columns: [[{
			title: valMessages.manager.columns.paramCount,
			width: 100
		}, {
			title: valMessages.manager.columns.remarks,
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
