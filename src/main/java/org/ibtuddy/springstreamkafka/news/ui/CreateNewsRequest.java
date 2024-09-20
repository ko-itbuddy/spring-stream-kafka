package org.ibtuddy.springstreamkafka.news.ui;

import jakarta.validation.constraints.NotBlank;

public record CreateNewsRequest(@NotBlank String source, @NotBlank String title) {
}
