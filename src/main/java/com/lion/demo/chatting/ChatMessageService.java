package com.lion.demo.chatting;

import java.util.List;

public interface ChatMessageService {

    List<ChatMessage> getListByUser(String uid, String friendUid);

    ChatMessage insertChatMessage(ChatMessage chatMessage);

}
