package pl.marekzolek.meetMe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Calendar {

    @NotNull(message = "Working hours are required")
    private WorkingHours workingHours;

    @NotNull(message = "Planned meetings are required")
    private List<Meeting> plannedMeeting = new ArrayList<>();
}
