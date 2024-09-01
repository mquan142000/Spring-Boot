package com.example.springbootbookminitest.formatter;

import com.example.springbootbookminitest.model.Type;
import com.example.springbootbookminitest.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

@Component
public class TypeFormatter implements Formatter<Type> {
    private final ITypeService typeService;

    @Autowired
    public TypeFormatter(ITypeService typeService) {
        this.typeService = typeService;
    }

    @Override
    public Type parse(String text, Locale locale) throws ParseException {
        try {
            Long id = Long.parseLong(text);
            Optional<Type> typeOptional = typeService.findById(id);
            return typeOptional.orElseThrow(() -> new ParseException("Type not found for id: " + id, 0));
        } catch (NumberFormatException e) {
            throw new ParseException("Invalid id format: " + text, 0);
        }
    }

    @Override
    public String print(Type object, Locale locale) {
        if (object == null) {
            return "";
        }
        return String.format("[%d, %s]", object.getId(), object.getName());
    }
}
