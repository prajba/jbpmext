var showAlert = top.showAlert, showConfirm = top.showConfirm;

function getSelectedForm() {
	return $("#formlist").datagrid("getSelected");
}

function colsToPost(frm) {
	var result = {};
	for (var i = 0; i < frm.cols.length; i ++) {
		var c = frm.cols[i];
		for (var k in c) {
			if (c.hasOwnProperty(k))
				result["form.fields[" + i + "]." + k] = c[k] || "";
		}
	}
	return result;
}

function saveForm(frm) {
	var isNew = !window.editingForm;
	var pdata = $.extend(true, {
		"form.formName": frm.formName,
		"form.tableName": frm.tableName,
		"form.formHtml": frm.formHtml,
		"form.beginTime": frm.beginTime,
		"form.endTime": frm.endTime,
		"form.usableStatus": frm.usableStatus,
		"form.sysPreset": frm.sysPreset,
		"form.version": frm.version,
		"form.remarks": frm.remarks
	}, colsToPost(frm));
	$.post(CONTEXT_ROOT + "/metaform/save.action", pdata, function(nfrm) {
		var fn = isNew ? appendNewForm : updateEditedForm;
		fn(nfrm);
		top.closeEditorDialog();
	}, "json");
}

//----------Add form---------
function showAddFormDialog() {
	window.editingForm = {};
	top.showEditorDialog({
		url: CONTEXT_ROOT + "/metaform/editor.action",
		name: "metaFormEditor"
	}, window);
}

function appendNewForm(form) {
	$("#formlist").datagrid("appendRow", form);
}

function addForm() {
	showAddFormDialog();
}
//-------End of add form-----

function showEditFormDialog(form) {
	window.editingForm = form;
	top.showEditorDialog({
		url: CONTEXT_ROOT + "/metaform/editor.action",
		data: form,
		name: "metaFormEditor"
	}, window);
}

function updateEditedForm(form) {
	var ix = $("#formlist").datagrid("getRowIndex", form.formName);
	if (ix >= 0) {
		$("#formlist").datagrid("selectRow", ix);
		$.extend(true, $("#formlist").datagrid("getSelected"), form);
		$("#formlist").datagrid("refreshRow", ix);
	}
}

function editForm() {
	var form = getSelectedForm();
	if (!form) {
		showAlert(top.title, formMessages.manager.msgs.noFormSelected);
	} else {
		showEditFormDialog(form);
	}
}

$(function() {
	$("#formlist").datagrid({
		title: formMessages.manager.gridTitle,
		iconCls: "icon-form",
		url: CONTEXT_ROOT + "/metaform/list.action",
		striped: true,
		singleSelect: true,
		rownumbers: true,
		sortName: "formName",
		sortOrder: "asc",
		idField: "formName",
		frozenColumns: [[{
			title: formMessages.manager.columns.formName,
			field: "formName",
			sortable: true,
			width: 200
		}]],
		columns: [[{
			title: formMessages.manager.columns.version,
			field: "version",
			width: 70
		}, {
			title: formMessages.manager.columns.beginTime,
			field: "beginTime",
			width: 100
		}, {
			title: formMessages.manager.columns.remarks,
			field: "remarks",
			width: 320
		}]],
		toolbar: [{
			id: "addForm",
			text: formMessages.manager.toolbar.addForm,
			iconCls: "icon-add",
			handler: addForm
		}, {
			id: "editForm",
			text: formMessages.manager.toolbar.editForm,
			iconCls: "icon-edit",
			handler: editForm
		}]
	});
});
