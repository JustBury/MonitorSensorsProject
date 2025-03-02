package by.bury.monitorsensors.controller;

import by.bury.monitorsensors.annotation.IsSensorExist;
import by.bury.monitorsensors.dto.SensorRequestDto;
import by.bury.monitorsensors.dto.SensorResponseDto;
import by.bury.monitorsensors.dto.SensorUpdateDto;
import by.bury.monitorsensors.service.SensorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "Sensor Controller", description = "API for working with sensor")
@RestController
@RequestMapping("/api/v1/sensor")
@Validated
public class SensorController {

    Logger logger = Logger.getLogger(SensorController.class.getName());

    private final SensorService sensorService;

    public SensorController(final SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Operation(summary = "Get all sensors", description = "Get all sensor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sensors were gotten successfully",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = SensorResponseDto.class))))
    })
    @GetMapping
    public ResponseEntity<List<SensorResponseDto>> getAllSensors() {
        logger.info("Get all sensors");
        List<SensorResponseDto> sensorResponseDtos = sensorService.getAllSensors();
        return new ResponseEntity<>(sensorResponseDtos, HttpStatus.OK);
    }

    @Operation(summary = "Get sensors by filter", description = "Get sensors by filtering by name and model")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sensors were gotten successfully",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = SensorResponseDto.class))))
    })
    @GetMapping("/filter")
    public ResponseEntity<List<SensorResponseDto>> getAllSensorsByFilter(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "model", required = false) String model) {
        logger.info("Get sensors by filter");
        List<SensorResponseDto> sensorResponseDtos = sensorService.getListSensorsByFilter(name, model);
        return new ResponseEntity<>(sensorResponseDtos, HttpStatus.OK);
    }


    @Operation(summary = "Get sensor by uuid", description = "Get sensor by uuid")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sensor was gotten successfully",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = SensorResponseDto.class)))),
            @ApiResponse(responseCode = "404", description = "Sensor doesn't exist")
    })
    @GetMapping(value = "/{uuid}")
    public ResponseEntity<SensorResponseDto> get(@PathVariable(name = "uuid") @IsSensorExist String uuid) {
        logger.info("Get sensor by uuid");
        SensorResponseDto sensorResponseDto = sensorService.getSensor(uuid);
        return new ResponseEntity<>(sensorResponseDto, HttpStatus.OK);
    }


    @Operation(summary = "Save new sensor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sensor was created successfully",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = SensorResponseDto.class)))),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<SensorResponseDto> save(@RequestBody @Valid SensorRequestDto sensorRequestDto) {
        SensorResponseDto sensorResponseDto = sensorService.saveSensor(sensorRequestDto);
        return new ResponseEntity<>(sensorResponseDto, HttpStatus.CREATED);
    }

    @Operation(summary = "Update Sensor", description = "Update Sensor by UUID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sensor was update successfully",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = SensorResponseDto.class)))),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "Sensor doesn't exist")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/{uuid}")
    public ResponseEntity<SensorResponseDto> update(@PathVariable(name = "uuid") @IsSensorExist String uuid,
                                                    @RequestBody @Valid SensorUpdateDto sensorUpdateDto) {
        SensorResponseDto sensorResponseDto = sensorService.updateSensor(uuid, sensorUpdateDto);
        return new ResponseEntity<>(sensorResponseDto, HttpStatus.CREATED);
    }

    @Operation(summary = "Delete existing sensor", description = "Delete sensor by uuid")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sensor was deleted successfully",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = SensorResponseDto.class)))),
            @ApiResponse(responseCode = "404", description = "Sensor doesn't exist")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/{uuid}")
    public ResponseEntity<SensorResponseDto> delete(@PathVariable(name = "uuid") @IsSensorExist String uuid) {
        sensorService.deleteSensor(uuid);
        return ResponseEntity.noContent().build();
    }
}
