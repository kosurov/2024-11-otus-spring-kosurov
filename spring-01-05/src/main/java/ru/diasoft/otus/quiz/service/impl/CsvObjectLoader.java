package ru.diasoft.otus.quiz.service.impl;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import ru.diasoft.otus.quiz.service.ObjectLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

@Service
public class CsvObjectLoader implements ObjectLoader {

    private static final Logger log = LoggerFactory.getLogger(CsvObjectLoader.class);

    @Override
    public <T> List<T> loadObjectList(Class<T> type, String fileName) {
        try {
            ObjectReader objectReader = getCsvObjectReader(type);
            InputStream inputStream = new ClassPathResource(fileName).getInputStream();
            return readValues(objectReader, inputStream);
        } catch (Exception e) {
            log.error("Error occurred while loading object list from file {}", fileName, e);
            return Collections.emptyList();
        }
    }

    private <T> ObjectReader getCsvObjectReader(Class<T> type) {
        CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
        CsvMapper mapper = new CsvMapper();
        return mapper.readerFor(type).with(bootstrapSchema);
    }

    private <T> List<T> readValues(ObjectReader objectReader, InputStream inputStream) throws IOException {
        try (MappingIterator<T> readValues = objectReader.readValues(inputStream)) {
            return readValues.readAll();
        }
    }
}
