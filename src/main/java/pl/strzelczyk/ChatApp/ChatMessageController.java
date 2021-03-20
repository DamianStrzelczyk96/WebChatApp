package pl.strzelczyk.ChatApp;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedHashMap;

@Controller
public class ChatMessageController {
    String name;

    @GetMapping("/")
    public String getUserName(){
        Object detalis =  ((UsernamePasswordAuthenticationToken)((OAuth2Authentication)((SecurityContextImpl) SecurityContextHolder.getContext()).getAuthentication()).getUserAuthentication()).getDetails();
        name = ((LinkedHashMap)detalis).values().toArray()[1].toString();
       return "gui";
    }

    @GetMapping("/hello")
    public String goToHello(){
        return "gui";
    }

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public ChatMessage get(ChatMessage chatMessage){
        if ( chatMessage.getUser()==null){
            chatMessage.setUser(name);
        }
    return chatMessage;
    }
}
