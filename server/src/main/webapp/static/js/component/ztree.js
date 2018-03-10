(function($){
$.extend({
    "tree": function (id,zNodes,opt,options) {
	    	var option = defaults;
	    	if(options!=undefined||options!=null){
		    	option.async = $.extend(defaults.async,options.async||{});
		    	option.callback = $.extend(defaults.callback,options.callback||{});
		    	option.view = $.extend(defaults.view,options.view||{});
		    	option.check = $.extend(defaults.check,options.check||{});
		    	option.data = $.extend(defaults.data,options.data||{});
	    	}
	    	currentTreeContent = $.extend(TreeContent,opt||{});
		    $.fn.zTree.init($(id), option,zNodes);
    	},	
    showTree:showMenu,
    hideTree:hideMenu
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
var TreeContent ={treeContentId:"treeContent",treeId:"treeDemo",viewNameId:"parentName",formDataId:"parentId"}; 
var currentTreeContent = {};
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
	function showMenu(opt) {
		
		var cityObj = $("#"+currentTreeContent.viewNameId);
		var cityOffset = $("#"+currentTreeContent.viewNameId).offset();
		$("#"+currentTreeContent.treeContentId).css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
		$("body").bind("mousedown", onBodyDown);
	}
	function hideMenu(){
		$("#"+currentTreeContent.treeContentId).fadeOut("fast");
	}
	function onBodyDown(event) {
			if (!(event.target.id == "menuBtn" || event.target.id == currentTreeContent.viewNameId || event.target.id == currentTreeContent.treeContentId || $(event.target).parents("#"+currentTreeContent.treeContentId).length>0)) {
				$.hideTree();
			}
	}
	function onCheck(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj(currentTreeContent.treeId),
		nodes = zTree.getCheckedNodes(true),
		v = "";
	     m = "";
		for (var i=0, l=nodes.length; i<l; i++) {
			
			v = nodes[i].name ;
			m = nodes[i].id ;
		}
		oneId=m;
		$("#"+currentTreeContent.viewNameId).attr("value", v);
		$("#"+currentTreeContent.formDataId).val(m);
	}
})(jQuery)