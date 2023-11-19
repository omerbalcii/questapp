package com.project.questapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.questapp.Repository.ICommentRepository;
import com.project.questapp.entities.Comment;
import com.project.questapp.entities.Post;
import com.project.questapp.entities.User;
import com.project.questapp.requests.CommentCreateRequest;
import com.project.questapp.requests.CommentUpdateRequest;

@Service
public class CommentService {

	private ICommentRepository commentRepository;
	private UserService userService;
	private PostServices postServices;
	
	public CommentService(ICommentRepository commentRepository,UserService userService,PostServices postServices)
	{
		this.commentRepository=commentRepository;
		this.userService=userService;
		this.postServices=postServices;
	}

	public List<Comment> getAllCommentsWithParam(Optional<Long> userId, Optional<Long> postId) 
	{
	if(userId.isPresent() && postId.isPresent()) {
		return commentRepository.findByUserIdAndPostId(userId.get(),postId.get());
		}
	else if (userId.isPresent()) {
		return commentRepository.findByUserId(userId.get());
		
	}else if(postId.isPresent()) {
		return commentRepository.findByPostId(postId.get());
	}else {
		return commentRepository.findAll();
	}
	
		
	}

	public Comment getOneCommentById(Long commentId) {
		
		return commentRepository.findById(commentId).orElse(null);
	}

	public Comment createOneComment(CommentCreateRequest request) {
		User user = userService.getOneUserById(request.getUserId());
		Post post = postServices.getOnePostById(request.getPostId());
		if(user != null && post != null) {
			Comment commentToSave = new Comment();
			commentToSave.setId(request.getId());
			commentToSave.setPost(post);
			commentToSave.setUser(user);
			commentToSave.setText(request.getText());
			return commentRepository.save(commentToSave);
		}else		
			return null;
	}

	public Comment updateOneCommentById(Long commentId, CommentUpdateRequest request) {
		Optional<Comment> comment = commentRepository.findById(commentId);
		if(comment.isPresent()) {
			Comment commentToUpdate = comment.get();
			commentToUpdate.setText(request.getText());
			return commentRepository.save(commentToUpdate);
		}else
			return null;
	}

	public void deleteOneCommentById(Long commentId) {
		
		commentRepository.deleteById(commentId);
		
	}
}
