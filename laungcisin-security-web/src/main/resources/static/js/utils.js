/*弹出层*/

/*
    参数解释：
    title   标题
    url     请求的url
    id      需要操作的数据id
    w       弹出层宽度（缺省调默认值）
    h       弹出层高度（缺省调默认值）
*/
function showWindows(title, url, w, h) {
    if (title == null || title == '') {
        title = false;
    }

    if (url == null || url == '') {
        url = "404.html";
    }

    if (w == null || w == '') {
        w = ($(window).width() * 0.9);
    }

    if (h == null || h == '') {
        h = ($(window).height() - 50);
    }

    parent.layer.open({
        type: 2,
        area: [w + 'px', h + 'px'],
        fix: true, //固定
        maxmin: false,
        resize: false,
        shadeClose: false,
        shade: [0.3, '#000'],
        title: title,
        content: url
    });
}

/*关闭弹出框口*/
function closeWindows() {
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
    return false;
}


