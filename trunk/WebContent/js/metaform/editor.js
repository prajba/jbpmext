INCLUDE([{
	src: "/js/ckeditor/adapters/jquery.js"
}]);

function initCKEditor() {
	CKEDITOR.editorConfig = function(config) {
		config.uiColor = '#ddd';

		config.toolbar = 'TB';
		config.toolbar_TB = [
			['Source'],
			['Cut','Copy','Paste','PasteText','PasteFromWord'],
			['Undo','Redo'],
			['Link','Unlink','Anchor'],
			['Image','Table','HorizontalRule'],
			['Bold','Italic','Underline'],
			['FontSize'],
			['TextColor','BGColor'],
			['NumberedList','BulletedList'],
			['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
			['zTextField', 'zTextarea']
		];

		config.fontSize_sizes = '10/10px;12/12px;14/14px;16/16px;18/18px;20/20px;22/22px;24/24px;28/28px;32/32px;48/48px;';
		config.extraPlugins = ["zforms"];
	};
	
	$("#formHtml").ckeditor();
}

$(function() {
	doEditorLayout("#editor");
	$(".inner-layout").addClass("filled").layout();
	initCKEditor();
});
