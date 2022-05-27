package ru.liga.application.api;

public interface PdfGeneratorService<T> {
    byte[] generatePdf(T t);
}
