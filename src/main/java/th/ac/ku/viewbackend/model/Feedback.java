package th.ac.ku.viewbackend.model;

public class Feedback implements BlockComponents {

    private String id;
    private String fbContent;
    private String fbBy;
    private String fbStatus;

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFbContent() {
        return fbContent;
    }

    public void setFbContent(String fbContent) {
        this.fbContent = fbContent;
    }

    public String getFbBy() {
        return fbBy;
    }

    public void setFbBy(String fbBy) {
        this.fbBy = fbBy;
    }

    public String getFbStatus() {
        return fbStatus;
    }

    public void setFbStatus(String fbStatus) {
        this.fbStatus = fbStatus;
    }


}
