package th.ac.ku.viewbackend.model;

public class StreamingPlatform implements BlockComponents {

    private String id; //PlatformName
    private String picPath;

    @Override
    public String getId() {
        return id;
    }

    public void setId(String platformName) {
        this.id = platformName;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }


}
