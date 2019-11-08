$(document).ready(function () {


let output = document.getElementById('output');
let content = document.getElementById('content');
let feedback = document.getElementById('feedback');

//한명이 타이핑중이면 그거 보여주려고
let conenctStatus = false;
let keyFired = false;
let roomKey = $('#roomId').val();
    let greetingUrl = '/topic/greetings/' + roomKey;
    let waitingUrl = '/topic/waiting/' + roomKey;
    let helloUrl ="/app/hello/" + roomKey;
    let typingUrl = '/app/typing/' + roomKey;

let stompClient = null;


let newMessage = {
    name: $("#name").val(),
    content: "*새로운 유저가 입장하였습니다*",
    roomId: $('#roomId').val()
};


function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);

    if (connected) {
        $("#connect").prop("class", "connection_btn white");
        $("#disconnect").prop("class", "disconnection_btn purple");
        $("#mario-chat").show();
    } else {
        $("#connect").prop("class", "connection_btn purple");
        $("#disconnect").prop("class", "disconnection_btn white");
        $("#mario-chat").hide();
    }
    $("#greetings").html("");
}



function connect() {
    let socket = new SockJS('/endpoint-websocket');

    stompClient = Stomp.over(socket);
     stompClient.connect({}, async function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        conenctStatus = true;


       await stompClient.subscribe(greetingUrl , function (greeting) {
            showGreeting(JSON.parse(greeting.body));
        });

       await stompClient.send(helloUrl, {}, JSON.stringify(newMessage));

        stompClient.subscribe(waitingUrl, function (waiting) {
            let waitVo = JSON.parse(waiting.body);
            if(waitVo.name !== $("#name").val()) {
                feedback.innerHTML += '<p><strong>' + waitVo.name + ': </strong>' + waitVo.content + '</p>';
            }
        });
    });
};

//TODO: 얘를 즉시실행 함수로 만들고 connet는 이따가 다시 또 이용하자!
(function init() {
    connect();
})();



function disconnect() {

    if (stompClient !== null) {
        //접속 종료하기 전에 채팅방에 뿌려줘야한다.
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
    output.innerHTML='';
    conenctStatus = false;
}

//TODO: 서버에게 데이터를 보내는 부분,stomp를 이용하고 있고 주소를 보니 controller에서 messagemapping으로 기다리고 있는 부분이었음
function sendName() {
    let sendData = {
        name: $("#name").val(),
        content: $("#content").val(),
        roomId: $('#roomId').val()
    };



    stompClient.send(helloUrl, {}, JSON.stringify(sendData));
    keyFired = false;
    $("#content").val("");
}


function showGreeting(message) {
    let fullGreetingVo = message;
    feedback.innerHTML = '';
    output.innerHTML += '<p><strong>' + fullGreetingVo.name + ': </strong>' + fullGreetingVo.content + '</p>';

    //특정 div에서 자동으로 스크롤 맨 아래로 이동하는 함수
    $('#chat-window').stop().animate({
        scrollTop: $('#chat-window')[0].scrollHeight
    }, 800);
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#connect").click(function () {
        connect();
    });
    $("#disconnect").click(function () {
        let byeUser = {
            name: $("#name").val(),
            content: "*접속을 종료합니다.*",
            roomId: $('#roomId').val()
        };

        stompClient.send(helloUrl, {}, JSON.stringify(byeUser));
        disconnect();
    });
    $("#send").click(function () {
        sendName();
    });

    content.addEventListener('keydown', function () {
        let typing = {
            name: $("#name").val(),
            content: "*입력중입니다.....*",
            roomId: $('#roomId').val()
        };

        if (conenctStatus && !keyFired) {
            stompClient.send(typingUrl, {}, JSON.stringify(typing));
            keyFired = true;
        }

    });
});


});



