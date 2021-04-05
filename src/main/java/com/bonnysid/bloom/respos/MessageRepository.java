package com.bonnysid.bloom.respos;

import com.bonnysid.bloom.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query(value = "select * from message where id_dialog = ?1", nativeQuery = true)
    public Optional<List<Message>> getMessagesByDialogId(Long id);

    @Query(value = "select m.id, m.id_dialog, m.text, m.date, m.id_from_user from message as m inner join dialogs d on d.id = m.id_dialog where d.id_from_user = ?1 and d.id_to_user = ?2", nativeQuery = true)
    public Optional<List<Message>> getMessagesByUserId(Long fromId, Long toId);
}
