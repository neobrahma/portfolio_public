package com.neobrahma.portfolio.presentation.information.info.model

import com.neobrahma.portfolio.ui.component.camembert.CamembertUi

data class InformationUI(
    val categoryLabel: String,
    val legends: List<LegendUi>,
    val camemberts: List<CamembertUi>
)