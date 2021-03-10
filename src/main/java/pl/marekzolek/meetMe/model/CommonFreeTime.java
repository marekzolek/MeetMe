package pl.marekzolek.meetMe.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonFreeTime {

    @NotNull(message = "Start is required")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime start;

    @NotNull(message = "End is required")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime end;
}
