package com.okeydokidoki.smartmirror.system;

import com.okeydokidoki.smartmirror.common.ApiResponse;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SystemController {

    @GetMapping("/health")
    public ApiResponse<Map<String, String>> health() {
        Map<String, String> status = new LinkedHashMap<>();
        status.put("status", "ok");
        status.put("service", "smart-mirror-api");
        return ApiResponse.ok(status);
    }
}
