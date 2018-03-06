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
        table.reload('roleListTable', {
            where: options
        });

        return false;//阻止form提交，刷新界面
    });

    table.render({
        elem: '#roleListTable',
        height: 'full-200',
        cellMinWidth: 80,
        url: '/role/listRole', //数据接口
        page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
            layout: ['prev', 'page', 'next', 'skip', 'limit', 'count'], //自定义分页布局
            curr: 1, //设定初始在第 1 页
            groups: 3, //只显示 3 个连续页码
            first: true, //显示首页
            last: true //显示尾页
        },
        cols: [[ //表头
            {type: 'numbers', fixed: true},
            {field: 'roleName', title: '角色名称', width: 200, align: 'center'},
            {field: 'roleCode', title: '角色代码', width: 200, align: 'center'},
            {
                field: 'status', title: '状态', width: 70, align: 'center', templet: function (d) {
                    var status = d.status === 1 ? '启用' : '禁用';
                    var cls = d.status === 1 ? '' : 'layui-btn-danger';
                    return '<a href="#" class="layui-btn ' + cls + ' layui-btn-xs">' + status + '</a>';
                }
            },
            {field: 'remark', title: '备注', width: 180, align: 'center'},
            {
                field: 'createTime', title: '创建时间', width: 180, align: 'center', templet: function (d) {
                    return new Date(parseInt(d.createTime)).toLocaleString().replace(/:\d{1,2}$/, ' ');
                }
            },
            {fixed: 'right', width: 180, align: 'center', toolbar: '#roleBar'} //这里的toolbar值是模板元素的选择器
        ]]
    });

    //监听工具条
    table.on('tool(roleTable)', function (obj) { //注：tool是工具条事件名，roleTable是table原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的DOM对象

        if (layEvent === 'detail') { //查看
            //do somehing
        } else if (layEvent === 'delete') { //删除
            layer.confirm('真的删除行么', function (index) {
                obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                layer.close(index);
                //向服务端发送删除指令
            });
        } else if (layEvent === 'edit') { //编辑
            showWindows('修改角色', '/role/updateRolePage/' + data.roleId, 793, 500, function () {
                $('.search-btn').click();
            });
            // parent.layer.open({
            //     type: 2,
            //     area: ['793px', '500px'],
            //     fix: true, //固定
            //     maxmin: false,
            //     resize: false,
            //     shadeClose: false,
            //     shade: [0.3, '#000'],
            //     title: '修改角色',
            //     content: '/role/updateRolePage/' + data.roleId,
            //     end: function () {
            //         $('.search-btn').click();
            //     }
            // });
        }
    });
});
