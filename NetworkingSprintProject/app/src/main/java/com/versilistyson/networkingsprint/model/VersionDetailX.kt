package com.versilistyson.networkingsprint.model

data class VersionDetailX(
    val encounter_details: List<EncounterDetail>,
    val max_chance: Int,
    val version: VersionXX
)