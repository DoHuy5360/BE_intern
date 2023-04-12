package com.example.demo.kit.encode;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncodeHandler {
    private BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
    public String enCodeScript;
    public String firstScript;

    public EncodeHandler enCode(String raw) {
        enCodeScript = bcrypt.encode(raw);
        return this;
    }

    public EncodeHandler setFirstSript(String raw) {
        firstScript = raw;
        return this;
    }

    public String getEnCodeScript() {
        return this.enCodeScript;
    }

    public EncodeHandler setRawSript(String raw) {
        this.enCodeScript = raw;
        return this;
    }

    public boolean isMath(String enCoded) {
        System.out.println(enCoded);
        System.out.println(enCodeScript);
        return bcrypt.matches(this.enCodeScript, enCoded);
    }

    public static void main(String[] arg) {
        String v1 = "a";
        String v2 = "a";

        EncodeHandler encodeHandler = new EncodeHandler();
        encodeHandler.enCode(v1);
        EncodeHandler encodeHandler2 = new EncodeHandler();
        encodeHandler2.enCode(v2);

        boolean de = encodeHandler.setRawSript(encodeHandler.getEnCodeScript())
                .isMath(encodeHandler2.getEnCodeScript());
        System.out.println(de);
    }
}
