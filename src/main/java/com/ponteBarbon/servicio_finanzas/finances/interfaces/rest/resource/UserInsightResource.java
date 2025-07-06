package com.ponteBarbon.servicio_finanzas.finances.interfaces.rest.resource;

public class UserInsightResource {
    private Long id;
    private Long userId;
    private String insightData;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getInsightData() { return insightData; }
    public void setInsightData(String insightData) { this.insightData = insightData; }
}

