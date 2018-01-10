layui.use(['form', 'layer'], function () {
    var form = layui.form;
    var layer = layui.layer;

    //监听提交
    form.on('submit(roleSubmit)', function (data) {
        $.ajax({
            type: "post",
            url: '/role/update',
            contentType: "application/json",
            data: JSON.stringify(data.field),
            async: false,
            dataType: "json",
            success: function (R) {
                if (R.code === 0) {
                    parent.layer.msg("操作成功", {icon: 1});
                    var frameIndex = parent.layer.getFrameIndex(window.name);//获取窗口索引
                    parent.layer.close(frameIndex);//关闭弹出的子页面窗口
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

var treeObj;

//加载菜单树
$.ajax({
    type: "GET",
    url: '/role/info/' + $('#roleId').val(),
    contentType: "application/json",
    async: false,
    dataType: "json",
    success: function (data) {
        treeObj = $.fn.zTree.init($("#menuTree"), setting, data);
        treeObj.expandAll(true);//展开所有节点
        treeObj.checkAllNodes(true);//勾选全部节点

        //禁用全部节点
        var rootNodes = treeObj.getNodes();
        var nodes = treeObj.transformToArray(rootNodes);
        for (var i = 0, len = nodes.length; i < len; i++) {
            treeObj.setChkDisabled(nodes[i], true);
        }
    },
    error: function () {
        parent.layui.layer.alert("系统错误");
    }
});

/*
function initTree() {
    //勾选已选中的菜单
    $.ajax({
        type: "get",
        url: '/role/info/' + $('#roleId').val(),
        contentType: "application/json",
        async: false,
        dataType: "json",
        success: function (data) {
            //勾选角色所拥有的菜单
            treeObj = $.fn.zTree.init($("#menuTree"), setting, data);
            for (var i = 0; i < data.length; i++) {
                var node = treeObj.getNodeByParam("id", data[i].menuId);
                if (node != null) {
                    treeObj.checkNode(node, true, false);
                }
            }
        },
        error: function () {
            alert("系统错误");
        }
    });
}
*/