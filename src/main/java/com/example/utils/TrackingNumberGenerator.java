package com.example.utils;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Pattern;

@Component
public class TrackingNumberGenerator {

    public TrackingNumberGenerator() {
    }

    private final Pattern NON_ALNUM = Pattern.compile("[^A-Za-z0-9]");
    private final SecureRandom RNG   = new SecureRandom();

    public String buildTrackingNumber(String originCountryId,
                                             String destinationCountryId,
                                             String customerId,
                                             String customerSlug) {

        String baseTrackingNumber = (clean(originCountryId) + clean(destinationCountryId) + clean(abbreviateName(customerSlug)) + clean(customerId) ).toUpperCase();

        if (baseTrackingNumber.length() > 12) {
            baseTrackingNumber = baseTrackingNumber.substring(0, 12);
        }

        String suffix = randomSuffix();

        return baseTrackingNumber + suffix;
    }

    private String randomSuffix() {
        return String.format("%04X", RNG.nextInt(0x10000));
    }

    public String getCurrentRfc3339Timestamp() {
        return ZonedDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }


    private String clean(String in) {
        return (in == null) ? "" : NON_ALNUM.matcher(in).replaceAll("");
    }

    private String abbreviateName(String slug) {
        String part1 = "";
        String part2 = "";

        if (slug != null && !slug.isBlank()) {
            String cleanedSlug = NON_ALNUM.matcher(slug).replaceAll("");
            if (!cleanedSlug.isEmpty()) {
                part2 = cleanedSlug.substring(0, Math.min(3, cleanedSlug.length())).toUpperCase();
            }
        }

        String combined = part1 + part2;
        return combined;
    }

}
