package org.maxym.spring.repository;

import org.maxym.spring.model.Item;
import org.maxym.spring.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer>{

    List<Item> findByItemName(String name);

    List<Item> findByOwner(Person owner);
}
