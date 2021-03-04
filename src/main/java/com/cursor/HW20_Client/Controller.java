package com.cursor.HW20_Client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/library/books")
public class Controller {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping
    public List<String> getAllAvailableBooks() {
        ResponseEntity<Object[]> entity = restTemplate.getForEntity("http://library/books", Object[].class);
        Object[] objects = entity.getBody();
        ObjectMapper mapper = new ObjectMapper();
        List<String> availableBooks = Arrays.stream(objects)
                .map(object -> mapper.convertValue(object, Book.class))
                .filter(r -> r.isAvailable())
                .map(Book::getTitle)
                .collect(Collectors.toList());
        return availableBooks;
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable("id") Long id) {
        return restTemplate.getForObject("http://library/books/" + id, Book.class);
    }
}
