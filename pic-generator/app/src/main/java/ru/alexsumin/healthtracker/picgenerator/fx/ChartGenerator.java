package ru.alexsumin.healthtracker.picgenerator.fx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import ru.alexsumin.healthtracker.picgenerator.api.DateValuePair;
import ru.alexsumin.healthtracker.picgenerator.util.ChartHelperUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

import static ru.alexsumin.healthtracker.picgenerator.model.FxConstants.FORMAT;
import static ru.alexsumin.healthtracker.picgenerator.model.FxConstants.STYLESHEET;

@Slf4j
public class ChartGenerator extends Application {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM");
    private volatile static boolean isJavaFxLaunched = Boolean.FALSE;
    @SuppressWarnings("unused")
    private static Canvas canvas;


    public static void initialize(ExecutorService taskExecutor) {
        taskExecutor.execute(() -> launch(ChartGenerator.class));
    }

    public static synchronized byte[] generateChart(List<DateValuePair> list, String title) {
        var result = new CompletableFuture<byte[]>();
        ChartSnapshotRunnable chartSnapshotRunnable = new ChartSnapshotRunnable(list, result, title);
        if (!isJavaFxLaunched) {
            Platform.startup(chartSnapshotRunnable);
            isJavaFxLaunched = Boolean.TRUE;
        } else
            Platform.runLater(chartSnapshotRunnable);

        try {
            return result.get();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("Something went wrong");
        }
    }

    @Override
    public void start(Stage stage) {
        canvas = new Canvas();
    }

    static class ChartSnapshotRunnable implements Runnable {

        List<DateValuePair> pairs;
        CompletableFuture<byte[]> result;
        String title;

        public ChartSnapshotRunnable(List<DateValuePair> pairs, CompletableFuture<byte[]> result, String title) {
            this.pairs = pairs;
            this.result = result;
            this.title = title;
        }

        @Override
        public void run() {

            try (var outputStream = new ByteArrayOutputStream()) {

                List<BigDecimal> collect = pairs
                        .stream()
                        .map(DateValuePair::getValue)
                        .collect(Collectors.toList());

                double minY = ChartHelperUtil.findMin(collect);
                double maxY = ChartHelperUtil.findMax(collect);
                double tick = ChartHelperUtil.calculateTick(minY, maxY);

                var chart = new CurvedFittedAreaChart(
                        new CategoryAxis(),
                        new NumberAxis(
                                minY, maxY, tick
                        ));

                final XYChart.Series<String, Number> series = new XYChart.Series<>();
                pairs.forEach(pair -> series.getData().add(new XYChart.Data<>(formatter.format(pair.getDate()), pair.getValue())));

                chart.getData().add(series);
                chart.setTitle(title);

                var snapshotScene = new Scene(chart);
                snapshotScene.getStylesheets().add(STYLESHEET);
                var snapshotParameters = new SnapshotParameters();
                snapshotParameters.setFill(Color.TRANSPARENT);
                WritableImage image = chart.snapshot(snapshotParameters, null);
                BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
                ImageIO.write(bImage, FORMAT, outputStream);

                result.complete(outputStream.toByteArray());
            } catch (Exception e) {
                result.completeExceptionally(new RuntimeException("Something went wrong"));
            }
        }
    }
}
