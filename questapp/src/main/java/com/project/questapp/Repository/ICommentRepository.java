package com.project.questapp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.questapp.entities.Comment;

public interface ICommentRepository extends JpaRepository<Comment, Long> {

	List<Comment> findByUserIdAndPostId(Long userId,Long postId);
	List<Comment> findByUserId(Long user);
	List<Comment> findByPostId(Long postId);

}
