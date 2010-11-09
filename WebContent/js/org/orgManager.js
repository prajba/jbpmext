var showAlert = top.showAlert, showConfirm = top.showConfirm;

function getSelectedOrg() {
	return $("#orgList").treegrid("getSelected");
}

//----------Add org----------
function showAddOrgDialog(porg) {
	showAlert(top.title, "Not implemented!");
	return null;
}

function appendNewOrg(porg, org) {
	showAlert(top.title, "Not implemented!");
}

function addChildOrg() {
	var porg = getSelectedOrg();
	if (!porg) {
		showAlert(top.title, orgMessages.manager.msgs.noOrgSelected);
	} else if (porg.type == "role") {
		showAlert(top.title, orgMessages.manager.msgs.noChildPermitted);
	} else {
		var org = showAddOrgDialog(porg);
		if (org) {
			appendNewOrg(porg, org);
		}
	}
}
//-----------End of add org----------

//--------------Edit org--------------
function showEditOrgDialog(org) {
	showAlert(top.title, "Not implemented!");
	return null;
}

function updateEditedOrg(org) {
	showAlert(top.title, "Not implemented!");
}

function editOrg() {
	var org = getSelectedOrg();
	if (!org) {
		showAlert(top.title, orgMessages.manager.msgs.noOrgSelected);
	} else {
		if (showEditOrgDialog(org))
			updateEditedOrg(org);
	}
}
//------------End of edit org------------

//------------Delete org--------------
function doDeleteOrg(org) {
	showAlert(top.title, "Not implemented!");
}

function removeOrgNode(org) {
	showAlert(top.title, "Not implemented!");
}

function delOrg() {
	var org = getSelectedOrg();
	if (!org) {
		showAlert(top.title, orgMessages.manager.msgs.noOrgSelected);
	} else {
		showConfirm(top.title, orgMessages.manager.msgs.confirmOrgDelete, function(toDel) {
				if (toDel) {
					doDeleteOrg(org);
				}
			}
		);
	}
}

function stopOrg() {
	showAlert(top.title, "Not implemented!");
}

function moveUp() {
	showAlert(top.title, "Not implemented!");
}

function moveDown() {
	showAlert(top.title, "Not implemented!");
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
		}, "-", {
			id: "btnDelOrg",
			text: orgMessages.manager.toolbar.delOrg,
			iconCls: "icon-remove",
			handler: delOrg
		}, {
			id: "btnStopOrg",
			text: orgMessages.manager.toolbar.stopOrg,
			iconCls: "icon-cancel",
			handler: stopOrg
		}, "-", {
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