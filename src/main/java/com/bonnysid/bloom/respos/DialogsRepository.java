package com.bonnysid.bloom.respos;

import com.bonnysid.bloom.model.Dialog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DialogsRepository extends JpaRepository<Dialog, Long> {

    @Query(value = "select * from dialogs where id_from_user = ?1", nativeQuery = true)
    public Optional<List<Dialog>> getAllByIdFromUser(Long id);
}
