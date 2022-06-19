package com.devjck.springboard.domain.report.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = "report")
@AllArgsConstructor
@NoArgsConstructor
public class Report {
    @Id
    private int reportSeq;

    @Column
    private String reportContents;

    @Column
    private int reportUserId;

    @Column
    private int reportingUserId;

    @Column
    private int reportedUserId;

    @Column
    private String reportDate;

    @Column
    private int reportStatus;
}
