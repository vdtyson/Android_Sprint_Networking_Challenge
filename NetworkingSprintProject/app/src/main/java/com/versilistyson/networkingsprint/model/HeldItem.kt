package com.versilistyson.networkingsprint.model

data class HeldItem(
    val item: Item,
    val version_details: List<VersionDetail>
)