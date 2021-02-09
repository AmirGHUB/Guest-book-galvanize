package com.galvanize.guestbook.Controller;

import com.galvanize.guestbook.Model.GuestBook;
import com.galvanize.guestbook.Service.GuestBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GuestBookController {

    @Autowired
    private GuestBookService service;

    @GetMapping("/visitors")
    public List<GuestBook> getAllEntries(){
        return service.getAllEntries();
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public GuestBook addGuest(@RequestBody GuestBook book){
        return service.addEntry(book);
    }

}
