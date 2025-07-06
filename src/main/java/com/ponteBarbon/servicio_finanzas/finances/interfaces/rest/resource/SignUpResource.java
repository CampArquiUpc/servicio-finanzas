package com.ponteBarbon.servicio_finanzas.finances.interfaces.rest.resource;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Recurso para registro de usuario (SignUp)")
public class SignUpResource {
    @Schema(description = "Nombre del usuario", example = "Juan Perez")
    private String name;
    @Schema(description = "Email del usuario", example = "juan@mail.com")
    private String email;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}

