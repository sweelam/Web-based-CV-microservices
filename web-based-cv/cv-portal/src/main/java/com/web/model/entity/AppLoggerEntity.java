package com.web.model.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "app_logger", schema = "sweprofile", catalog = "")
public class AppLoggerEntity {
    private long logId;
    private String requestPath;
    private String requestMethod;
    private String requestToken;
    private String requestHeader;

    @Id
    @Column(name = "log_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getLogId() {
        return logId;
    }

    public void setLogId(long logId) {
        this.logId = logId;
    }

    @Basic
    @Column(name = "request_path")
    public String getRequestPath() {
        return requestPath;
    }

    public void setRequestPath(String requestPath) {
        this.requestPath = requestPath;
    }

    @Basic
    @Column(name = "request_method")
    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    @Basic
    @Column(name = "request_token")
    public String getRequestToken() {
        return requestToken;
    }

    public void setRequestToken(String requestBody) {
        this.requestToken = requestBody;
    }

    @Basic
    @Column(name = "request_header")
    public String getRequestHeader() {
        return requestHeader;
    }

    public void setRequestHeader(String requestHeader) {
        this.requestHeader = requestHeader;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppLoggerEntity that = (AppLoggerEntity) o;
        return logId == that.logId &&
                Objects.equals(requestPath, that.requestPath) &&
                Objects.equals(requestMethod, that.requestMethod) &&
                Objects.equals(requestToken, that.requestToken) &&
                Objects.equals(requestHeader, that.requestHeader);
    }

    @Override
    public int hashCode() {

        return Objects.hash(logId, requestPath, requestMethod, requestToken, requestHeader);
    }
}
