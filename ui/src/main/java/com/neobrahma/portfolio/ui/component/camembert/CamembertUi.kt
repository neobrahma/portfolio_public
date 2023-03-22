package com.neobrahma.portfolio.ui.component.camembert

data class CamembertUi(
    val arrayColor: FloatArray,
    val percent: Float
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CamembertUi

        if (!arrayColor.contentEquals(other.arrayColor)) return false
        if (percent != other.percent) return false

        return true
    }

    override fun hashCode(): Int {
        var result = arrayColor.contentHashCode()
        result = 31 * result + percent.hashCode()
        return result
    }
}