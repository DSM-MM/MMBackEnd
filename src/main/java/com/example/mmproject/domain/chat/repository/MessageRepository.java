package com.example.mmproject.domain.chat.repository;

import com.example.mmproject.domain.chat.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
