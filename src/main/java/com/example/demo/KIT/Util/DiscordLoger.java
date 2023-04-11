package com.example.demo.kit.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class DiscordLoger {
    private static HttpHeaders headers = new HttpHeaders();
    private static RestTemplate restTemplate = new RestTemplate();
    public String webhookId;
    public String channelId;
    public String webhookToken;
    private static String url;
    private static String requestBody;
    private static HttpEntity<String> entity;

    public DiscordLoger() {
        this.webhookId = "1095224498556510258";
        this.webhookToken = "DJXaInfYDEijijdfPKx30rNv5xlX4mTCvQ501W_ALb6fKI59I7t16DraTl7kMN6BE1OL";
        this.channelId = "1095224215076077650";
    }

    public DiscordLoger(String channelId) {
        this.channelId = channelId;
    }

    public DiscordLoger(String webhookId, String webhookToken, String channelId) {
        this.webhookId = webhookId;
        this.webhookToken = webhookToken;
        this.channelId = channelId;

    }

    public DiscordLoger prepareContent(String content) {
        requestBody = "{\"content\": \"" + content + "\" }";
        headers.setContentType(MediaType.APPLICATION_JSON);
        entity = new HttpEntity<>(requestBody, headers);
        return this;
    }

    public void send() {
        url = String.format("https://discord.com/api/v10/webhooks/%s/%s?channel_id=%s&wait=true", webhookId,
                webhookToken,
                channelId);

        restTemplate.postForEntity(url, entity, String.class);
    }
}
