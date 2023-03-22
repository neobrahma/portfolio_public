package com.neobrahma.portfolio.data.mock

import com.neobrahma.portfolio.data.mock.model.*

data class PortfolioDAO(
    val categories: List<CategoryDAO>,
    val stacks: List<StackDAO>,
    val companies: List<CompanyDAO>,
)


val categories = listOf(
    CategoryDAO(1, "OS"),
    CategoryDAO(2, "IDEs"),
    CategoryDAO(3, "Languages"),
    CategoryDAO(4, "Architectures"),
    CategoryDAO(5, "Programmation Reactive"),
    CategoryDAO(6, "Dependancy Injection"),
    CategoryDAO(7, "Protocole communication"),
    CategoryDAO(8, "Gestion de tag"),
    CategoryDAO(9, "Tests"),
    CategoryDAO(10, "Librairies"),
    CategoryDAO(11, "Gestion de versions"),
    CategoryDAO(12, "Methodologies")
)

val stacks = listOf(
    StackDAO(1, "Windows", "logo_default", 1),
    StackDAO(2, "Mac OS", "logo_default", 1),
    StackDAO(3, "Ubuntu", "logo_default", 1),
    StackDAO(4, "Android", "logo_default", 1),

    StackDAO(5, "Eclipse", "logo_default", 2),
    StackDAO(6, "Android Studio", "logo_default", 2),

    StackDAO(7, "Java", "logo_default", 3),
    StackDAO(8, "Kotlin", "logo_default", 3),
    StackDAO(9, "Python", "logo_default", 3),
    StackDAO(10, "Perl", "logo_default", 3),
    StackDAO(11, "Javascript", "logo_default", 3),
    StackDAO(12, "PHP", "logo_default", 3),

    StackDAO(13, "MVP", "logo_default", 4),
    StackDAO(14, "MVVM", "logo_default", 4),
    StackDAO(15, "Clean Architecture", "logo_default", 4),

    StackDAO(16, "Reactive X", "logo_default", 5),
    StackDAO(17, "Kotlin Flow", "logo_default", 5),

    StackDAO(18, "Dagger 2", "logo_default", 6),
    StackDAO(19, "Hilt", "logo_default", 6),

    StackDAO(20, "BLE", "logo_default", 7),
    StackDAO(21, "MQTT", "logo_default", 7),
    StackDAO(22, "AllJoyn", "logo_default", 7),

    StackDAO(23, "GTM", "logo_default", 8),
    StackDAO(24, "Coremetrics", "logo_default", 8),

    StackDAO(25, "JUnit", "logo_default", 9),
    StackDAO(26, "Espresso", "logo_default", 9),
    StackDAO(27, "Robolectric", "logo_default", 9),

    StackDAO(28, "Retrofit", "logo_default", 10),
    StackDAO(29, "Lottie", "logo_default", 10),
    StackDAO(30, "Picasso", "logo_default", 10),
    StackDAO(31, "Green Robot", "logo_default", 10),

    StackDAO(32, "CVS", "logo_default", 11),
    StackDAO(33, "SVN", "logo_default", 11),
    StackDAO(34, "Git", "logo_default", 11),

    StackDAO(35, "Cycle V", "logo_default", 12),
    StackDAO(36, "Agile", "logo_default", 12)
)

val companies = listOf(
    CompanyDAO(
        companyId = 14,
        name = "Neo Brahma Studio",
        logo = "logo_default",
        date = "02/19 - aujourd'hui",
        city = "Reims",
        clients = listOf(
            ClientDAO(
                id = 15,
                name = "Renault Digital",
                logo = "logo_default",
                date = "08/22 - 11/22",
                city = "Paris",
                projects = listOf(
                    ProjectDAO(
                        id = 24,
                        name = "MyBrand",
                        logo = "logo_default",
                        role = "Android Developer",
                        context = "Application bla bla bla",
                        tasks = listOf(
                            "dev",
                            "fix",
                            "etc, etc"
                        ),
                        stacks = listOf(2, 4, 6, 7, 8, 14, 15, 16, 18, 25, 26, 27, 34, 36, 28)
                    )
                )
            ),
            ClientDAO(
                id = 16,
                name = "Leroy Merlin",
                logo = "logo_default",
                date = "07/20 - 04/22",
                city = "Reims",
                projects = listOf(
                    ProjectDAO(
                        id = 23,
                        name = "Enki",
                        logo = "logo_default",
                        role = "Android Developer",
                        context = "Application bla bla bla",
                        tasks = listOf(
                            "dev",
                            "fix",
                            "etc, etc"
                        ),
                        stacks = listOf(2, 4, 6, 7, 8, 13, 15, 16, 18, 20, 21, 22, 23, 34, 36)
                    ),
                    ProjectDAO(
                        id = 22,
                        name = "Application Test IoT",
                        logo = "logo_default",
                        role = "Android Developer",
                        context = "Application bla bla bla",
                        tasks = listOf(
                            "dev",
                            "fix",
                            "etc, etc"
                        ),
                        stacks = listOf(2, 4, 6, 8, 14, 15, 21, 22, 34)
                    )
                )
            ),
            ClientDAO(
                id = 17,
                name = "OBS",
                logo = "logo_default",
                date = "19/19 - 06/20",
                city = "Bordeaux",
                projects = listOf(
                    ProjectDAO(
                        id = 21,
                        name = "MyMarque",
                        logo = "logo_default",
                        role = "Android Developer",
                        context = "Application bla bla bla",
                        tasks = listOf(
                            "dev",
                            "fix",
                            "etc, etc"
                        ),
                        stacks = listOf(2, 4, 6, 7, 34)
                    )
                )
            )
        ),
        projects = listOf(
            ProjectDAO(
                id = 26,
                name = "Custom View",
                logo = "logo_default",
                role = "Android Developer",
                context = "Application bla bla bla",
                tasks = listOf(
                    "dev",
                    "fix",
                    "etc, etc"
                ),
                stacks = listOf(2, 4, 6, 8, 34)
            ),
            ProjectDAO(
                id = 25,
                name = "Custom View",
                logo = "logo_default",
                role = "Android Developer",
                context = "Application bla bla bla",
                tasks = listOf(
                    "dev",
                    "fix",
                    "etc, etc"
                ),
                stacks = listOf(2, 4, 6, 8, 34)
            ),
            ProjectDAO(
                id = 20,
                name = "Portfolio",
                logo = "logo_default",
                role = "Android Developer",
                context = "Application representant mon CV en m'inspirant de GIT.",
                tasks = listOf(
                    "Developpement de l'application from scratch",
                    "Developpement d'un API Rest"
                ),
                stacks = listOf(2, 4, 6, 8, 14, 15, 17, 19, 34)
            ),
            ProjectDAO(
                id = 19,
                name = "Custom View",
                logo = "logo_default",
                role = "Android Developer",
                context = "Application bla bla bla",
                tasks = listOf(
                    "dev",
                    "fix",
                    "etc, etc"
                ),
                stacks = listOf(2, 4, 6, 8, 34)
            )
        )
    ),
    CompanyDAO(
        companyId = 13,
        name = "Groupe PSA",
        logo = "logo_default",
        date = "09/17 - 06/19",
        city = "Poissy",
        clients = emptyList(),
        projects = listOf(
            ProjectDAO(
                id = 18,
                name = "MyMarque",
                logo = "logo_default",
                role = "Android Developer",
                context = "Application bla bla bla",
                tasks = listOf(
                    "dev",
                    "fix",
                    "etc, etc"
                ),
                stacks = listOf(2, 4, 6, 7, 34)
            )
        )
    ),
    CompanyDAO(
        companyId = 12,
        name = "in-Tact",
        logo = "logo_intact",
        date = "01/17 - 06/17",
        city = "Paris",
        clients = emptyList(),
        projects = listOf(
            ProjectDAO(
                id = 17,
                name = "MobEFluid",
                logo = "logo_default",
                role = "Android Developer",
                context = "Application bla bla bla",
                tasks = listOf(
                    "dev",
                    "fix",
                    "etc, etc"
                ),
                stacks = listOf(2, 4, 6, 7, 34)
            )
        )
    ),
    CompanyDAO(
        companyId = 11,
        name = "Wynd",
        logo = "logo_wynd",
        date = "10/16 - 12/16",
        city = "Paris",
        clients = emptyList(),
        projects = listOf(
            ProjectDAO(
                id = 16,
                name = "Wynd",
                logo = "logo_default",
                role = "Android Developer",
                context = "Application bla bla bla",
                tasks = listOf(
                    "dev",
                    "fix",
                    "etc, etc"
                ),
                stacks = listOf(2, 4, 6, 7, 34, 16)
            )
        )
    ),
    CompanyDAO(
        companyId = 10,
        name = "AppStud",
        logo = "logo_appstud",
        date = "08/16",
        city = "Paris",
        clients = emptyList(),
        projects = listOf(
            ProjectDAO(
                id = 15,
                name = "Invidam",
                logo = "logo_default",
                role = "Android Developer",
                context = "Application bla bla bla",
                tasks = listOf(
                    "dev",
                    "fix",
                    "etc, etc"
                ),
                stacks = listOf(1, 4, 6, 7, 34, 16)
            )
        )
    ),
    CompanyDAO(
        companyId = 9,
        name = "Backelite",
        logo = "logo_backelite",
        date = "02/16 - 06/16",
        city = "Montpellier",
        clients = emptyList(),
        projects = listOf(
            ProjectDAO(
                id = 14,
                name = "MaCarte CA",
                logo = "logo_default",
                role = "Android Developer",
                context = "Application bla bla bla",
                tasks = listOf(
                    "dev",
                    "fix",
                    "etc, etc"
                ),
                stacks = listOf(3, 4, 6, 7, 16, 24, 25, 27)
            )
        )
    ),
    CompanyDAO(
        companyId = 8,
        name = "Zone Franche",
        logo = "logo_default",
        date = "09/15 - 01/16",
        city = "Clichy-Levallois",
        clients = emptyList(),
        projects = listOf(
            ProjectDAO(
                id = 13,
                name = "Citroen Mon Entretien V3",
                logo = "logo_default",
                role = "Android Developer",
                context = "Application bla bla bla",
                tasks = listOf(
                    "dev",
                    "fix",
                    "etc, etc"
                ),
                stacks = listOf(1, 4, 5, 7, 34)
            )
        )
    ),
    CompanyDAO(
        companyId = 7,
        name = "LeBonCoin",
        logo = "logo_default",
        date = "04/14 - 06/14",
        city = "Paris",
        clients = emptyList(),
        projects = listOf(
            ProjectDAO(
                id = 12,
                name = "LeBonCoin",
                logo = "logo_default",
                role = "Android Developer",
                context = "Application bla bla bla",
                tasks = listOf(
                    "dev",
                    "fix",
                    "etc, etc"
                ),
                stacks = listOf(1, 4, 6, 7, 34, 18)
            )
        )
    ),
    CompanyDAO(
        companyId = 6,
        name = "Steamulo",
        logo = "logo_default",
        date = "06/14 - 09/14",
        city = "Paris",
        clients = emptyList(),
        projects = listOf(
            ProjectDAO(
                id = 11,
                name = "Urban Pulse",
                logo = "logo_default",
                role = "Android Developer",
                context = "Application bla bla bla",
                tasks = listOf(
                    "dev",
                    "fix",
                    "etc, etc"
                ),
                stacks = listOf(1, 4, 5, 7, 33)
            ),
            ProjectDAO(
                id = 10,
                name = "Be Smart by Cofinoga",
                logo = "logo_default",
                role = "Android Developer",
                context = "Application bla bla bla",
                tasks = listOf(
                    "dev",
                    "fix",
                    "etc, etc"
                ),
                stacks = listOf(1, 4, 5, 7, 33)
            ),
            ProjectDAO(
                id = 9,
                name = "AccorHotels.com",
                logo = "logo_default",
                role = "Android Developer",
                context = "Application bla bla bla",
                tasks = listOf(
                    "dev",
                    "fix",
                    "etc, etc"
                ),
                stacks = listOf(1, 4, 5, 7, 33)
            )
        )
    ),
    CompanyDAO(
        companyId = 5,
        name = "Parrot",
        logo = "logo_default",
        date = "09/13 - 02/14",
        city = "Paris",
        clients = emptyList(),
        projects = listOf(
            ProjectDAO(
                id = 8,
                name = "Asteroid Smart & Tablet",
                logo = "logo_default",
                role = "Android Developer",
                context = "Application bla bla bla",
                tasks = listOf(
                    "dev",
                    "fix",
                    "etc, etc"
                ),
                stacks = listOf(1, 4, 5, 7, 9, 34)
            )
        )
    ),
    CompanyDAO(
        companyId = 4,
        name = "Stime",
        logo = "logo_default",
        date = "08/12 - 12/12",
        city = "Montrouge",
        clients = emptyList(),
        projects = listOf(
            ProjectDAO(
                id = 7,
                name = "ChoisirSonVin",
                logo = "logo_default",
                role = "Android Developer",
                context = "Application bla bla bla",
                tasks = listOf(
                    "dev",
                    "fix",
                    "etc, etc"
                ),
                stacks = listOf(1, 4, 5, 7)
            ),
            ProjectDAO(
                id = 6,
                name = "Inter7",
                logo = "logo_default",
                role = "Android Developer",
                context = "Application bla bla bla",
                tasks = listOf(
                    "dev",
                    "fix",
                    "etc, etc"
                ),
                stacks = listOf(1, 4, 5, 7)
            )
        )
    ),
    CompanyDAO(
        companyId = 3,
        name = "Glucoz",
        logo = "logo_default",
        date = "04/12 - 07/12",
        city = "Paris",
        clients = emptyList(),
        projects = listOf(
            ProjectDAO(
                id = 5,
                name = "Evenizer",
                logo = "logo_default",
                role = "Android Developer",
                context = "Application bla bla bla",
                tasks = listOf(
                    "dev",
                    "fix",
                    "etc, etc"
                ),
                stacks = listOf(1, 4, 5, 7)
            ),
            ProjectDAO(
                id = 4,
                name = "POC",
                logo = "logo_default",
                role = "Android Developer",
                context = "Application bla bla bla",
                tasks = listOf(
                    "dev",
                    "fix",
                    "etc, etc"
                ),
                stacks = listOf(1, 4, 5, 7)
            ),
            ProjectDAO(
                id = 3,
                name = "R.S Monitor",
                logo = "logo_default",
                role = "Android Developer",
                context = "Application bla bla bla",
                tasks = listOf(
                    "dev",
                    "fix",
                    "etc, etc"
                ),
                stacks = listOf(1, 4, 5, 7)
            )
        )
    ),
    CompanyDAO(
        companyId = 2,
        name = "Groupe Altimate",
        logo = "logo_default",
        date = "09/13 - 02/14",
        city = "Rueil-Malmaison",
        clients = emptyList(),
        projects = listOf(
            ProjectDAO(
                id = 2,
                name = "Aberdyn",
                logo = "logo_default",
                role = "Android Developer",
                context = "Application bla bla bla",
                tasks = listOf(
                    "dev",
                    "fix",
                    "etc, etc"
                ),
                stacks = listOf(1, 4, 5, 12)
            )
        )
    ),
    CompanyDAO(
        companyId = 1,
        name = "Thales",
        logo = "logo_default",
        date = "09/13 - 02/14",
        city = "Velisy",
        clients = emptyList(),
        projects = listOf(
            ProjectDAO(
                id = 1,
                name = "Thales Interne",
                logo = "logo_default",
                role = "J2EE Developer",
                context = "Application bla bla bla",
                tasks = listOf(
                    "dev",
                    "fix",
                    "etc, etc"
                ),
                stacks = listOf(1, 5, 7, 11, 32)
            )
        )
    ),
    CompanyDAO(
        companyId = 0,
        name = "Atos",
        logo = "logo_default",
        date = "09/13 - 02/14",
        city = "Rennes",
        clients = emptyList(),
        projects = listOf(
            ProjectDAO(
                id = 0,
                name = "CNT",
                logo = "logo_default",
                role = "J2EE Developer",
                context = "Application bla bla bla",
                tasks = listOf(
                    "dev",
                    "fix",
                    "etc, etc"
                ),
                stacks = listOf(1, 5, 7, 9, 32)
            )
        )
    )
)

val portfolioDAO = PortfolioDAO(
    categories = categories,
    stacks = stacks,
    companies = companies
)