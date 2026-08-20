package com.ahmed.backend.versionControllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/legacy/versions")
public class LegacyVersionApproachesController {

    // URL version

    //api/legacy/versions
    //api/legacy/versions/
    //api/legacy/versions/v1
    @GetMapping({"", "/v1", "/"})
    public ResponseEntity<String> defaultPathVersion() {
        return ResponseEntity.ok("Response from defaultPathVersion API 1.0.0");
    }

    //api/legacy/versions/v2
    @GetMapping("/v2")
    public ResponseEntity<String> PathVersion2() {
        return ResponseEntity.ok("Response from API Version 2.0.0");
    }

    // param version -> /api/legacy/versions?version=2
    @GetMapping(params = "version=2")
    public ResponseEntity<String> v2ReqParamVersion() {
        return ResponseEntity.ok("Response from v2ReqParamVersion API 2.0.0");
    }

    // headers -> /api/legacy/versions
    @GetMapping(headers = "X-API-Version=1")
    public ResponseEntity<String> defaultReqHeaderVersion() {
        return ResponseEntity.ok("Response from defaultReqHeaderVersion API 1.0.0");
    }

    // headers -> /api/legacy/versions
    @GetMapping(headers = "X-API-VERSION=2")
    public ResponseEntity<String> v2ReqHeaderVersion() {
        return ResponseEntity.ok("Response from v2ReqHeaderVersion API 2.0.0");
    }

    // mediaType -> produces because it's response

    // /api/legacy/versions
    // Accept : application/vnd.ahmed.v1+json (in postman)
    @GetMapping(produces = "application/vnd.ahmed.v1+json")
    public ResponseEntity<String> defaultMediaTypeVersion() {
        return ResponseEntity.ok("Response from defaultMediaTypeVersion API 1.0.0");
    }

    // /api/legacy/versions
    // Accept : application/vnd.ahmed.v2+json (in postman)
    @GetMapping(produces = "application/vnd.ahmed.v2+json")
    public ResponseEntity<String> v2MediaTypeVersion() {
        return ResponseEntity.ok("Response from v2MediaTypeVersion API 2.0.0");
    }

}
