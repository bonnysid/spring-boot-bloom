package com.bonnysid.bloom.respos;

import com.bonnysid.bloom.model.Dialog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DialogsRepository extends JpaRepository<Dialog, Long> {

    @Query(value = "select * from dialogs where id_from_user = ?1", nativeQuery = true)
    public Optional<List<Dialog>> getAllByUserID(Long id);

    @Query(value = "select * from dialogs where id_from_user = ?1 and id_to_user = ?2", nativeQuery = true)
    public Optional<Dialog> findByFromIDAndToID(Long fromID, Long toID);

    @Query(value = "insert into dialogs(id_from_user, id_to_user) values(?1, ?2)", nativeQuery = true)
    public Optional<Dialog> saveDialog(Long fromID, Long toID);
}

