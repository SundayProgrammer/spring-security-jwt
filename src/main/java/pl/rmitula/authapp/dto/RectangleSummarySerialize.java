package pl.rmitula.authapp.dto;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import pl.rmitula.authapp.model.Points;
import pl.rmitula.authapp.service.PointsService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RectangleSummarySerialize extends StdSerializer<RectangleSummary> {

    public RectangleSummarySerialize() {
        this(null);
    }

    public RectangleSummarySerialize(Class<RectangleSummary> t) {
        super(t);
    }

    @Override
    public void serialize(RectangleSummary rectangleSummary, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", rectangleSummary.getId());
        jsonGenerator.writeStringField("name", rectangleSummary.getName());
        jsonGenerator.writeStringField("address", rectangleSummary.getAddress());
        jsonGenerator.writeStringField("description", rectangleSummary.getDescription());
        jsonGenerator.writeArrayFieldStart("points");

        for (Points point : rectangleSummary.getPoints()) {
            double[] arr = {point.getX(), point.getY()};
            jsonGenerator.writeArray(arr, 0, 2);
        }

        jsonGenerator.writeEndArray();
        jsonGenerator.writeNumberField("userId", rectangleSummary.getUserId());
        jsonGenerator.writeEndObject();
    }
}
