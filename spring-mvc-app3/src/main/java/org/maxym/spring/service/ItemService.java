package org.maxym.spring.service;

import org.maxym.spring.model.Item;
import org.maxym.spring.model.Person;
import org.maxym.spring.repository.ItemRepository;
import org.maxym.spring.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Period;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;
    private final PersonRepository personRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository, PersonRepository personRepository) {
        this.itemRepository = itemRepository;
        this.personRepository = personRepository;
    }

    public List<Item> findByItemName(String name) {
        return itemRepository.findByItemName(name);
    }

    public List<Item> findByOwner(Person owner) {
        return itemRepository.findByOwner(owner);
    }
}
