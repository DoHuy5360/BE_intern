package com.example.demo.kit.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DiscordLogger {
    private static HttpHeaders headers = new HttpHeaders();
    private static RestTemplate restTemplate = new RestTemplate();
    public String webhookId;
    public String webhookToken;
    private static String url;
    private static String requestBody;
    private static HttpEntity<String> entity;
    public String channelId = "1095224215076077650";

    public void send(String finalContent) {
        requestBody = "{\"content\": \"" + "```" + finalContent + "```" + "\" }";
        headers.setContentType(MediaType.APPLICATION_JSON);
        entity = new HttpEntity<>(requestBody, headers);
        url = String.format("https://discord.com/api/v10/webhooks/%s/%s?channel_id=%s&wait=true", webhookId,
                webhookToken,
                channelId);

        restTemplate.postForEntity(url, entity, String.class);
    }

    public void no0Send(String who, String content) {
        this.webhookId = "1095224498556510258";
        this.webhookToken = "DJXaInfYDEijijdfPKx30rNv5xlX4mTCvQ501W_ALb6fKI59I7t16DraTl7kMN6BE1OL";

        String finalContent = String.format("%s | %s | %s", Time.getCurrentTime(), who, content);
        send(finalContent);
    }

    public void no1Send(String who, String content) {
        this.webhookId = "1096046452515541024";
        this.webhookToken = "fGaTlrpOITpAZ-R6u4xJRRsPjDJeA3BlrC0hS0kvEFGRqELmevqG5hhmKOG7QtFhXZXh";

        String finalContent = String.format("%s | %s | %s", Time.getCurrentTime(), who, content);
        send(finalContent);
    }

    public void no3Send(String content) {
        this.webhookId = "1098460400653181008";
        this.webhookToken = "TsCbeP99sjSDDOlMRauSR_jV71eqgNrySTWRgCn2CgIL-EADRv5qpBBeQbzpJ285u-ve";

        String finalContent = String.format("%s | %s", Time.getCurrentTime(), content);
        send(finalContent);
    }

}
