package mj.childGrowth;

import org.springframework.amqp.core.Message;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MessageExtractor {
    private final Charset CHARACTER_SET = StandardCharsets.UTF_8;

    public List<String> extractAsList(Message message) {
        String body = new String(message.getBody(), CHARACTER_SET);
        body = body.replace("[", "");
        body = body.replace("]", "");
        body = body.replaceAll("\"", "");

        return new ArrayList<>(Arrays.asList(body.split(",")));
    }
}
