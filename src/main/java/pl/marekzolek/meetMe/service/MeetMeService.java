package pl.marekzolek.meetMe.service;

import org.springframework.stereotype.Service;
import pl.marekzolek.meetMe.model.Calendar;
import pl.marekzolek.meetMe.model.CommonFreeTime;
import pl.marekzolek.meetMe.request.SuggestionsRequest;
import pl.marekzolek.meetMe.response.SuggestionsResponse;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MeetMeService {

    public SuggestionsResponse getTimeSuggestions(SuggestionsRequest suggestionsRequest) {

        SuggestionsResponse response = new SuggestionsResponse();

        long meetingDuration = Duration.between(LocalTime.of(0, 0), suggestionsRequest.getDuration()).getSeconds();
        List<LocalTime> firstCalendarTimes = new ArrayList<>();
        List<LocalTime> secondCalendarTimes = new ArrayList<>();

        List<LocalTime> firstFreeTimes = new ArrayList<>();
        List<LocalTime> secondFreeTimes = new ArrayList<>();

        List<LocalTime> commonFreeTimes = new ArrayList<>();

        Calendar firstCalendar = suggestionsRequest.getCalendars().get(0);
        Calendar secondCalendar = suggestionsRequest.getCalendars().get(1);

        fillCalendarTimes(firstCalendarTimes, firstCalendar);
        fillCalendarTimes(secondCalendarTimes, secondCalendar);

        getFreeTimes(meetingDuration, firstCalendarTimes, firstFreeTimes);
        getFreeTimes(meetingDuration, secondCalendarTimes, secondFreeTimes);


        getCommonFreeTime(firstFreeTimes, secondFreeTimes, commonFreeTimes);


        for (int i = 1; i < commonFreeTimes.size(); i = i + 2) {
            if (Duration.between(commonFreeTimes.get(i - 1), commonFreeTimes.get(i)).getSeconds() - meetingDuration >= 0) {
                response.getFreeTimes().add(new CommonFreeTime(commonFreeTimes.get(i - 1), commonFreeTimes.get(i)));
            }
        }

        return response;
    }

    private void getCommonFreeTime(List<LocalTime> firstFreeTimes, List<LocalTime> secondFreeTimes, List<LocalTime> commonFreeTimes) {
        long freeTimeSize = Math.min(firstFreeTimes.size(), secondFreeTimes.size());
        for (int i = 1; i < freeTimeSize; i = i + 2) {
            if (firstFreeTimes.get(i - 1).isAfter(secondFreeTimes.get(i - 1))
                    && firstFreeTimes.get(i - 1).isBefore(secondFreeTimes.get(i))) {

                commonFreeTimes.add(firstFreeTimes.get(i - 1));

            } else if (secondFreeTimes.get(i - 1).isAfter(firstFreeTimes.get(i - 1))
                    && secondFreeTimes.get(i - 1).isBefore(firstFreeTimes.get(i))) {

                commonFreeTimes.add(secondFreeTimes.get(i - 1));

            } else if (firstFreeTimes.get(i - 1).equals(secondFreeTimes.get(i - 1))) {
                commonFreeTimes.add(firstFreeTimes.get(i - 1));
            }
            if (firstFreeTimes.get(i).isAfter(secondFreeTimes.get(i - 1))
                    && firstFreeTimes.get(i).isBefore(secondFreeTimes.get(i))) {

                commonFreeTimes.add(firstFreeTimes.get(i));

            } else if (secondFreeTimes.get(i).isAfter(firstFreeTimes.get(i - 1))
                    && secondFreeTimes.get(i).isBefore(firstFreeTimes.get(i))) {

                commonFreeTimes.add(secondFreeTimes.get(i));

            } else if (firstFreeTimes.get(i).equals(secondFreeTimes.get(i))) {
                commonFreeTimes.add(firstFreeTimes.get(i));
            }
        }
    }

    private void getFreeTimes(long meetingDuration, List<LocalTime> calendarTimes, List<LocalTime> freeTimes) {
        for (int i = 1; i < calendarTimes.size(); i = i + 2) {
            long freeTime = Duration.between(calendarTimes.get(i - 1), calendarTimes.get(i)).getSeconds();
            if (freeTime >= meetingDuration) {
                freeTimes.add(calendarTimes.get(i - 1));
                freeTimes.add(calendarTimes.get(i));
            }
        }
    }

    private void fillCalendarTimes(List<LocalTime> calendarTimes, Calendar calendar) {
        calendarTimes.add(calendar.getWorkingHours().getStart());
        calendar.getPlannedMeeting().forEach(meeting -> {
            calendarTimes.add(meeting.getStart());
            calendarTimes.add(meeting.getEnd());
        });
        calendarTimes.add(calendar.getWorkingHours().getEnd());
    }
}
