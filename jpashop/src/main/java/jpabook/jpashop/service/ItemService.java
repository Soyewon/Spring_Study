package jpabook.jpashop.service;

import jpabook.jpashop.domain.Item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    /**
     * 이 코드는 거의 ItemRepo에 위임만 해주는 수준의 코드이기 때문에,
     * 이게 진짜 필요한 코드인가? 에 대해서 생각해 볼 필요가 있음 !
     * => repo에 직접 접근하여 해결해도 되기 때문
     */
    private final ItemRepository itemRepository;

    @Transactional // readOnly면 저장이 안되기 때문에
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
