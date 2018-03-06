layui.use(['form', 'table'], function () {
    var table = layui.table;
    var form = layui.form;

    //监听查询form
    form.on('submit(search)', function (data) {
        var defaultParam = {
            limit: 10,
            page: 1,
            _: new Date().getTime()
        };

        var options = $.extend(defaultParam, data.field);
        table.reload('menuListTable', {
            where: options
        });

        return false;//阻止form提交，刷新界面
    });

    table.render({
        elem: '#menuListTable',
        height: 'full-150',
        cellMinWidth: 80,
        url: '/menu/listMenu', //数据接口
        page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
            layout: ['prev', 'page', 'next', 'skip', 'limit', 'count'], //自定义分页布局
            curr: 1, //设定初始在第 1 页
            groups: 5, //只显示 3 个连续页码
            first: true, //显示首页
            last: true //显示尾页
        },
        cols: [[ //表头
            {type: 'checkbox'},
            {field: 'parentName', title: '上级菜单', width: 100, align: 'center'},
            {field: 'name', title: '菜单名称', width: 150, align: 'center'},
            {
                field: 'icon', title: '菜单图标', width: 100, align: 'center', templet: function (d) {
                    if (d.icon == "" || d.icon == null) {
                        return "";
                    }
                    var result = '<i class="' + d.icon + '"></i>';
                    return result;
                }
            },
            {field: 'url', title: '菜单URL', width: '30%', align: 'center'},
            {field: 'permissions', title: '授权标识', width: '30%', align: 'center'},
            {
                field: 'type', width: 10, title: '类型', width: 80, align: 'center', templet: function (d) {
                    var value = d.type;
                    if (value === 0) {
                        return '<a class="layui-btn layui-btn-sm layui-btn-warm">目录</a>';
                    }
                    if (value === 1) {
                        return '<a class="layui-btn layui-btn-sm layui-btn-sys">菜单</a>';
                    }
                    if (value === 2) {
                        return '<a class="layui-btn layui-btn-sm layui-btn-green">按钮</a>';
                    }
                    if (value == "" || value == null) {
                        return "";
                    }
                    return value;
                }
            }
        ]]
    });
});
