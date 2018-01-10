layui.use('table', function () {
    var table = layui.table;

    //第一个实例
    table.render({
        elem: '#userListTable',
        height: 'full-200',
        cellMinWidth: 80,
        url: '/user/listUser', //数据接口
        page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
            layout: ['prev', 'page', 'next', 'skip', 'limit', 'count'], //自定义分页布局
            curr: 1, //设定初始在第 1 页
            groups: 3, //只显示 3 个连续页码
            first: true, //显示首页
            last: true //显示尾页
        },
        cols: [[ //表头
            {field: 'username', title: '用户名', width: 180},
            {field: 'email', title: '电子邮箱', width: 180},
            {field: 'mobile', title: '手机号码', width: 180},
            {
                field: 'status', title: '状态', width: 70, templet: function (d) {
                    var status = d.status === 1 ? '启用' : '禁用';
                    var cls = d.status === 1 ? '' : 'layui-btn-danger';
                    return '<a href="#" class="layui-btn ' + cls + ' layui-btn-xs">' + status + '</a>';
                }
            },
            {fixed: 'right', width: 180, align: 'center', toolbar: '#barDemo'} //这里的toolbar值是模板元素的选择器
        ]]
    });

    //监听工具条
    table.on('tool(test)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的DOM对象

        if (layEvent === 'detail') { //查看
            //do somehing
        } else if (layEvent === 'del') { //删除
            layer.confirm('真的删除行么', function (index) {
                obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                layer.close(index);
                //向服务端发送删除指令
            });
        } else if (layEvent === 'edit') { //编辑
            //do something

            //同步更新缓存对应的值
            obj.update({
                username: '123'
                , title: 'xxx'
            });
        }
    });
});
