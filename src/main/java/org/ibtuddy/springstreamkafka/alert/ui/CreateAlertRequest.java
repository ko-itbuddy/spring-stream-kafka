package org.ibtuddy.springstreamkafka.alert.ui;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record CreateAlertRequest(@Min(1) @Max(5) Integer level, @NotBlank String message) {
}
