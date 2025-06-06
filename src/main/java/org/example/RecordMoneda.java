
package org.example;

import java.util.Map;

public record RecordMoneda(String result,
                           String base_code,
                           Map<String, Double> conversion_rates
) {
}
