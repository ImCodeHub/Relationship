package com.DTO.Relationship.Service.ServiceInterface;

import com.DTO.Relationship.Entity.Comment;
import com.DTO.Relationship.Model.CommentModel;

public interface CommentInterface {
    public Comment createCommentOnPost(Integer postId,CommentModel commentModel);
}
