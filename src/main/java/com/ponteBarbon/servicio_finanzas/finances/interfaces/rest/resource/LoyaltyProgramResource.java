package com.ponteBarbon.servicio_finanzas.finances.interfaces.rest.resource;

public class LoyaltyProgramResource {
    private Long id;
    private Long userId;
    private Integer points;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Integer getPoints() { return points; }
    public void setPoints(Integer points) { this.points = points; }
}

