package com.ponteBarbon.servicio_finanzas.finances.interfaces.rest.resource;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Recurso para usuario (User)")
public class UserResource {
    @Schema(description = "ID del usuario", example = "1")
    private Long id;
    @Schema(description = "Nombre del usuario", example = "Juan Perez")
    private String name;
    @Schema(description = "Email del usuario", example = "juan@mail.com")
    private String email;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}

