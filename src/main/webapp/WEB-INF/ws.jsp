<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h1>WebSockets</h1>
<div class="row">
    <div class="col s3">
        <strong id="chat-user">Connecting...</strong>
        <input id="user-message" type="text">
        <button onclick="sendClick()">Send</button>
        <ul class="collection" id="chat-container">
        </ul>
    </div>
    <div class="col s9">
        <p>
            WebSocket - протокол (приблизно рівня HTTP), відомий схемами
            <b>ws://</b> та <b>wss://</b>. Для прикладного программування
            головна відмінність - у дуплексному режимі передавання даних
            за якого як і клієнт може ініціювати передачу, так і сервер.
            Реалізується це механізмом подій та їх утворенням як на клієнті,
            так і на сервері за однаковими принципами.
        </p>
        <p>
            Оскільки це інший протокол, він реалізується окремим сервером,
            який існує або окремо, або паралельно з основним (HTTP). Веб-
            сервери (на кшталт Tomcat) здатні обслуговувати обидва типа
            протоколів.
        </p>
        <p>
            Для роботи з вебсокетом додаємо залежність (javax.websocket-api),
            оголошуємо клас серверу (див. WebsocketServer). Оскільки сервер
            забезпечує дуплексний зв'язок, він повинен зберігати масив
            (колекцію) активних підключень (сесій) та реалізовувати засоби
            повідомлення усіх підключених клієнтів при надходженні даних від
            одного з них.
        </p>
        <p>
            Вебсокет, як об'єкт (Java), утворюється для одного HTTP-об'єкта,
            водночас у межах одного HTTP відбувається багаторазовий
            обмін даними. Іншими словами, багато вебсокет пакетів "прив'язані"
            до одного HTTP. Для збереження відомостей про HTTP (у якому,
            зокрема, є відомості про токен авторизації) необхідно вправаджувати
            конфігуратор.
        </p>
    </div>
</div>


<script>
    document.addEventListener('DOMContentLoaded',() => {
        const token = window.localStorage.getItem('token');
        if(token)
        {
            initWebsocket();
        }
        else {
            document.getElementById("chat-user").innerText =
            "Авторизуйтесь для користування чатом";
        }
    });
    function sendClick(){
        window.websocket.send(
            JSON.stringify({
                command: 'chat',
                data: document.getElementById("user-message").value
            }))
    }
    function addMessage(txt){
        const li = document.createElement("li");
        li.className = "collection-item";
        li.appendChild(document.createTextNode(txt));
        document.getElementById("chat-container").appendChild(li)
    }
    function initWebsocket(){
        const host = window.location.host + getAppContext();
        window.websocket = new WebSocket(`ws://${host}/chat`);
        window.websocket.onopen = onWsOpen;
        window.websocket.onclose = onWsClose;
        window.websocket.onmessage = onWsMessage;
        window.websocket.onerror = onWsError;

    }
    function onWsOpen(e){
        // console.log("onWsOpen", e);
        const token = window.localStorage.getItem('token');
        window.websocket.send(JSON.stringify({
            command: 'auth',
            data: token
        }));

    }
    function onWsClose(e){
        //console.log("onWsClose", e);
        addMessage("Chat deactivated");

    }
    function onWsMessage(e){
        // console.log("onWsMessage", e);
        const message = JSON.parse(e.data);
        if (message.status === 201){
            addMessage(message.data);
        }
        else if(message.status === 202){
            addMessage("Chat activated");
            const item = JSON.parse(atob(window.localStorage.getItem('token')));
            document.getElementById("chat-user").innerText =
                message.data;
        }
        else if(message.status === 403 || message.status === 401){
            document.getElementById("chat-user").innerText = "Повторіть авторизацію";
        }
        else
        {
            console.log(message);
        }

    }
    function onWsError(e){
        console.log("onWsError", e);

    }

    function getAppContext(){
        return '/' + window.location.pathname.split('/')[1];
    }
</script>