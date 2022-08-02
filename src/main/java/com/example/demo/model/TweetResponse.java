package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigInteger;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TweetResponse {

    @JsonSerialize(using = ToStringSerializer.class)
    BigInteger id;
    String tweetText;
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date createdDate;

}
