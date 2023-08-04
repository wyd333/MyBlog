// 根据 key 获取 url 中对应的 value

function getParamValue(key) {
    // 1-得到url的参数部分
    var params = location.search;

    // 2-去除"?"
    if(params.indexOf("?") >= 0) {  //存在问号"?"
        params = params.substring(1);   //从第一位开始截取，直到末尾
        // 3-根据"&"分割成多个数组
        var paramArray = params.split("&");
        // 4-循环对比 key，并返回查询的 value
        if(paramArray.length >= 1){
            for (var i = 0; i < paramArray.length; i++) {
                // key=value
                var item = paramArray[i].split("=");
                if(key == item[0]) {
                    return item[1];
                }
            }
        }

    }
    return null;
}