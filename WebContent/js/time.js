/**
 * Created by moliner_yunfei on 2017/12/26.
 */
function getTime(id,ui) {
    var now, weeks, mon, day, hour, min, sec;
    now = new Date();
    weeks = new Array("日", "一", "二", "三", "四", "五", "六");
    mon = now.getMonth() + 1;
    day = now.getDate();
    hour = now.getHours();
    min = now.getMinutes();
    sec = now.getSeconds();
    mon = mon < 10 ? "0" + mon : mon;
    day = day < 10 ? "0" + day : day;
    hour = hour < 10 ? "0" + hour : hour;
    min = min < 10 ? "0" + min : min;
    sec = sec < 10 ? "0" + sec : sec;
    $("#" + id).html(
        "<span>" + now.getFullYear() + "-" + mon + "-" + day + "  " +
        hour + ":" + min + ":" + sec + "  " +
        "星期" + weeks[now.getDay()] + "  " + ui+"天气</span>");
}
