package th.ac.ku.viewbackend.model;

import com.google.cloud.Timestamp;

public class Report {
    private String reportId;
    private String reportContent;
    private String atcId;
    private String reportBy;
    private String commentId;
    private Timestamp reportDate;

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getReportContent() {
        return reportContent;
    }

    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }

    public String getAtcId() {
        return atcId;
    }

    public void setAtcId(String atcId) {
        this.atcId = atcId;
    }

    public String getReportBy() {
        return reportBy;
    }

    public void setReportBy(String reportBy) {
        this.reportBy = reportBy;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public Timestamp getReportDate() {
        return reportDate;
    }

    public void setReportDate(Timestamp reportDate) {
        this.reportDate = reportDate;
    }
}