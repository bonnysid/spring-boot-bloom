package com.bonnysid.bloom.respos;

import com.bonnysid.bloom.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query(value = "select * from message where id_dialog = ?1", nativeQuery = true)
    public Optional<List<Message>> getMessagesByDialogId(Long id);
}
