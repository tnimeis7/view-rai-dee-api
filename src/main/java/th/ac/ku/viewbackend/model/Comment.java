package th.ac.ku.viewbackend.model;

import com.google.cloud.Timestamp;

import java.util.Date;

public class Comment implements BlockComponents{
    private String id;
    private String articleId;
    private String commentContent;
    private Timestamp commentDate;

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Date getCommentDate() {
        if(commentDate==null){
            return null;
        }
        return commentDate.toDate();
    }

    public void setCommentDate() {
        Timestamp timestamp = Timestamp.now();
        this.commentDate =  timestamp;
    }


}
