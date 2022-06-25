package com.devjck.springboard.domain.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = "message")
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @Id
    private int messageSeq;

    @Column
    private String messageContents;

    @Column
    private int messageWriter;

    @Column
    private int messageSender;

    @Column
    private int messageReadStatus;
}
