package com.test.core.controllers;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@JacksonXmlRootElement(
        localName = "Products"
)
@RequiredArgsConstructor
@Getter
public class Product {
    private final int id;
    private final  String name;
    private final String desc;

}
