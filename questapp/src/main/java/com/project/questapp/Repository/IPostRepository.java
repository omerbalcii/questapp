package com.project.questapp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.questapp.entities.Post;

public interface IPostRepository extends JpaRepository<Post, Long> {

	List<Post> findByUserId(Long userId);

}
