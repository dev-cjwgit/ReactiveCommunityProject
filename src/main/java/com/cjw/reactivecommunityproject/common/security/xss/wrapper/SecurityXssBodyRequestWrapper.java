package com.cjw.reactivecommunityproject.common.security.xss.wrapper;

import com.cjw.reactivecommunityproject.common.security.xss.utils.SecurityXssSanitizer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class SecurityXssBodyRequestWrapper extends HttpServletRequestWrapper {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private final byte[] cached;

    public SecurityXssBodyRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        try (InputStream is = request.getInputStream()) {
            byte[] raw = is.readAllBytes();
            if (raw.length == 0) {
                this.cached = raw;
                return;
            }

            String ct = request.getContentType();
            if (ct != null && ct.toLowerCase().contains("application/json")) {
                JsonNode node = MAPPER.readTree(raw);
                JsonNode cleaned = sanitizeNode(node);
                this.cached = MAPPER.writeValueAsBytes(cleaned);
            } else {
                this.cached = raw; // JSON이 아니면 그대로
            }
        }
    }

    private JsonNode sanitizeNode(JsonNode node) {
        if (node == null) {
            return NullNode.getInstance();
        }
        if (node.isTextual()) {
            return TextNode.valueOf(SecurityXssSanitizer.clean(node.textValue()));
        } else if (node.isObject()) {
            ObjectNode obj = (ObjectNode) node;
            obj.fieldNames().forEachRemaining(f -> obj.set(f, sanitizeNode(obj.get(f))));
            return obj;
        } else if (node.isArray()) {
            ArrayNode arr = (ArrayNode) node;
            for (int i = 0; i < arr.size(); i++) arr.set(i, sanitizeNode(arr.get(i)));
            return arr;
        }
        return node;
    }

    @Override
    public ServletInputStream getInputStream() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(cached);
        return new ServletInputStream() {
            @Override
            public int read() {
                return inputStream.read();
            }

            @Override
            public boolean isFinished() {
                return inputStream.available() == 0;
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setReadListener(ReadListener readListener) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        };
    }

    @Override
    public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(getInputStream(), StandardCharsets.UTF_8));
    }

    @Override
    public int getContentLength() {
        return cached.length;
    }

    @Override
    public long getContentLengthLong() {
        return cached.length;
    }
}