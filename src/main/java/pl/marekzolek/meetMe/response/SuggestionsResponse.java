package pl.marekzolek.meetMe.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.marekzolek.meetMe.model.CommonFreeTime;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuggestionsResponse {
    List<CommonFreeTime> freeTimes = new ArrayList<>();
}
