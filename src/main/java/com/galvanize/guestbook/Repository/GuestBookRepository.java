package com.galvanize.guestbook.Repository;

import com.galvanize.guestbook.Model.GuestBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestBookRepository extends JpaRepository<GuestBook, Long> {
}
