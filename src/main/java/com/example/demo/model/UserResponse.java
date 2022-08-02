package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigInteger;
import java.util.Date;

@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    @JsonSerialize(using = ToStringSerializer.class)
    BigInteger tweetId;
    Long userId;
    String userName;
    String screenName;
    String location;
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date createdDate;
}
