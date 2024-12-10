package com.lion.demo.chatting;

import com.lion.demo.entity.User;
import com.lion.demo.service.UserService;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("chatting")
public class ChattingController {

    @Autowired
    private ChatMessageService chatMessageService;
    @Autowired
    private RecipientService recipientService;
    @Autowired
    private UserService userService;

    @GetMapping("/mock")
    public String mockForm() {
        return "chatting/mock";
    }

    @PostMapping("/mock")
    public String mockProc(String senderUid, String recipientUid, String message,
        LocalDateTime timestamp) {
        User sender = userService.findByUid(senderUid);
        User recipient = userService.findByUid(recipientUid);
        ChatMessage chatMessage = ChatMessage.builder()
            .sender(sender).recipient(recipient).message(message).timestamp(timestamp).hasRead(0)
            .build();
        chatMessageService.insertChatMessage(chatMessage);
//        recipientService.insertFriend(sender, recipient);
        return "/chatting/mock";
    }
}
