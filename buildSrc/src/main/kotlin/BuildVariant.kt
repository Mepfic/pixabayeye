import com.android.build.api.dsl.ProductFlavor
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

enum class UiDimension(val flavor: String) {
    CLASSIC_UI("classicUi"),
    COMPOSE_UI("composeUi");

    companion object {
        const val DIMENSION = "UiType"
        fun addProductFlavors(productFlavor: NamedDomainObjectContainer<out ProductFlavor>) {
            values().map { it.flavor }.forEach {
                productFlavor.create(it) {
                    dimension = DIMENSION
                }
            }
        }
    }
}

enum class UiFeatures {
    SEARCH,
    DETAILS
}

fun DependencyHandler.uiImplementation(uiFeatures: UiFeatures) {
    UiDimension.values().forEach { ui ->
        val path = when (uiFeatures) {
            UiFeatures.SEARCH -> getSearchModule(ui)
            UiFeatures.DETAILS -> getDetailsModule(ui)
        }
        add("${ui.flavor}$IMPLEMENTATION", project(path))
    }
}

private fun getSearchModule(ui: UiDimension): String =
    when(ui) {
        UiDimension.CLASSIC_UI -> ":uiSearch"
        UiDimension.COMPOSE_UI -> ":uiSearchCompose"
    }

private fun getDetailsModule(ui: UiDimension): String =
    when(ui) {
        UiDimension.CLASSIC_UI -> ":uiDetails"
        UiDimension.COMPOSE_UI -> ":uiDetailsCompose"
    }

private const val IMPLEMENTATION = "Implementation"
