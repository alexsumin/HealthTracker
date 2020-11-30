package ru.alexsumin.healthtracker.core.service;

public interface PicGeneratorService {
    byte[] createChart(Long id);
}
