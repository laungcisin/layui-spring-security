layui.use(['layer', 'form'], function () {
    var form = layui.form;

    function loginSuccess() {
        layer.msg("登录成功", {icon: 6, time: 1000}, function (index) {
            layer.close(index);
            window.location.href = '/';
        });
    }

    function loginError(msg) {
        layer.msg(msg, {icon: 5, time: 2000}, function (index) {
            layer.close(index);
        });
    }

    //监听提交
    form.on('submit(login)', function (data) {
        $.post('/authentication/form', data.field, function (ret) {
            if (ret.code === 200) {
                loginSuccess();
            } else {
                loginError(ret.content);
                refreshCode();
            }
        });
        return false;
    });
});

function refreshCode() {
    var captcha = document.getElementById("captcha");
    captcha.src = "/code/image?width=100&height=38?t=" + new Date().getTime();
}
