let stompClient = null;
let userId = null;
let selectedReceiverId = null;
let usersArr = [];

function login() {
    userId = document.getElementById('loginUserId').value.trim();
    if (!userId) {
        alert('Введите свой ID!');
        return;
    }
    document.getElementById('currentUser').textContent = `Ваш ID: ${userId}`;
    document.getElementById('loginBox').style.display = 'none';
    document.getElementById('chatBox').style.display = 'flex';
    document.getElementById('usersListBox').style.display = '';
    loadUsers();
    connectSocket();
}

function loadUsers() {
    fetch('/api/users')
        .then(res => res.json())
        .then(users => {
            usersArr = users.filter(u => u.id != userId);
            showUsers(usersArr);
        });
}

function showUsers(users) {
    const usersList = document.getElementById('usersList');
    usersList.innerHTML = '';
    users.forEach(u => {
        const div = document.createElement('div');
        div.className = 'user-item' + (selectedReceiverId == u.id ? ' selected' : '');
        div.textContent = `${u.userName || '(Без имени)'} [${u.id}]`;
        div.onclick = () => {
            selectedReceiverId = u.id;
            document.getElementById('receiver').value = u.id;
            showUsers(usersArr);
        };
        usersList.appendChild(div);
    });
}

function connectSocket() {
    const socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function () {
        stompClient.subscribe('/queue/messages/' + userId, function (message) {
            console.log("Получено сообщение:", message.body);
            showMessage(JSON.parse(message.body), false);
        });
    });
}

function sendMessage() {
    const receiver = document.getElementById('receiver').value.trim();
    const text = document.getElementById('messageInput').value.trim();
    if (!receiver || !text) {
        alert('Выберите получателя и введите сообщение!');
        return;
    }
    const message = {
        senderId: Number(userId),
        receiverId: Number(receiver),
        messageText: text
    };
    stompClient.send("/app/chat", {}, JSON.stringify(message));
    showMessage(message, true);
    document.getElementById('messageInput').value = '';
}

function showMessage(message, self) {
    const messages = document.getElementById('messages');
    const row = document.createElement('div');
    row.className = 'message-row' + (self ? ' self' : '');
    const bubble = document.createElement('div');
    bubble.className = 'bubble';
    bubble.textContent = message.messageText;
    const meta = document.createElement('span');
    meta.className = 'meta';
    meta.textContent = self ? 'Вы' : `От: ${message.senderId}`;
    bubble.appendChild(meta);
    row.appendChild(bubble);
    messages.appendChild(row);
    messages.scrollTop = messages.scrollHeight;
}

document.addEventListener('DOMContentLoaded', function () {
    const input = document.getElementById('messageInput');
    if (input) {
        input.addEventListener('keydown', function (e) {
            if (e.key === 'Enter') sendMessage();
        });
    }
});