function getUser(){
    var url="http://localhost:8080/Day29/query";
    $.getJSON(
        url,function (JSONData,status) {
            if (status=="success"){
                var username = JSONData['username'];
                $('#user').text("用户:"+username);
            }
        }
    );
}
getUser();