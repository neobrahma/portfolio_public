package com.neobrahma.portfolio.presentation.information.info

import com.neobrahma.portfolio.domain.model.InformationData
import com.neobrahma.portfolio.presentation.information.info.model.InformationUI
import com.neobrahma.portfolio.presentation.information.info.model.LegendUi
import com.neobrahma.portfolio.ui.component.camembert.CamembertUi
import javax.inject.Inject

class InformationMapper @Inject constructor() {

    fun mapperToUI(information: InformationData): List<InformationUI> {
        return information.list.map { stackStatistical ->

            val total = stackStatistical.stacks.sumOf {
                it.second
            }

            val deltaColor = 360 / stackStatistical.stacks.size
            val startColor = deltaColor / 2

            val legends = mutableListOf<LegendUi>()
            val camemberts = mutableListOf<CamembertUi>()

            stackStatistical.stacks.forEachIndexed { index, pair ->
                val array = floatArrayOf(
                    (index * deltaColor + startColor).toFloat(),
                    1.0f,
                    1.0f
                )

                legends.add(LegendUi(array, pair.first))
                camemberts.add(CamembertUi(array, 360f * pair.second / total))
            }

            InformationUI(
                categoryLabel = stackStatistical.label,
                legends = legends,
                camemberts = camemberts
            )
        }
    }

}