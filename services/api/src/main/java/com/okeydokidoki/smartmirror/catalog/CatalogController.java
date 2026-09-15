package com.okeydokidoki.smartmirror.catalog;

import com.okeydokidoki.smartmirror.common.ApiResponse;
import java.util.Collections;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
public class CatalogController {

    @GetMapping
    public ApiResponse<List<Object>> listProducts() {
        return ApiResponse.ok(Collections.emptyList());
    }
}
