(function($){
$.extend({
    "tree": function (id,zNodes,options) {
	    	var option = defaults;
	    	if(options!=undefined||options!=null){
		    	option.async = $.extend(defaults.async,options.async||{});
		    	option.callback = $.extend(defaults.callback,options.callback||{});
		    	option.view = $.extend(defaults.view,options.view||{});
		    	option.check = $.extend(defaults.check,options.check||{});
		    	option.data = $.extend(defaults.data,options.data||{});
	    	}
		    $.fn.zTree.init($(id), option,zNodes);
    	},	
    	showTree:showMenu
});
var defaults = {
		async: {
			enable: true,
			url:"/test",
			autoParam:["id"],
			dataFilter: filter
		},
		view: {
			selectedMulti: false,
			dblClickExpand: false
		},
		check: {
			enable: true,
			chkStyle: "radio",
			radioType: "all"
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			onCheck: onCheck
		}
	};

	function filter(treeId, parentNode, childNodes) {
		if(childNodes.status!=200){return null}
		childNodes = childNodes.result;
		if (!childNodes) return null;
		for (var i=0, l=childNodes.length; i<l; i++) {
			childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
			if(childNodes[i].isEnd==0){
				childNodes[i].isParent=true;
			}else{
				childNodes[i].isParent=false;
				childNodes[i].chkDisabled=true;
			}
			if(childNodes[i].id==oneId){
				childNodes[i].checked=true;
			}
		}
		return childNodes;
	}
	function showMenu() {
		var cityObj = $("#parentName");
		var cityOffset = $("#parentName").offset();
		$("#treeContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
		$("body").bind("mousedown", onBodyDown);
	}
	function hideMenu(dom,a){
		$("#treeContent").fadeOut("fast");
	}
	function onBodyDown(event) {
			if (!(event.target.id == "menuBtn" || event.target.id == "parentName" || event.target.id == "treeContent" || $(event.target).parents("#treeContent").length>0)) {
				hideMenu();
			}
	}
	function onCheck(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
		nodes = zTree.getCheckedNodes(true),
		v = "";
	     m = "";
		for (var i=0, l=nodes.length; i<l; i++) {
			
			v = nodes[i].name ;
			m = nodes[i].id ;
		}
		oneId=m;
		$("#parentName").attr("value", v);
		$("#parentId").val(m);
	}
})(jQuery)