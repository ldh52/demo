package com.lion.demo.chatting;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipientRepository extends JpaRepository<Recipient, Long> {

    List<Recipient> findByUserUid(String uid);

    List<Recipient> findByFriendUid(String friendUid);
}
