package com.example.model;

public class BMIRecord {
    private int id;
    private double height;
    private double weight;
    private double bmi;

    public BMIRecord(double height, double weight, double bmi) {
        this.height = height;
        this.weight = weight;
        this.bmi = bmi;
    }

    public int getId() {
        return id;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public double getBmi() {
        return bmi;
    }

    @Override
    public String toString() {
        return String.format("Altura: %.2f m, Peso: %.2f kg, IMC: %.2f", height, weight, bmi);
    }
}
