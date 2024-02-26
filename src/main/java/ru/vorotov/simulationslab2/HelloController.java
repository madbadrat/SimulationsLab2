package ru.vorotov.simulationslab2;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class HelloController {
    @FXML
    private TextField dollarTextField;
    @FXML
    private TextField euroTextField;
    @FXML
    private TextField daysField;
    @FXML
    private LineChart<Integer, Double> chart;

    private final double k = 0.02;
    Random random = new Random();

    double dollarPrice, euroPrice;
    int days;

    int counter;
    boolean isRunning;

    public void onCalcButtonClick(ActionEvent actionEvent) {
        isRunning = true;
        chart.getData().clear();
        // инициализация графика
        XYChart.Series<Integer, Double> dollarSeries = new XYChart.Series<>();
        XYChart.Series<Integer, Double> euroSeries = new XYChart.Series<>();
        dollarSeries.setName("Dollar");
        euroSeries.setName("Euro");
        chart.getData().add(dollarSeries);
        chart.getData().add(euroSeries);

        dollarPrice = Double.parseDouble(dollarTextField.getText());
        euroPrice = Double.parseDouble(euroTextField.getText());
        days = Integer.parseInt(daysField.getText());
        dollarSeries.getData().add(new XYChart.Data<>(0, dollarPrice));
        euroSeries.getData().add(new XYChart.Data<>(0, euroPrice));
        counter = 0;

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                dollarPrice = dollarPrice * (1 + k * (random.nextDouble() - 0.5));
                euroPrice = euroPrice * (1 + k * (random.nextDouble() - 0.5));
                Platform.runLater(() -> dollarSeries.getData().add(new XYChart.Data<>(counter, dollarPrice)));
                Platform.runLater(() -> euroSeries.getData().add(new XYChart.Data<>(counter, euroPrice)));
                counter++;
                if (!isRunning) {
                    timer.cancel();
                }
            }
        };

        timer.scheduleAtFixedRate(task, 0, 100);
    }

    public void onStopButtonClick(ActionEvent actionEvent) {
        isRunning=false;
    }
}