layui.use(['form', 'layer'], function () {
    var form = layui.form;
    var layer = layui.layer;

    //监听提交
    form.on('submit(roleSubmit)', function (data) {
        //获取选择的菜单
        var nodes = ztree.getCheckedNodes(true);
        var menuIdList = new Array();
        for (var i = 0; i < nodes.length; i++) {
            menuIdList.push(nodes[i].id);
        }

        //向form表单中添加选中的菜单
        data.field.menuIdList = menuIdList;
        $.ajax({
            type: "post",
            url: '/role/save',
            contentType: "application/json",
            data: JSON.stringify(data.field),
            async: false,
            dataType: "json",
            success: function (R) {
                if (R.code === 0) {
                    parent.layer.msg("操作成功", {icon: 1});
                    var frameIndex = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(frameIndex);
                    var parent_iframe = $(parent.document).find("iframe.cy-show ")[0] || $(parent.document).find("iframe")[0];
                    $(parent_iframe).contents().find(".search-btn").click();
                } else {
                    parent.layer.alert(R.msg);
                }
            },
            error: function () {
                layer.alert("系统错误");
            }
        });
        return false;
    });
});

/*下拉树*/
var setting = {
    data: {
        simpleData: {
            enable: true,
            idKey: "id",
            pIdKey: "pId",
            rootPId: -1
        },
        key: {
            url: "nourl"
        }
    },
    check: {
        enable: true,
        nocheckInherit: true
    }
};

var ztree;
//加载菜单树
$.ajax({
    type: "GET",
    url: '/noPermission/treeData',
    contentType: "application/json",
    async: false,
    dataType: "json",
    success: function (data) {
        ztree = $.fn.zTree.init($("#menuTree"), setting, data);
        ztree.expandAll(true);//展开所有节点
    },
    error: function () {
        alert("系统错误");
    }
});