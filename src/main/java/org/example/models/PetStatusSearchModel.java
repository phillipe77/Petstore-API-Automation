package org.example.models;

import java.util.List;

public record PetStatusSearchModel(
        long id,
        Category category,
        String name,
        List<String> photoUrls,
        List<Tag> tags,
        String status
) {
    public record Category(long id, String name) {}
    public record Tag(long id, String name) {}
}
