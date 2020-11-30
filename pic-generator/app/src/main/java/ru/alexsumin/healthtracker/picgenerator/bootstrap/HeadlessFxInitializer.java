package ru.alexsumin.healthtracker.picgenerator.bootstrap;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import ru.alexsumin.healthtracker.picgenerator.fx.ChartGenerator;

import java.util.concurrent.Executors;

public class HeadlessFxInitializer implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        ChartGenerator.initialize(Executors.newSingleThreadExecutor());
    }
}
