package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    // item 저장
    public void save(Item item) {
        if(item.getId() == null) { // item은 jpa에 저장하기 전까지 id가 없음 => 넣을 때 신규로 등록됨
            em.persist(item);
        } else {
            em.merge(item); // update 비슷한 것
        }
    }

    // 단건 조회
    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    // 모두 조회
    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
