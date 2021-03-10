package pl.marekzolek.meetMe.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.marekzolek.meetMe.model.Calendar;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuggestionsRequest {

    @NotNull(message = "Duration is required")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime duration;

    @NotNull(message = "Calendars are required")
    private List<Calendar> calendars = new ArrayList<>();
}
