package org.example.models;

import java.util.List;

public record PetUpdateModel(long id, String name, String status, List<String> photoUrls) {}
