package com.lion.demo.chatting;

import com.lion.demo.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cmid;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "senderUid", referencedColumnName = "uid")
    private User sender;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "recipientUid", referencedColumnName = "uid")
    private User recipient;

    private String message;
    private LocalDateTime timestamp;
    private int hasRead;
}
