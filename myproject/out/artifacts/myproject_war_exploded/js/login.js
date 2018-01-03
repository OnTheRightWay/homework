function passwordOnFocus(ele){
    console.log(ele.style.border);
    ele.placeholder="";
    // ele.style.fontSize="30px";
    // ele.style.border="solid 2px red"
    // ele.style.outline="solid 2px red";
}
function passwordOnBlur(ele) {
    ele.placeholder="登录密码";
    // ele.style.fontSize="20px";
    // ele.style.border="none"
    // ele.style.outline="invert none medium";
}
function usernameOnFocus(ele){
    ele.placeholder="";
    // ele.style.fontSize="30px";
    // ele.style.border="solid 2px red"
}
function usernameOnBlur(ele) {
    ele.placeholder="邮箱地址";
    // ele.style.fontSize="20px";
    // ele.style.border="none"
}
function showTime() {
    var time = document.getElementById("time");
    var date = new Date();
    var s = date.toLocaleString();
    // time.innerText=year+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds;
    time.innerText=s

    setInterval(function () {
        var date = new Date();
        var s = date.toLocaleString();
        // time.innerText=year+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds;
        time.innerText=s
        // time.innerText=s
        // var time2 = date.getTime();
     },1000);
}
showTime();