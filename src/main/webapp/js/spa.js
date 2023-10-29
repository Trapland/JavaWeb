document.addEventListener('DOMContentLoaded',() => {
    M.Modal.init( document.querySelectorAll('.modal'), {
        opacity: 0.6,
        inDuration: 200,
        outDuration: 200,
        onOpenStart: onModalOpens,
    });
    const authSignInButton = document.getElementById("auth-sign-in");
    if(authSignInButton){
        authSignInButton.addEventListener('click',authSignInButtonClick);
    }
    else {
        console.error("auth-sign-in not found");
    }
    const spaTokenStatus = document.getElementById("spa-token-status");
    if(spaTokenStatus){
        const token = window.localStorage.getItem('token');
        spaTokenStatus.innerText = (!!token) ? 'Встановлено ' + token : 'Не встановлено';
        if(token){
            const tokenObject = JSON.parse(atob(token));
            spaTokenStatus.innerText = "Дійсний до " + tokenObject.exp;
            const appContext = getAppContext();

            fetch(`${appContext}/tpl/spa-auth.html`,{
                method: 'GET',
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            })
                .then(r=>r.text()).then(t =>
            document.querySelector('auth-part').innerHTML = t);
            document.getElementById("spa-log-out")
                .addEventListener('click',logoutClick);
        }
    }
    const spaGetDataButton = document.getElementById("spa-get-data");
    if (spaGetDataButton) spaGetDataButton.addEventListener('click', spaGetDataClick);
    const spaGetDataButton2 = document.getElementById("spa-get-data2");
    if (spaGetDataButton2) spaGetDataButton2.addEventListener('click', spaGetDataClick2);
    const spaGetDataButton3 = document.getElementById("spa-get-data3");
    if (spaGetDataButton3) spaGetDataButton3.addEventListener('click', spaGetDataClick3);
    const spaGetDataButton4 = document.getElementById("spa-get-data4");
    if (spaGetDataButton4) spaGetDataButton4.addEventListener('click', spaGetDataClick4);
});
function onModalOpens(){
    [authLogin,authPassword,authMessage] = getAuthElements();
    authLogin.value = "";
    authPassword.value = "";
    authMessage.innerText = "";
}

function getAppContext(){
    return '/' + window.location.pathname.split('/')[1];
}
function spaGetDataClick(){
    fetch(`${getAppContext()}/tpl/NP.png`,{
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${window.localStorage.getItem('token')}`
        }
    })
        .then(r=>r.blob()).then(b =>
    {
        const blobUrl = URL.createObjectURL(b);
        document.querySelector('auth-part').innerHTML =
        `<img src="${blobUrl}" width="100"/>`;

    });
}
function spaGetDataClick2(){
    console.log('spaGetDataClick2');
    fetch(`${getAppContext()}/tpl/figures.html`,{
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${window.localStorage.getItem('token')}`
        }
    })
        .then(r=>r.text()).then(t =>
        document.querySelector('auth-part').innerHTML = t);
}
function spaGetDataClick3(){
    console.log('spaGetDataClick3');
    fetch(`${getAppContext()}/tpl/table.html`,{
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${window.localStorage.getItem('token')}`
        }
    })
        .then(r=>r.text()).then(t =>
        document.querySelector('auth-part').innerHTML = t);
}
function spaGetDataClick4(){
    console.log('spaGetDataClick4');
    fetch(`${getAppContext()}/tpl/test.html`,{
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${window.localStorage.getItem('token')}`
        }
    })
        .then(r=>{
            if(r.status === 404)
            {
                document.querySelector('auth-part').innerHTML = `error 404<br/>`;
            }
        })
}

function logoutClick(){
    window.localStorage.removeItem('token');
    window.location.reload();
}

function authSignInButtonClick(){
    [authLogin,authPassword,authMessage] = getAuthElements();
    if(authLogin.value.length === 0){
        authMessage.innerText = "Логін не може бути порожнім";
    }
const appContext = getAppContext();

    fetch(`${appContext}/auth?login=${authLogin.value}&password=${authPassword.value}`,{
        method:'GET'
    }).then(r => {
        if(r.status !== 200){
            authMessage.innerText = "Автентифікацію відхилено";
        }
        else {
            r.text().then(base64encodedText => {
                console.log(base64encodedText);

                const token = JSON.parse(atob(base64encodedText));
                if (typeof token.jti === 'undefined') {
                    authMessage.innerText = "Помилка одержання токену"
                    return;
                }
                window.localStorage.setItem('token', base64encodedText);
                if(appContext.includes("spa"))
                {
                    window.location.reload();
                }
                else
                {
                    window.location.replace(getAppContext() + "/spa");
                }
            })
        }
    });
}

function getAuthElements(){
    const authLogin = document.getElementById("auth-login");
    if(!authLogin){
        throw "auth-login not found";
    }
    const authPassword = document.getElementById("auth-password");
    if(!authPassword){
        throw "auth-password not found";
    }
    const authMessage = document.getElementById("auth-message");
    if(!authMessage){
        throw "auth-message not found";
    }
    return [authLogin,authPassword,authMessage];
}