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
		}]]
	});
});