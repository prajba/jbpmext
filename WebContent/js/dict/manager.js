var showAlert = top.showAlert, showConfirm = top.showConfirm;
var __GRID_ID = "#catlist";
var msgs = dictMessages.catManager.msgs;

function getSelected() {
	return $(__GRID_ID).datagrid("getSelected");
}

function saveEdited(edited) {
	var isNew = edited.id === undefined;
	$.post(CONTEXT_ROOT + "/dict/saveCategory.action", {
		"cat.id": edited.id || "",
		"cat.displayName": edited.displayName,
		"cat.valueType": edited.valueType,
		"cat.tableName": edited.tableName,
		"cat.remarks": edited.remarks
	}, function(n) {
		var fn = isNew ? appendAdded : updateEdited;
		fn(n);
		top.closeEditorDialog();
	}, "json");
}

function manHandler() {
	var cat = getSelected();
	if (!cat) {
		showAlert(top.title, msgs.noneSelected);
	} else {
		$("<form method='post' action='" + CONTEXT_ROOT + "/dict/catManager.action'>"
				+ "<input name='cat.id' value='" + cat.id
				+ "'/><input name='cat.displayName' value='" + cat.displayName
				+ "'/><input name='cat.valueType' value='" + cat.valueType
				+ "'/></form>").submit();
	}
}

//------------Add------------
function showAddDialog() {
	top.showEditorDialog({
		url: CONTEXT_ROOT + "/dict/catEditor.action",
		width: 400,
		height: 500
	}, window);
}

function appendAdded(added) {
	$(__GRID_ID).datagrid("appendRow", added);
}

function addHandler() {
	window.editing = null;
	showAddDialog();
}
//--------End of add---------

//----------Edit-------------
function showEditDialog(editing) {
	top.showEditorDialog({
		url: CONTEXT_ROOT + "/dict/catEditor.action",
		data: editing,
		width: 400,
		height: 500
	}, window);
}

function updateEdited(edited) {
	var ix = $(__GRID_ID).datagrid("getRowIndex", edited.id);
	if (ix >= 0) {
		$(__GRID_ID).datagrid("selectRow", ix);
		$.extend(true, $(__GRID_ID).datagrid("getSelected"), edited);
		$(__GRID_ID).datagrid("refreshRow", ix);
	}
}

function editHandler() {
	var e = getSelected();
	if (e) {
		window.editing = e;
		showEditDialog();
	} else {
		showAlert(top.title, msgs.noneSelected);
	}
}
//-------End of edit---------

function delHandler() {
	showAlert(top.title, "Not implemented.");
}

$(function() {
	$(__GRID_ID).datagrid({
		title: dictMessages.catManager.gridTitle,
		iconCls: "icon-cat-man",
		url: CONTEXT_ROOT + "/dict/listCats.action",
		striped: true,
		singleSelect: true,
		sortName: "name",
		sortOrder: "asc",
		idField: "id",
		frozenColumns: [[{
			title: dictMessages.catManager.columns.name,
			field: "displayName",
			sortable: true,
			width: 200
		}]],
		columns: [[{
			title: dictMessages.catManager.columns.valueType,
			field: "valueType",
			width: 100
		}, {
			title: dictMessages.catManager.columns.remarks,
			field: "remarks",
			width: 300
		}]],
		rownumbers: true,
		toolbar: [{
			id: "addCat",
			text: dictMessages.catManager.toolbar.addCategory,
			iconCls: "icon-add",
			handler: addHandler
		}, {
			id: "editCategory",
			text: dictMessages.catManager.toolbar.editCategory,
			iconCls: "icon-edit",
			handler: editHandler
		}, "-", {
			id: "delCategory",
			text: dictMessages.catManager.toolbar.delCategory,
			iconCls: "icon-remove",
			handler: delHandler
		}, "-", {
			id: "manCategory",
			text: dictMessages.catManager.toolbar.manCategory,
			iconCls: "icon-dict",
			handler: manHandler
		}]
	});
});
