# MeetMe
> Recruitment exercise

## JSON input example

{
    "duration": "00:30",
    "calendars": [
        {
            "workingHours": {
                "start": "09:00",
                "end": "19:55"
            },
            "plannedMeeting": [
                {
                    "start": "09:00",
                    "end": "10:30"
                },
                {
                    "start": "12:00",
                    "end": "13:00"
                },
                {
                    "start": "16:00",
                    "end": "18:00"
                }
            ]
        },
        {
            "workingHours": {
                "start": "10:00",
                "end": "18:30"
            },
            "plannedMeeting": [
                {
                    "start": "10:00",
                    "end": "11:30"
                },
                {
                    "start": "12:30",
                    "end": "14:30"
                },
                {
                    "start": "14:30",
                    "end": "15:00"
                },
                {
                    "start": "16:00",
                    "end": "17:00"
                }
            ]
        }
    ]
}

## Expected JSON response example

{
    "freeTimes": [
        {
            "start": "11:30",
            "end": "12:00"
        },
        {
            "start": "15:00",
            "end": "16:00"
        },
        {
            "start": "18:00",
            "end": "18:30"
        }
    ]
}
 
## Informations

Application run on port 8080, before run meke sure port is not busy or change port in application.properties with a comand:

server.port=8081
