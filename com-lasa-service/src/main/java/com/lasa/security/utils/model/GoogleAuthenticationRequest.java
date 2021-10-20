package com.lasa.security.utils.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Data
@NoArgsConstructor
@ApiIgnore
public class GoogleAuthenticationRequest {
    private String token;
    private String name;
    private String avatarUrl;
    private String mssv;
    private String meetUrl;
    private String majorId;
    private List<Integer> topics;
}
