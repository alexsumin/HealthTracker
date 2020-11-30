package ru.alexsumin.healthtracker.picgenerator.api;


import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChartRequest {
    @NotEmpty
    List<DateValuePair> values;
    String title;
}
