package org.feuyeux.mqtt.service.controller;

import org.feuyeux.mqtt.service.controller.model.GetTempAverageResponse;
import org.feuyeux.mqtt.service.controller.model.GetTempCountResponse;
import org.feuyeux.mqtt.service.controller.model.GetTempsResponse;
import org.feuyeux.mqtt.model.TempStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for accessing temperature data.
 */
@RestController
public class TempStatsController {

    @Autowired
    private TempStatistics tempStats;

    @GetMapping(value = "/temps/stats",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTemps() {
        GetTempsResponse response = new GetTempsResponse();
        response.setCount(tempStats.getCount());
        response.setAverage(tempStats.getAverage());

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/temps/stats/count",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTempCount() {
        return ResponseEntity.ok(new GetTempCountResponse(tempStats.getCount()));
    }

    @GetMapping(value = "/temps/stats/avg",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAverageTemp() {
        return ResponseEntity.ok(new GetTempAverageResponse(tempStats.getAverage()));
    }
}
