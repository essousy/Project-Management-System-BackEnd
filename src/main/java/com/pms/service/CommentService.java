package com.pms.service;

import com.pms.model.Comment;

import java.util.List;

public interface CommentService {

    Comment createComment(Long issueId, Long userId, String content) throws Exception;


    void deleteComment(Long CommentId, Long userId) throws Exception;

    List<Comment> findCommentByIssueId(Long issueId);
}
