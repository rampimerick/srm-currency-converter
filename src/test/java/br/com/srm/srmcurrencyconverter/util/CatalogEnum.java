package br.com.srm.srmcurrencyconverter.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.stream.Collectors;

public enum CatalogEnum {

    ORDER_REQUEST_NEWORDER,
    KINGDOM_REQUEST_NEWKINGDOM,
    CURRENCY_REQUEST_NEWCURRENCYRATE,
    PRODUCT_REQUEST_NEWPRODUCT,
    PRODUCTKINGDOM_REQUEST_NEWPRODUCTKINGDOM;


    public String getFileAsString() throws FileNotFoundException {
        final String[] segments = name().toLowerCase().split("_");
        final StringBuilder path = new StringBuilder("catalogs/");
        for (String seg: segments) {
            path.append(seg).append("/");
        }
        path.setLength(path.length() - 1);
        path.append(".json");
        final ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        final InputStream inputStream = classloader.getResourceAsStream(path.toString());
        if (Objects.isNull(inputStream)) {
            throw new FileNotFoundException(path.toString());
        }
        return new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
    }
}
