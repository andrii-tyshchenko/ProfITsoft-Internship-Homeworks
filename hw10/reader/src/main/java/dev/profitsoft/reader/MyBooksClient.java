package dev.profitsoft.reader;

import dev.profitsoft.hw10.dtos.BookBasicInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(value = "mybooksClient", url = "http://localhost:8080/")
public interface MyBooksClient {
    @GetMapping(value = "/books/myBooks")
    List<BookBasicInfo> getMyBooks(@RequestHeader("Authorization") String header);

    @GetMapping(value = "/books/myBooks/{id}/text")
    String getTextOfCertainBook(@PathVariable String id, @RequestHeader("Authorization") String header);
}