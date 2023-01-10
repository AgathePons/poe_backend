package survey.backend.entities.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Objects;

public class BlankStringConverter {
  @Converter
  public class EmptyStringConverter
          implements AttributeConverter<String, String> {
    @Override
    public String convertToDatabaseColumn(String attribute) {
      return (Objects.nonNull(attribute) && !attribute.isEmpty())
              ? attribute : null;
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
      return dbData;
    }
  }
}
