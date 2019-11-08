$(document).ready(function () {
    $('#finish').on('click', function () {

        let rName = $('#roomname').val();

        if(rName.length === 0) {
            console.log(rName.length);
            $('#roomname').addClass('shake');

        } else {
            let body = {
                roomName: $('#roomname').val(),
                leaderName: $('#leadername').val()
            };
            let name = $("#leadername").val();

            let Jdata = JSON.stringify(body);

            $.ajax({
                url: "/makeroom",
                method: "post",
                data: Jdata,
                contentType: "application/json"
            })
                .done(function (data) {
                    parent.close();
                    window.close();
                    self.close();

                    let chatRoomVO = data;

                    window.opener.top.location.href = "/chatroom?name=" + name + "&roomName=" + chatRoomVO.roomName + "&roomId=" + chatRoomVO.roomId;
                })
                .fail(function () {
                    console.log("네트워크 오류");
                })

        }
    });
});