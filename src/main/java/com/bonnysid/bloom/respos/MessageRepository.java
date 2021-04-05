package com.bonnysid.bloom.respos;

import com.bonnysid.bloom.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
