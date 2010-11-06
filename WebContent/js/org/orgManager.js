function addChildOrg() {
	alert("Not implemented!");
}

function editOrg() {
	alert("Not implemented!");
}

function delOrg() {
	alert("Not implemented!");
}

function stopOrg() {
	alert("Not implemented!");
}

function moveUp() {
	alert("Not implemented!");
}

function moveDown() {
	alert("Not implemented!");
}

$(function() {
	$("#orgList").treegrid({
		title: orgMessages.manager.gridTitle,
		iconCls: "icon-org-man",
		nowrap: false,
		rownumbers: true,
		animate: true,
		collapsible: true,
		url: CONTEXT_ROOT + "/org/orgList.action",
		idField: "id",
		treeField: "name",
		frozenColumns: [[{title: orgMessages.manager.columns.name, field: "name", width: 240}]],
		columns: [[{
			title: orgMessages.manager.columns.code, field: "code", width: 160
		}, {
			title: orgMessages.manager.columns.type, field: "type", width: 40
		}, {
			title: orgMessages.manager.columns.remarks, field: "remarks", width: 240
		}]],
		toolbar: [{
			id: "btnAddChildOrg",
			text: orgMessages.manager.toolbar.addChild,
			iconCls: "icon-add",
			handler: addChildOrg
		}, {
			id: "btnEditOrg",
			text: orgMessages.manager.toolbar.editOrg,
			iconCls: "icon-edit",
			handler: editOrg
		}, {
			id: "btnDelOrg",
			text: orgMessages.manager.toolbar.delOrg,
			iconCls: "icon-remove",
			handler: delOrg
		}, {
			id: "btnStopOrg",
			text: orgMessages.manager.toolbar.stopOrg,
			iconCls: "icon-cancel",
			handler: stopOrg
		}, {
			id: "btnMoveUp",
			text: orgMessages.manager.toolbar.moveUp,
			iconCls: "icon-up",
			handler: moveUp
		}, {
			id: "btnMoveDown",
			text: orgMessages.manager.toolbar.moveDown,
			iconCls: "icon-down",
			handler: moveDown
		}]
	});
});