package com.someshop.intershop.repository;

import com.someshop.intershop.model.Announcement;
import com.someshop.intershop.model.Comment;
import com.someshop.intershop.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "select * from comment where announcement_id = ?1 order by date * -1", nativeQuery = true)
    List<Comment> findAllByAnnouncement(Announcement announcement);

    @Query(value = "select * from comment where shop_id = ?1 order by date * -1", nativeQuery = true)
    List<Comment> findAllByShop(Shop shop);
}
