var showAlert = top.showAlert, showConfirm = top.showConfirm;

function getSelectedProcess() {
	return $("#proclist").datagrid("getSelected");
}

function saveProcess(proc, data, callback) {
	var isNew = !window.editingProcess;
	$.ajax({
		type: "POST",
		url: CONTEXT_ROOT + "/bizproc/save.action",
		data: {
			id: proc.id,
			data: data
		},
		success: function(nproc) {
			if (typeof nproc != "string") {
				var fn = isNew ? appendNewProc : updateEditedProc;
				fn(nproc);
				top.closeEditorDialog();
			}
			callback(nproc);
		}
	});
}

//-----------Add proc-----------
function showAddProcDialog() {
	top.showEditorDialog({
		url: CONTEXT_ROOT + "/bizproc/editor.action",
		name: "bizProcEditor"
	}, window);
}

function appendNewProc(proc) {
	$("#proclist").datagrid("appendRow", proc);
}

function addProc() {
	showAddProcDialog();
}
//---------End of add proc--------

//-----------Edit proc---------
function showEditProcessDialog(proc) {
	window.editingProcess = proc;
	top.showEditorDialog({
		url: CONTEXT_ROOT + "/bizproc/editor.action",
		data: proc,
		name: "bizProcEditor"
	}, window);
}

function updateEditedProc(proc) {
	var ix = $("#proclist").datagrid("getRowIndex", proc.id);
	if (ix >= 0) {
		$("#proclist").datagrid("selectRow", ix);
		$.extend(true, $("#proclist").datagrid("getSelected"), proc);
		$("#proclist").datagrid("refreshRow", ix);
	}
}

function editProc() {
	var proc = getSelectedProcess();
	if (!proc) {
		showAlert(top.title, procMessages.manager.msgs.noProcSelected);
	} else {
		showEditProcessDialog(proc);
	}
}
//---------End of edit proc---------

function stopProc() {
	showAlert(top.title, "Not implemented.");
}

$(function() {
	$("#proclist").datagrid({
		title: procMessages.manager.gridTitle,
		iconCls: "icon-proc-man",
		url: CONTEXT_ROOT + "/bizproc/list.action",
		striped: true,
		singleSelect: true,
		rownumbers: true,
		sortName: "name",
		sortOrder: "asc",
		idField: "id",
		frozenColumns: [[{
			title: procMessages.manager.columns.name,
			field: "name",
			sortable: true,
			width: 200
		}]],
		columns: [[{
			title: procMessages.manager.columns.version,
			field: "version",
			width: 70
		}, {
			title: procMessages.manager.columns.deploymentId,
			field: "deploymentId",
			width: 100
		}, {
			title: procMessages.manager.columns.suspended,
			field: "suspended",
			width: 90
		}, {
			title: procMessages.manager.columns.description,
			field: "description",
			width: 320
		}]],
		toolbar: [{
			id: "addProc",
			text: procMessages.manager.toolbar.addProc,
			iconCls: "icon-add",
			handler: addProc
		}, {
			id: "editProc",
			text: procMessages.manager.toolbar.editProc,
			iconCls: "icon-edit",
			handler: editProc
		}, "-", {
			id: "stopProc",
			text: procMessages.manager.toolbar.stopProc,
			iconCls: "icon-cancel",
			handler: stopProc
		}]
	});
});
