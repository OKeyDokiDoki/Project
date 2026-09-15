package com.okeydokidoki.smartmirror.tryon;

import com.okeydokidoki.smartmirror.common.ApiResponse;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/try-on")
public class TryOnController {

    @PostMapping("/sessions")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ApiResponse<Map<String, Object>> createSession(
            @Valid @RequestBody TryOnRequest request) {
        Map<String, Object> session = new LinkedHashMap<>();
        session.put("id", UUID.randomUUID().toString());
        session.put("userId", request.getUserId());
        session.put("productIds", request.getProductIds());
        session.put("status", "pending");
        return ApiResponse.ok(session);
    }

    public static class TryOnRequest {
        @NotBlank
        private String userId;
        @NotEmpty
        private List<String> productIds;
        private String sourceAssetUrl;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public List<String> getProductIds() {
            return productIds;
        }

        public void setProductIds(List<String> productIds) {
            this.productIds = productIds;
        }

        public String getSourceAssetUrl() {
            return sourceAssetUrl;
        }

        public void setSourceAssetUrl(String sourceAssetUrl) {
            this.sourceAssetUrl = sourceAssetUrl;
        }
    }
}
