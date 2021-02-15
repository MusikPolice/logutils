package ca.jonathanfritz.logutils.ca.jonathanfritz.logutils.cabrillo

enum class CabrilloTag(public val tag: String, val required: Boolean, val pattern: Regex = Regex(".*")) {
    // from https://wwrof.org/cabrillo/cabrillo-v3-header/
    StartOfLog("START-OF-LOG", true, Regex("""^\d+\.?\d*$""")),
    EndOfLog("END-OF-LOG", true, Regex("""^$""")),
    Callsign("CALLSIGN", false, Regex("""^[A-Z]{1,2}\d[A-Z0-9]{2,4}$""")),
    Contest("CONTEST", false, Regex("""^[A-Z0-9\-]{1,32}$""")),
    CategoryAssisted("CATEGORY-ASSISTED", false, Regex("""^(ASSISTED|NON-ASSISTED)$"""))
}