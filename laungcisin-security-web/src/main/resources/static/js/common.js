/** common.js By Beginner Emain:zheng_jinfan@126.com HomePage:http://www.zhengjinfan.cn */
layui.define(['layer'], function (exports) {
    "use strict";

    var $ = layui.jquery,
        layer = layui.layer;

    var common = {
        /**
         * 抛出一个异常错误信息
         * @param {String} msg
         */
        throwError: function (msg) {
            throw new Error(msg);
            return;
        },
        /**
         * 弹出一个错误提示
         * @param {String} msg
         */
        msgError: function (msg) {
            layer.msg(msg, {
                icon: 5
            });
            return;
        }
    };

    exports('common', common);
});

//选择一条记录
function getSelectedRow(table_id) {
    var checked = $("#" + table_id + " tbody .layui-form-checked");
    if (checked.length == 0) {
        parent.layer.msg("请选择一条记录", {icon: 5});
        return;
    }
    var selectedIDs = [];
    for (var i = 0; i < checked.length; i++) {
        var _this = $(checked[i]).prev();
        selectedIDs.push($(_this).attr("primary"));

    }
    if (selectedIDs.length > 1) {
        parent.layer.msg("只能选择一条记录", {icon: 5});
        return;
    }
    return selectedIDs[0];
}