package pl.marekzolek.meetMe.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.marekzolek.meetMe.model.Calendar;
import pl.marekzolek.meetMe.model.CommonFreeTime;
import pl.marekzolek.meetMe.model.Meeting;
import pl.marekzolek.meetMe.model.WorkingHours;
import pl.marekzolek.meetMe.request.SuggestionsRequest;
import pl.marekzolek.meetMe.response.SuggestionsResponse;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeetMeService.class)
class MeetMeServiceTest {

    @Autowired
    private MeetMeService service;

    SuggestionsRequest request;
    Meeting firstMeetingForFirst;
    Meeting secondMeetingForFirst;
    Meeting thirdMeetingForFirst;
    Meeting firstMeetingForSecond;
    Meeting secondMeetingForSecond;
    Meeting thirdMeetingForSecond;
    Meeting fourthMeetingForSecond;
    List<Meeting> plannedMeetingsForFirst;
    List<Meeting> plannedMeetingsForSecond;
    WorkingHours firstWorkingHours;
    WorkingHours secondWorkingHours;
    Calendar firstCalendar;
    Calendar secondCalendar;

    SuggestionsResponse response;
    List<CommonFreeTime> freeTimes;
    CommonFreeTime firstCommonFreeTime;
    CommonFreeTime secondCommonFreeTime;
    CommonFreeTime thirdCommonFreeTime;

    @BeforeEach
    public void setUp() {
        firstMeetingForFirst = new Meeting();
        firstMeetingForFirst.setStart(LocalTime.of(9, 0));
        firstMeetingForFirst.setEnd(LocalTime.of(10, 30));
        secondMeetingForFirst = new Meeting();
        secondMeetingForFirst.setStart(LocalTime.of(12, 0));
        secondMeetingForFirst.setEnd(LocalTime.of(13, 0));
        thirdMeetingForFirst = new Meeting();
        thirdMeetingForFirst.setStart(LocalTime.of(16, 0));
        thirdMeetingForFirst.setEnd(LocalTime.of(18, 0));

        firstMeetingForSecond = new Meeting();
        firstMeetingForSecond.setStart(LocalTime.of(10, 0));
        firstMeetingForSecond.setEnd(LocalTime.of(11, 30));
        secondMeetingForSecond = new Meeting();
        secondMeetingForSecond.setStart(LocalTime.of(12, 30));
        secondMeetingForSecond.setEnd(LocalTime.of(14, 30));
        thirdMeetingForSecond = new Meeting();
        thirdMeetingForSecond.setStart(LocalTime.of(14, 30));
        thirdMeetingForSecond.setEnd(LocalTime.of(15, 0));
        fourthMeetingForSecond = new Meeting();
        fourthMeetingForSecond.setStart(LocalTime.of(16, 0));
        fourthMeetingForSecond.setEnd(LocalTime.of(17, 0));

        plannedMeetingsForFirst = new ArrayList<>();
        plannedMeetingsForFirst.add(firstMeetingForFirst);
        plannedMeetingsForFirst.add(secondMeetingForFirst);
        plannedMeetingsForFirst.add(thirdMeetingForFirst);

        plannedMeetingsForSecond = new ArrayList<>();
        plannedMeetingsForSecond.add(firstMeetingForSecond);
        plannedMeetingsForSecond.add(secondMeetingForSecond);
        plannedMeetingsForSecond.add(thirdMeetingForSecond);
        plannedMeetingsForSecond.add(fourthMeetingForSecond);

        firstWorkingHours = new WorkingHours();
        firstWorkingHours.setStart(LocalTime.of(9, 0));
        firstWorkingHours.setEnd(LocalTime.of(19, 55));

        secondWorkingHours = new WorkingHours();
        secondWorkingHours.setStart(LocalTime.of(10, 0));
        secondWorkingHours.setEnd(LocalTime.of(18, 30));

        firstCalendar = new Calendar();
        firstCalendar.setWorkingHours(firstWorkingHours);
        firstCalendar.getPlannedMeeting().addAll(plannedMeetingsForFirst);

        secondCalendar = new Calendar();
        secondCalendar.setWorkingHours(secondWorkingHours);
        secondCalendar.getPlannedMeeting().addAll(plannedMeetingsForSecond);

        request = new SuggestionsRequest();
        request.setDuration(LocalTime.of(0, 30));
        request.getCalendars().add(firstCalendar);
        request.getCalendars().add(secondCalendar);

        firstCommonFreeTime = new CommonFreeTime(LocalTime.of(11, 30), LocalTime.of(12, 0));
        secondCommonFreeTime = new CommonFreeTime(LocalTime.of(15, 0), LocalTime.of(16, 0));
        thirdCommonFreeTime = new CommonFreeTime(LocalTime.of(18, 0), LocalTime.of(18, 30));

        freeTimes = new ArrayList<>();
        freeTimes.add(firstCommonFreeTime);
        freeTimes.add(secondCommonFreeTime);
        freeTimes.add(thirdCommonFreeTime);

        response = new SuggestionsResponse();
        response.getFreeTimes().addAll(freeTimes);
    }

    @Test
    void getTimeSuggestions() {
        SuggestionsResponse responseTest = service.getTimeSuggestions(request);
        assertEquals(responseTest.getFreeTimes().get(0).getStart(), response.getFreeTimes().get(0).getStart());
        assertEquals(responseTest.getFreeTimes().get(0).getEnd(), response.getFreeTimes().get(0).getEnd());
        assertEquals(responseTest.getFreeTimes().get(1).getStart(), response.getFreeTimes().get(1).getStart());
        assertEquals(responseTest.getFreeTimes().get(1).getEnd(), response.getFreeTimes().get(1).getEnd());
        assertEquals(responseTest.getFreeTimes().get(2).getStart(), response.getFreeTimes().get(2).getStart());
        assertEquals(responseTest.getFreeTimes().get(2).getEnd(), response.getFreeTimes().get(2).getEnd());
    }
}