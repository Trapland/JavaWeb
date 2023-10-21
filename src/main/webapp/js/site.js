document.addEventListener('DOMContentLoaded',function (){

    //var instances =
     M.Modal.init(document.querySelectorAll('.modal'),{
        opacity: 0.5,
        inDuration: 200,
        outDuration: 200
    });
     const createButton = document.getElementById("db-create-button");
    if(createButton) createButton.addEventListener('click',createButtonClick);
    const insertButton = document.getElementById("db-insert-button");
    if(insertButton) insertButton.addEventListener('click',insertButtonClick);
    const readButton = document.getElementById("db-read-button");
    if(readButton) readButton.addEventListener('click',readButtonClick);
});

function createButtonClick(){
    fetch(window.location.href, {
        method: 'PUT'
    }).then(r => r.json()).then(j => {
        console.log(j);
    });
}

function insertButtonClick(){
    const nameInput = document.querySelector('[name="user-name"]');
    if(!nameInput) throw '[name="user-name"] not found';
    const phoneInput = document.querySelector('[name="user-phone"]');
    if(!phoneInput) throw '[name="user-phone"] not found';
    fetch(window.location.href, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({name: nameInput.value,
                                    phone: phoneInput.value})
    }).then(r => r.json()).then(j => {
        console.log(j);
    });
}

function readButtonClick(){
    fetch(window.location.href,{
        method: "COPY"
    }).then(r => r.json()).then(showCalls);
}
function showCalls(j){
    var table = '<table class="material-table"><tr><th>id</th><th>name</th><th>phone</th><th>callMoment</th><th>delete</th></tr>';
    for (let call of j){
        let m = ( typeof call.callMoment == 'undefined' || call.callMoment == null ) ?
            `<button data-id="${call.id}" onclick="callClick(event)">call</button>` :  call.callMoment ;

        let d = `<button class="btn orange lighten-2" data-id="${call.id}" onclick="deleteClick(event)"><i class="material-icons red-text">remove</i></button>`;
        table += `<tr><td>${call.id}</td><td>${call.name}</td><td>${call.phone}</td><td>${m}</td><td>${d}</td></tr>`;
    }
    table+= "</table>";
    document.getElementById("calls-container").innerHTML = table;
}

function deleteClick(e){
    const btn = e.target.closest('button');
    const callId = btn.getAttribute("data-id");
    if(confirm(`DELETE ORDER NUMBER ${callId}` )){
        fetch(window.location.href + "?call-id=" + callId, {
            method: 'DELETE',
        }).then(r => {
            if(r.status === 202){ // успішне видалення
                let tr =
                    btn          // button
                    .parentNode  // td
                    .parentNode; // tr
                tr.parentNode.removeChild(tr);
            }
            else{
                r.json().then(alert);
            }
        });
    }
}

function callClick(e){
    const callId = e.target.getAttribute("data-id");
    if(confirm(`MAKE CALL FOR ORDER NUMBER= ${callId}` )){
        fetch(window.location.href + "?call-id=" + callId, {
            method: 'PATCH',
        }).then(r => r.json()).then(j => {
            if(typeof j.callMoment == 'undefined') // j - текст помилки
            {
                alert(j);
            }
            else {
                //прибираємо кнопку та ставимо дату
                e.target.parentNode.innerHTML = j.callMoment;
            }
            console.log(j);
        });
    }
}