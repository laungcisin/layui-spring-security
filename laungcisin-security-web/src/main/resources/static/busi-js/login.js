layui.use(['layer', 'form', 'element', 'layer'], function () {
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

    //监听表单登录提交
    form.on('submit(login-password)', function (data) {
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

    //监听手机验证码登录提交
    form.on('submit(login-sms)', function (data) {
        $.post('/authentication/mobile', data.field, function (ret) {
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

/**
 * 刷新验证码
 */
function refreshCode() {
    var captcha = document.getElementById("captcha");
    captcha.src = "/code/image?t=" + new Date().getTime();
}


/**
 * 刷新验证码
 */
function getSmsCode() {
    $.get('/code/sms?mobile=' + $('#mobile').val(), function (data) {
        layui.layer.open({
            title: '提示信息',
            content: data.content,
            btnAlign: 'c'
        });

        return false;
    });

    return false;
}
