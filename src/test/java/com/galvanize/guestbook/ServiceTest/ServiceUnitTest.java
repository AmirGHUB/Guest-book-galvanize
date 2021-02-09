package com.galvanize.guestbook.ServiceTest;

import com.galvanize.guestbook.Model.GuestBook;
import com.galvanize.guestbook.Repository.GuestBookRepository;
import com.galvanize.guestbook.Service.GuestBookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServiceUnitTest {

    @Mock
    private GuestBookRepository repository;

    @InjectMocks
    private GuestBookService service;

    private List<GuestBook> entries;

    @BeforeEach
    public void init(){
        entries = new ArrayList<>();
        entries.addAll(List.of(new GuestBook("alaa","excellent"),
                new GuestBook("amir","good")));
    }

    @Test
    public void fetchAllEntries(){

        when(repository.findAll()).thenReturn(entries);

        List<GuestBook> actualResponse = service.getAllEntries();

        assertEquals(entries.size(), actualResponse.size());
        assertTrue(entries.equals(actualResponse));
    }

    @Test
    public void addNewEntry(){
        GuestBook guest1 = new GuestBook("sam","very nice");

        when(repository.save(any(GuestBook.class))).thenReturn(guest1);

        GuestBook actualResponse = service.addEntry(guest1);

        assertTrue(guest1.getName().equals(actualResponse.getName()));
        assertTrue(guest1.getComment().equals(actualResponse.getComment()));


    }
}
