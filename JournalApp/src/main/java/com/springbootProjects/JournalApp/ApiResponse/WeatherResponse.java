package com.springbootProjects.JournalApp.ApiResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherResponse {
    private Current current;


    @Getter
    @Setter
    public class Current {
        @JsonProperty("temp_c")
        private double TempC;

        @JsonProperty("temp_f")
        private double TempF;

        @JsonProperty("is_day")
        private int isDay;
    }


}



