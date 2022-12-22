package com.oraclecompany.bbanggle.domain.common.converter;

public interface AttributeConverter<X, Y> {
    
    Y convertToDatabaseColumn(X var1);
    
    X convertToEntityAttribute(Y var1);
}