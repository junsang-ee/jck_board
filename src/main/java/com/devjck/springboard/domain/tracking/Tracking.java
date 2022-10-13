package com.devjck.springboard.domain.tracking;

import com.devjck.springboard.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Optional;

@Data
@Entity(name="tracking")
@NoArgsConstructor
@AllArgsConstructor
public class Tracking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trackingId;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "requestUser", nullable = false)
    private User requestUser;

    private String httpMethodType;

    private String requestApiUri;
    @Lob
    private String requestHeader;

    private String requestBody;

    @Lob
    private String responseHeader;

    private String responseBody;

    private int resultCode;

    private String startTime;

    private String endTime;


    public Tracking(User requestUser, String httpMethodType, String requestApiUri, String requestHeader,
                    String requestBody, String responseHeader, String responseBody, int resultCode, String startTime, String endTime) {
        this.requestUser = requestUser;
        this.httpMethodType = httpMethodType;
        this.requestApiUri = requestApiUri;
        this.requestHeader = requestHeader;
        this.requestBody = requestBody;
        this.responseHeader = responseHeader;
        this.responseBody = responseBody;
        this.resultCode = resultCode;
        this.startTime = startTime;
        this.endTime = endTime;
    }


}
