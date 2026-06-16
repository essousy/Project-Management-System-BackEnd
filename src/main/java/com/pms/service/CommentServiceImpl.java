package com.pms.service;

import com.pms.model.Comment;
import com.pms.model.Issue;
import com.pms.model.User;
import com.pms.repository.CommentRepository;
import com.pms.repository.IssueRepository;
import com.pms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public Comment createComment(Long issueId, Long userId, String content)
    throws Exception {

        Optional<Issue> issueOptional = issueRepository.findById(issueId);
        Optional<User> userOptional = userRepository.findById(userId);

        if(issueOptional.isEmpty()){
            throw new Exception("issue not found with id"+issueId);
        }

        if(userOptional.isEmpty()){
            throw new Exception("User not found with id"+issueId);
        }

        Issue issue = issueOptional.get();
        User user = userOptional.get();

        Comment comment = new Comment();

        comment.setIssue(issue);
        comment.setUser(user);
        comment.setCreatedDateTime(LocalDateTime.now());
        comment.setContent(content);

        Comment savedComment = commentRepository.save(comment);

        issue.getComments().add(savedComment);

        return savedComment;

    }

    @Override
    public void deleteComment(Long CommentId, Long userId) throws Exception {
        Optional<Comment> commentOptional = commentRepository.findById(CommentId);
        Optional<User> userOptional = userRepository.findById(userId);

        if(commentOptional.isEmpty()){
            throw new Exception("comment not found with id"+CommentId);
        }
        if(userOptional.isEmpty()){
            throw new Exception("user not found with id"+CommentId);
        }

        Comment comment = commentOptional.get();
        User user = userOptional.get();

        if(comment.getUser().equals(user)){
            commentRepository.delete(comment);
        }else{
            throw new Exception("User does not have permission to delete this comment!");
        }

    }


    @Override
    public List<Comment> findCommentByIssueId(Long issueId){

        return commentRepository.findCommentByIssueId(issueId);

    }


}
