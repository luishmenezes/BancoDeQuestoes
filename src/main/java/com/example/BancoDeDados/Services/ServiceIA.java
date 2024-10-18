package com.example.BancoDeDados.Services;


import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
@Service
public class ServiceIA {
    private String respostaDoGemini;

    @Value("${apikey}")
    private String apiKey;

    private static final String GEMINI_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=";

    public String enviarParaGemini(String textoPDF) {
        RestTemplate restTemplate = new RestTemplate();

        String prompt = "Extraia as questões do texto abaixo e formate-as no seguinte padrão JSON, mesmo que o texto original não esteja totalmente estruturado assim. O padrão é:\n" +
                "{\n" +
                "  \"cabecalho\": \"(Exemplo - 2023)\",\n" +
                "  \"enunciado\": \"Exemplo de enunciado de questão.\",\n" +
                "  \"alternativas\": [\n" +
                "    \"a) Alternativa A.\",\n" +
                "    \"b) Alternativa B.\",\n" +
                "    \"c) Alternativa C.\",\n" +
                "    \"d) Alternativa D.\",\n" +
                "    \"e) Alternativa E.\"\n" +
                "  ]\n" +
                "}\n" +
                "Certifique-se de que cada questão extraída tenha um campo \"cabecalho\", um campo \"enunciado\", e uma lista de \"alternativas\". Preencha o campo \"cabecalho\" com a fonte e o ano da questão entre parênteses, mesmo que não esteja claro no texto original, e organize as alternativas no formato solicitado.\n" + textoPDF;

        JSONObject requestPayload = new JSONObject();
        JSONObject content = new JSONObject();
        JSONObject part = new JSONObject();

        part.put("text", prompt);
        content.append("parts", part);
        requestPayload.append("contents", content);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<>(requestPayload.toString(), headers);

        try {
            String fullUrl = GEMINI_URL + apiKey;
            ResponseEntity<String> response = restTemplate.exchange(fullUrl, HttpMethod.POST, entity, String.class);

            JSONObject jsonResponse = new JSONObject(response.getBody());
            JSONArray candidates = jsonResponse.getJSONArray("candidates");
            if (candidates.length() > 0) {
                JSONObject contentObj = candidates.getJSONObject(0).getJSONObject("content");
                JSONArray parts = contentObj.getJSONArray("parts");
                if (parts.length() > 0) {
                    String extractedText = parts.getJSONObject(0).getString("text");
                    respostaDoGemini = extractedText;
                    System.out.println(respostaDoGemini);
                    return extractedText;
                }
            }
            return "Nenhum texto encontrado na resposta.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao acessar a API da Gemini: " + e.getMessage();
        }
    }

    public String getRespostaDoGemini() {
        return respostaDoGemini;
    }

}
