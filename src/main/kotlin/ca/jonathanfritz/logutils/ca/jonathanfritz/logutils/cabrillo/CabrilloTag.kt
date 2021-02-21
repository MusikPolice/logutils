package ca.jonathanfritz.logutils.ca.jonathanfritz.logutils.cabrillo

// from https://wwrof.org/cabrillo/cabrillo-v3-header/
enum class CabrilloTag(public val tag: String, val required: Boolean, val pattern: Regex = Regex(".*")) {

    // major.minor ex: 3.0
    StartOfLog("START-OF-LOG", true, Regex("""^\d+\.?\d*$""")),

    // no value
    EndOfLog("END-OF-LOG", true, Regex("""^$""")),

    // one or two character alpha prefix, a separating numeral, one to four character alpha suffix
    Callsign("CALLSIGN", false, Regex("""^[A-Z]{1,2}\d[A-Z0-9]{2,4}$""")),

    // valid characters are A-Z, 0-9, and hyphen (-). Maximum length is 32 characters
    Contest("CONTEST", false, Regex("""^[A-Z0-9\-]{1,32}$""")),

    // one of two possible values
    CategoryAssisted("CATEGORY-ASSISTED", false, Regex("""^(ASSISTED|NON-ASSISTED)$""")),

    CategoryBand("CATEGORY-BAND", false, Regex("""^()$"""))
}