var client = null;
var color;
var saveNick = null;



    function setNickName(name) {
        saveNick=name;
    }

 function showMessage(value,user, userColor){

  var newResponse = document.createElement('p');
    newResponse.style.color = userColor;
     newResponse.appendChild(document.createTextNode(user));
     newResponse.appendChild(document.createTextNode(": "));
     newResponse.appendChild(document.createTextNode(value));


  var response =  document.getElementById('response');
  response.appendChild(newResponse);


     document.getElementById("nickName").innerHTML = "Log as: " +  saveNick;


 }


function getRandomColor() {
    var letters = '0123456789ABCDEF';
    var color = '#';
    for (var i = 0; i < 6; i++) {
        color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
}

function connect(){
        client = Stomp.client('ws://localhost:6070/chat');
        client.connect({},function (frame){
            color = getRandomColor();
            client.subscribe("/topic/messages", function(message){

                if(saveNick==null){setNickName(JSON.parse(message.body).user)}

                    showMessage(JSON.parse(message.body).value, JSON.parse(message.body).user, JSON.parse(message.body).userColor)

            }
           );
        }
        )
}
function sendMessage(){
    var messageToSend = document.getElementById('messageToSend').value;
    var user = saveNick;

    client.send("/app/chat",{},JSON.stringify({'value': messageToSend, 'user': user, 'userColor': color}));
    document.getElementById('messageToSend').value ="";



}
