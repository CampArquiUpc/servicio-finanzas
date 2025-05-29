package com.ponteBarbon.servicio_finanzas.finances.infrastructure.google.vertexIa;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ponteBarbon.servicio_finanzas.finances.application.outBoundedService.googleVertexIa.GoogleVertexIaService;
import org.springframework.stereotype.Service;

@Service
public abstract class GoogleVertexIaServiceImpl implements GoogleVertexIaService {

    private static final Gson gson = new Gson();

    private final String project = "pontebarbon";
    private final String location = "global";
    private final String model = "gemini-2.5-flash-preview-05-20";


    private final String prompt = """
                Extrae las siguientes entidades de la frase: acción, tipo de gasto, medio de pago, monto, moneda.
                Frase: "agregar gastos de cine pagado con yape monto 50 soles"
                Resultado:
                """;




}
