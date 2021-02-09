package com.galvanize.guestbook.Service;

import com.galvanize.guestbook.Model.GuestBook;
import com.galvanize.guestbook.Repository.GuestBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestBookService {

    @Autowired
    private GuestBookRepository repository;

    public List<GuestBook> getAllEntries() {
        return repository.findAll();
    }

    public GuestBook addEntry(GuestBook book) {
        return repository.save(book);
    }
}
