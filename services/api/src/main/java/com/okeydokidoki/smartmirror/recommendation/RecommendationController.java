package com.okeydokidoki.smartmirror.recommendation;

import com.okeydokidoki.smartmirror.common.ApiResponse;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/recommendations")
public class RecommendationController {

    @PostMapping
    public ApiResponse<Map<String, Object>> recommend(
            @Valid @RequestBody RecommendationRequest request) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("userId", request.getUserId());
        result.put("items", Collections.emptyList());
        result.put("message", "推荐引擎尚未接入");
        return ApiResponse.ok(result);
    }

    public static class RecommendationRequest {
        @NotBlank
        private String userId;
        private String scene;
        private String style;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getScene() {
            return scene;
        }

        public void setScene(String scene) {
            this.scene = scene;
        }

        public String getStyle() {
            return style;
        }

        public void setStyle(String style) {
            this.style = style;
        }
    }
}
