package com.codecool.guest.book;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GuestBook implements HttpHandler {
    public void handle(HttpExchange httpExchange) throws IOException {

        GuestBookDAO dao = new GuestBookDAOFactory().getDao();
        String method = httpExchange.getRequestMethod();

        if (method.equals("POST")) {
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();
            Map<String, String> inputs = parseFormData(formData);
            dao.addEntry(new Entry(inputs.get("message"), inputs.get("name"), LocalDateTime.now()));
        }

        List<Entry> entries = dao.getAllEntries();

        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/guest_book.twig");

        JtwigModel model = JtwigModel.newModel();
        model.with("entries", entries);

        String response = template.render(model);
        byte[] responseBytes = response.getBytes();

        httpExchange.sendResponseHeaders(200, responseBytes.length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(responseBytes);
        os.close();
    }

    private Map<String, String> parseFormData(String formData) throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<>();
        String[] pairs = formData.split("&");
        for(String pair : pairs){
            String[] keyValue = pair.split("=");
            String value = URLDecoder.decode(keyValue[1], "UTF-8");
            map.put(keyValue[0], value);
        }
        return map;
    }
}
