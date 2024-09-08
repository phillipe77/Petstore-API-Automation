package org.example.models;

public record PetOrderModel(long id, long petId, int quantity, String shipDate, String status, boolean complete) {}
