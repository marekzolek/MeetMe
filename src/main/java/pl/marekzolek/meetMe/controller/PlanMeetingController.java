package pl.marekzolek.meetMe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import pl.marekzolek.meetMe.request.SuggestionsRequest;
import pl.marekzolek.meetMe.response.SuggestionsResponse;
import pl.marekzolek.meetMe.service.MeetMeService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/meetMe")
public class PlanMeetingController {

    private final MeetMeService meetMeService;

    @GetMapping
    public SuggestionsResponse getMeetingSuggestion(@Valid @RequestBody SuggestionsRequest suggestionsRequest) {
        return meetMeService.getTimeSuggestions(suggestionsRequest);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
