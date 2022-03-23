package com.jaikalubai.jaikalubai;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


import java.util.List;

public interface ItemRepository extends MongoRepository<ContactItem, String> {

    @Query("{firstName:'?0'}")
    ContactItem findContactItemBy(String firstName);

    @Query(value="{category:'?0'}", fields="{'firstName' : 1, 'lastName' : 1}")
    List<ContactItem> findAll(String firstName);

    public long count();

}
