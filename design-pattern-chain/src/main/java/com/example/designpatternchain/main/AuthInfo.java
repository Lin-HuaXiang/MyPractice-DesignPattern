package com.example.designpatternchain.main;

import lombok.Data;

@Data
public class AuthInfo {

    private String code;

    private String info = "";

    public AuthInfo(String code, String... infos) {
        this.code = code;

        for (String str : infos) {
            this.info = this.info.concat(str);
        }

    }
    
}
