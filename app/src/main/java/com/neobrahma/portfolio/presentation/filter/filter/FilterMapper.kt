package com.neobrahma.portfolio.presentation.filter.filter

import com.neobrahma.portfolio.domain.model.CategoryData
import com.neobrahma.portfolio.presentation.Utils
import com.neobrahma.portfolio.presentation.filter.filter.model.FilterUi
import com.neobrahma.portfolio.presentation.filter.filter.model.StackUi
import javax.inject.Inject

class FilterMapper @Inject constructor(
    private val utils: Utils
){
    fun mapperCategoryToUI(categories: List<CategoryData>): List<FilterUi> {
        return categories.map { data ->
            FilterUi(
                label = data.label,
                stacks = data.stacks.map { stack ->
                    StackUi(
                        stack.stackId, stack.label,
                        utils.drawableFromResourcesByName(stack.iconId),
                        stack.isFavorite
                    )
                }
            )
        }
    }
}