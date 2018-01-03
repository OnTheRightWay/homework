function passwordOnFocus(ele){
    ele.placeholder="";
    var fof = document.getElementById("fof");
    fof.style.visibility="visible";
}
function passwordOnBlur(ele) {
    ele.placeholder="请输入登录密码";
    var fof = document.getElementById("fof");
    fof.style.visibility="hidden";
}
function usernameOnFocus(ele){
    ele.placeholder="";
    var bof = document.getElementById("bof");
    bof.style.visibility="visible";
}
function usernameOnBlur(ele) {
    ele.placeholder="请输入邮箱地址";
    var bof = document.getElementById("bof");
    bof.style.visibility="hidden";
}