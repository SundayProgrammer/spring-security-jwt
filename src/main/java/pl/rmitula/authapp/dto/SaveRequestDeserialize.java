package pl.rmitula.authapp.dto;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SaveRequestDeserialize extends StdDeserializer<SaveRectangleRequest> {

    public SaveRequestDeserialize() {
        this(null);
    }

    public SaveRequestDeserialize(Class<?> vc) {
        super(vc);
    }

    @Override
    public SaveRectangleRequest deserialize(JsonParser jsonParser, DeserializationContext context)
        throws IOException, JsonProcessingException {

        JsonNode jn = jsonParser.getCodec().readTree(jsonParser);
        SaveRectangleRequest request = new SaveRectangleRequest();
        request.setAddress(jn.get("address").textValue());
        request.setDescription(jn.get("description").textValue());
        request.setName(jn.get("name").textValue());
        List<PointSummary> pointsList = new ArrayList<>();
        Iterator<JsonNode> rawPoints  = jn.get("points").elements();
        rawPoints.forEachRemaining(jsonNode -> {
            pointsList.add(new PointSummary(
                    jsonNode.get("x").asDouble(),
                    jsonNode.get("y").asDouble()));
        });
        request.setPoints(pointsList);

        return request;
    }
}
