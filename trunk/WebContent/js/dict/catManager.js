var showAlert = top.showAlert, showConfirm = top.showConfirm;
var __GRID_ID = "#entrylist";
var msgs = dictMessages.entryManager.msgs;
var catName, catId, valueType;

function backHandler() {
	location.href = CONTEXT_ROOT + "/dict/manager.action";
}

function getSelected() {
	return $(__GRID_ID).datagrid("getSelected");
}

function saveEdited(edited) {
	var isNew = edited.id === undefined;
	$.post(CONTEXT_ROOT + "/dict/saveEntry.action", {
		"cat.id": catId,
		"entry.id": edited.id || "",
		"entry.key": edited.key,
		"entry.intValue": edited.value,
		"entry.stringValue": edited.value,
		"entry.usableStatus": edited.usableStatus || 0
	}, function(n) {
		var fn = isNew ? appendAdded : updateEdited;
		fn(n);
		top.closeEditorDialog();
	}, "json");
}

//------------Add------------
function showAddDialog() {
	top.showEditorDialog({
		url: CONTEXT_ROOT + "/dict/entryEditor.action",
		width: 400,
		height: 160
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
		url: CONTEXT_ROOT + "/dict/entryEditor.action",
		data: editing,
		width: 400,
		height: 160
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
	catName = $("#catName").text();
	catId = $("#catId").text();
	valueType = $("#valType").text();
	$(__GRID_ID).datagrid({
		title: dictMessages.entryManager.gridTitle + ":" + catName,
		iconCls: "icon-entry-man",
		url: CONTEXT_ROOT + "/dict/listEntries.action?cat.id=" + catId,
		striped: true,
		singleSelect: true,
		sortName: "key",
		sortOrder: "asc",
		idField: "id",
		frozenColumns: [[{
			title: dictMessages.entryManager.columns.key,
			field: "key",
			sortable: true,
			width: 200
		}]],
		columns: [[{
			title: dictMessages.entryManager.columns.value,
			field: "value",
			width: 100
		}]],
		rownumbers: true,
		toolbar: [{
			id: "addEntry",
			text: dictMessages.entryManager.toolbar.addEntry,
			iconCls: "icon-add",
			handler: addHandler
		}, {
			id: "editCategory",
			text: dictMessages.entryManager.toolbar.editEntry,
			iconCls: "icon-edit",
			handler: editHandler
		}, "-", {
			id: "delCategory",
			text: dictMessages.entryManager.toolbar.delEntry,
			iconCls: "icon-remove",
			handler: delHandler
		}, "-", {
			id: "backToDict",
			text: dictMessages.entryManager.toolbar.backToDict,
			iconCls: "icon-back",
			handler: backHandler
		}]
	});
});
