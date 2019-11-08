# springboot_websocket
## making chatting room using websocket in spring ## 

## code practice point ##
1. using websocket, sockjs and stomp.
2. used to use http polling in former service. change to websocket to emprove performance. 
3. first, made only one group chatting room. 
4. after that, made various group chatting room using different channel in Controller section.
5. stomp has concept of subscribe, so multi chatting room function is able in one contoller @MessageMapping and @SendTo. 
6. It even support sets sendTo annotation.
7. in server, there is not much thing to do to use websocket. Just import websocekt gradle and coding websocket configuration. 
in the configuration, we have to use sockjs. 
8. in client, javascript has small things to do. use sockjs and stomp to connect with server later.
9. websocket provides many annotation in spring. not only @MessageMapping and @SendTo but also @DestinationVariable and so one. 


## concept practice point ## 
1. websocket uses ws protocol. Http is used only one time to upgrade websocket protocol and it called handshaking. 
2. websocket only uses one tcp connection.
3. before websocket, http has polling, long polling and streaming. All of them are uni-directional. Furthermore, polling and long polling use
ajax several time. Two of them toss burden to server.  
4. polling is the worst. It sets timer to send request to server regularly. polling can still use in time non-sensitive service. 
5. long polling is quite similar with polling. But it doesn't return response before getting any event from server. So basically, 
it waits for that all the time. 
6. polling, longpolling, websocket connection can be options. But have to think what service I'll make. 
7. websocket is bi-directional. And it's not closed untill client or server wants to close it. So we don't have to observe handshaking all the time. 

8. sockjs is for controlling version in browser. IE support websocket in 10 version. So it might not be working sometimes in various situation. 
9. if the browser is not supporting browser, then sockjs switch websoket to long polling or streaming way in the browser. 

10. stomp is message handling protocol. Websocket library is working in quite low-level way, so we have to handle a message sending/receiving 
in client and server. However, stomp protocol has function of subscribe. All server has to do is getting message from specific client and 
passes to clients which subscribe the channel. 



## key point code ## 

     Logger log = LoggerFactory.getLogger(GreetingController.class);

    //클라이언트가 app/hello로 달고 오쳥을 보내면 controller가 messagemapping으로 잡는거겠지?
    @MessageMapping("/hello/{roomKey}")
    //client가 보낸 메세지를 /topic/greetings를 구독하는 다른 클라이언트들에게 보내준다 (broadcasting 해준다)
    @SendTo("/topic/greetings/{roomKey}" )
    public FullGreeting greeting(@DestinationVariable String roomKey,  FullGreeting input) throws Exception {
        log.info("입력된 이름: " + input.getName() +" 입력된 내용: " + input.getContent() );

        return new FullGreeting(input.getName(), input.getContent());
    }

    @MessageMapping("/typing/{roomKey}")
    @SendTo("/topic/waiting/{roomKey}")
    public FullGreeting waiting(@DestinationVariable String roomKey, FullGreeting input) throws Exception {
        log.info("누군가 waiting controller 호출함");
        //TODO: 나중에 직접 문자열을 보내주기
        return new FullGreeting(input.getName(), input.getContent());
    }



2. javascript 
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


## performance gif ## 

1. performance for maintaining various group chatting room. not use another websocekt. only one websocket is used and many different channel is used to serve these channel.



2. because they are using other channels, so messages can't be sent to each others. keep an eye on Controller setting. 


## reference ##
1. 
