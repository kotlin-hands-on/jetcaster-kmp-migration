package com.example.jetcaster.core.data.network

import kotlinx.datetime.UtcOffset
import kotlinx.datetime.format.DateTimeComponents
import kotlinx.datetime.format.DateTimeComponents.Companion.Format
import kotlinx.datetime.format.DateTimeFormat
import kotlinx.datetime.format.DayOfWeekNames
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.Padding
import kotlinx.datetime.format.alternativeParsing
import kotlinx.datetime.format.char
import kotlinx.datetime.format.optional

val RFC_1123_WITH_UTC: DateTimeFormat<DateTimeComponents> = Format {
    alternativeParsing(
        {
            // the day of week may be missing
        },
    ) {
        dayOfWeek(DayOfWeekNames.ENGLISH_ABBREVIATED)
        chars(", ")
    }
    day(Padding.NONE)
    char(' ')
    monthName(MonthNames.ENGLISH_ABBREVIATED)
    char(' ')
    year()
    char(' ')
    hour()
    char(':')
    minute()
    optional {
        char(':')
        second()
    }
    chars(" ")
    alternativeParsing(
        {
            chars("UTC")
        },
        {
            chars("UT")
        },
        {
            chars("Z")
        },
    ) {
        optional("GMT") {
            offset(UtcOffset.Formats.FOUR_DIGITS)
        }
    }
}