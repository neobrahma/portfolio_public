package com.neobrahma.portfolio.presentation.information.info.model

data class LegendUi(
    val arrayColor: FloatArray,
    val stackLabel: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LegendUi

        if (!arrayColor.contentEquals(other.arrayColor)) return false
        if (stackLabel != other.stackLabel) return false

        return true
    }

    override fun hashCode(): Int {
        var result = arrayColor.contentHashCode()
        result = 31 * result + stackLabel.hashCode()
        return result
    }
}