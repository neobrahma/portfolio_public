package com.neobrahma.portfolio.presentation.information.info

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.neobrahma.portfolio.R
import com.neobrahma.portfolio.ui.component.background.BackgroundItemView
import com.neobrahma.portfolio.ui.component.background.BackgroundPrimaryItemView
import com.neobrahma.portfolio.ui.component.item.primary.PrimaryItemUI
import com.neobrahma.portfolio.ui.shape.OctagonShape

@Composable
fun InformationScreen() {
    Scaffold(
        content = {
            Information(
                modifier = Modifier
                    .background(color = colorResource(id = R.color.blue_dark))
                    .fillMaxSize()
                    .padding(it)
            )
        }
    )
}

@Composable
fun Information(
    modifier: Modifier
) {
    Column(
        modifier = modifier.verticalScroll(
            state = rememberScrollState()
        )
    ) {
        MeView(
            PrimaryItemUI(
                title = "Lecuyer Anthony",
                header = "Android Application Developer",
                footer = "Nanterre (92) - France",
                idDrawable = R.drawable.logo_appstud,
                isClosable = false,
                isOpenable = false,
                color = R.color.red
            )
        )
        ContactView()
        PresentationView()
        DegreeView()
        DiversView()
        LoisirView()
    }
}

@Composable
fun MeView(ui: PrimaryItemUI) {
    ConstraintLayout(
        modifier = Modifier
            .height(116.dp)
            .fillMaxWidth()
    ) {
        val (iconRef, titleRef, valueRef, testRef, backgroundRef) = createRefs()
        val paddingStart = 10.dp
        Image(
            painter = painterResource(id = ui.idDrawable),
            contentDescription = null,
            modifier = Modifier
                .size(96.dp)
                .clip(OctagonShape())
                .constrainAs(iconRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start, paddingStart)
                }
        )
        Text(
            text = ui.header,
            color = colorResource(id = com.neobrahma.portfolio.ui.R.color.white),
            fontSize = 14.sp,
            modifier = Modifier
                .constrainAs(titleRef) {
                    start.linkTo(iconRef.end, 2.dp)
                    top.linkTo(parent.top, 4.dp)
                    bottom.linkTo(testRef.top)
                }
        )
        Text(
            text = ui.title,
            color = colorResource(id = com.neobrahma.portfolio.ui.R.color.white),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier
                .constrainAs(testRef) {
                    start.linkTo(iconRef.end, 2.dp)
                    top.linkTo(titleRef.bottom)
                    bottom.linkTo(valueRef.top)
                }
        )
        Text(
            text = ui.footer,
            color = colorResource(id = com.neobrahma.portfolio.ui.R.color.white),
            fontSize = 14.sp,
            modifier = Modifier
                .constrainAs(valueRef) {
                    start.linkTo(iconRef.end, 2.dp)
                    top.linkTo(testRef.bottom)
                    bottom.linkTo(parent.bottom, 4.dp)
                }
        )
        BackgroundPrimaryItemView(
            Modifier
                .height(116.dp)
                .fillMaxWidth()
                .constrainAs(backgroundRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
        )
    }
}

@Composable
fun ContactView() {
    val context = LocalContext.current
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        val (titleRef, descriptionRef, backgroundRef) = createRefs()

        val startGuideline = createGuidelineFromStart(32.dp)
        val endGuideline = createGuidelineFromEnd(32.dp)
        val topGuideline = createGuidelineFromTop(16.dp)
        val bottomGuideline = createGuidelineFromBottom(24.dp)

        Text(
            text = "CONTACT",
            textAlign = TextAlign.Start,
            color = colorResource(id = com.neobrahma.portfolio.ui.R.color.white),
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier
                .constrainAs(titleRef) {
                    start.linkTo(startGuideline)
                    end.linkTo(endGuideline)
                    top.linkTo(topGuideline)
                    width = Dimension.fillToConstraints
                }

        )

        Column(
            modifier = Modifier.constrainAs(descriptionRef) {
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                top.linkTo(titleRef.bottom)
                bottom.linkTo(bottomGuideline)
                width = Dimension.fillToConstraints
            }
        ) {
            val modifierIcon = Modifier
                .padding(8.dp)
                .size(48.dp)
                .clip(OctagonShape())
            Row(
                modifier = Modifier.fillMaxWidth(1f),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_mail),
                    contentDescription = null,
                    modifier = modifierIcon
                        .clickable {
                            val intent = Intent(Intent.ACTION_SEND)
                            intent.type = "plain/text"
                            intent.putExtra(
                                Intent.EXTRA_EMAIL,
                                arrayOf("anthony.lecuyer@gmail.com")
                            )
                            intent.putExtra(Intent.EXTRA_SUBJECT, "Contact from portfolio")
                            context.startActivity(Intent.createChooser(intent, "Choose your mail"))
                        }

                )
                Image(
                    painter = painterResource(id = R.drawable.ic_phone),
                    contentDescription = null,
                    modifier = modifierIcon
                        .clickable {
                            val intent = Intent(Intent.ACTION_DIAL)
                            intent.data = Uri.parse("tel:+33659384239")
                            context.startActivity(intent)
                        }
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(1f),
                horizontalArrangement = Arrangement.Center,
            ) {
                listOf(
                    R.drawable.ic_insta to "https://www.instagram.com/neo.brahma.studio/",
                    R.drawable.ic_github to "https://github.com/neobrahma",
                    R.drawable.ic_linkedin to "https://www.linkedin.com/in/anthony-lecuyer-199385237/"
                ).forEach {
                    Image(
                        painter = painterResource(id = it.first),
                        contentDescription = null,
                        modifier = modifierIcon
                            .clickable {
                                val browserIntent =
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(it.second)
                                    )
                                context.startActivity(browserIntent)
                            }
                    )
                }
            }
        }

        BackgroundItemView(
            modifier = Modifier
                .constrainAs(backgroundRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }
        )
    }
}

@Composable
fun PresentationView() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        val (titleRef, descriptionRef, backgroundRef) = createRefs()

        val startGuideline = createGuidelineFromStart(32.dp)
        val endGuideline = createGuidelineFromEnd(32.dp)
        val topGuideline = createGuidelineFromTop(16.dp)
        val bottomGuideline = createGuidelineFromBottom(24.dp)

        Text(
            text = "INTRODUCTION",
            textAlign = TextAlign.Start,
            color = colorResource(id = com.neobrahma.portfolio.ui.R.color.white),
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier
                .constrainAs(titleRef) {
                    start.linkTo(startGuideline)
                    end.linkTo(endGuideline)
                    top.linkTo(topGuideline)
                    width = Dimension.fillToConstraints
                }

        )

        Text(
            text = "Très intéressé par le potentiel des google phones en 2010, je me suis auto-former en créer ma 1er application Chronosport disponible sur le store.\n" +
                    "\n" +
                    "J’aime améliorer mes compétences techniques & UI en développant des projets perso afin d’essayer d’innover et non suivre uniquement les guidelines.\n" +
                    "\n" +
                    "Gérant une équipe pour mon restaurant, je suis une personne organisé afin optimiser le temps de mes employés afin d’être plus productif.",
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.subtitle2,
            modifier = Modifier.constrainAs(descriptionRef) {
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                top.linkTo(titleRef.bottom)
                bottom.linkTo(bottomGuideline)
                width = Dimension.fillToConstraints
            },
            color = colorResource(id = com.neobrahma.portfolio.ui.R.color.white)
        )

        BackgroundItemView(
            modifier = Modifier
                .constrainAs(backgroundRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }
        )
    }
}

@Composable
fun DegreeView() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        val (titleRef, descriptionRef, backgroundRef) = createRefs()

        val startGuideline = createGuidelineFromStart(32.dp)
        val endGuideline = createGuidelineFromEnd(32.dp)
        val topGuideline = createGuidelineFromTop(16.dp)
        val bottomGuideline = createGuidelineFromBottom(24.dp)

        Text(
            text = "DEGREES",
            color = colorResource(id = com.neobrahma.portfolio.ui.R.color.white),
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier
                .constrainAs(titleRef) {
                    start.linkTo(startGuideline)
                    end.linkTo(endGuideline)
                    top.linkTo(topGuideline)
                    width = Dimension.fillToConstraints
                }
        )

        Row(
            modifier = Modifier.constrainAs(descriptionRef) {
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                top.linkTo(titleRef.bottom)
                bottom.linkTo(bottomGuideline)
                width = Dimension.fillToConstraints
            }
        ) {
            Text(
                text = "2010",
                style = MaterialTheme.typography.subtitle2,
                color = colorResource(id = com.neobrahma.portfolio.ui.R.color.white)
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp, 0.dp, 8.dp, 0.dp)
            ) {
                Text(
                    text = "IFSIC",
                    style = MaterialTheme.typography.subtitle2,
                    color = colorResource(id = com.neobrahma.portfolio.ui.R.color.white)
                )
                Text(
                    text = "Master computer science\nmajor Génie Logiciel",
                    style = MaterialTheme.typography.subtitle2,
                    color = colorResource(id = com.neobrahma.portfolio.ui.R.color.white)
                )
            }
            Text(
                text = "Rennes (35)\nFrance",
                style = MaterialTheme.typography.subtitle2,
                color = colorResource(id = com.neobrahma.portfolio.ui.R.color.white)
            )
        }

        BackgroundItemView(
            modifier = Modifier
                .constrainAs(backgroundRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }
        )
    }
}

@Composable
fun DiversView() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        val (titleRef, descriptionRef, backgroundRef) = createRefs()

        val startGuideline = createGuidelineFromStart(32.dp)
        val endGuideline = createGuidelineFromEnd(32.dp)
        val topGuideline = createGuidelineFromTop(16.dp)
        val bottomGuideline = createGuidelineFromBottom(24.dp)

        Text(
            text = "OTHER",
            color = colorResource(id = com.neobrahma.portfolio.ui.R.color.white),
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier
                .constrainAs(titleRef) {
                    start.linkTo(startGuideline)
                    end.linkTo(endGuideline)
                    top.linkTo(topGuideline)
                    width = Dimension.fillToConstraints
                }
        )

        Text(
            text = "Permis B\n" +
                    "Français : langue natale\n" +
                    "Anglais : niveau XX",
            style = MaterialTheme.typography.subtitle2,
            modifier = Modifier.constrainAs(descriptionRef) {
                start.linkTo(startGuideline)
                top.linkTo(titleRef.bottom)
                bottom.linkTo(bottomGuideline)
                width = Dimension.fillToConstraints
            },
            color = colorResource(id = com.neobrahma.portfolio.ui.R.color.white)
        )

        BackgroundItemView(
            modifier = Modifier
                .constrainAs(backgroundRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }
        )
    }
}

@Composable
fun LoisirView() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        val (titleRef, descriptionRef, backgroundRef) = createRefs()

        val startGuideline = createGuidelineFromStart(32.dp)
        val endGuideline = createGuidelineFromEnd(32.dp)
        val topGuideline = createGuidelineFromTop(16.dp)
        val bottomGuideline = createGuidelineFromBottom(24.dp)

        Text(
            text = "LOISIRS",
            color = colorResource(id = com.neobrahma.portfolio.ui.R.color.white),
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier
                .constrainAs(titleRef) {
                    start.linkTo(startGuideline)
                    end.linkTo(endGuideline)
                    top.linkTo(topGuideline)
                    width = Dimension.fillToConstraints
                }
        )

        Text(
            text = "Series/films VO\n" +
                    "Jeux video\n" +
                    "Lecture\n" +
                    "Cuisine\n" +
                    "Jardinage\n" +
                    "Construction de meubles\n" +
                    "Art martiaux\n" +
                    "Tir à l’arc\n" +
                    "Basket",
            style = MaterialTheme.typography.subtitle2,
            modifier = Modifier.constrainAs(descriptionRef) {
                start.linkTo(startGuideline)
                top.linkTo(titleRef.bottom)
                bottom.linkTo(bottomGuideline)
                width = Dimension.fillToConstraints
            },
            color = colorResource(id = com.neobrahma.portfolio.ui.R.color.white)
        )

        BackgroundItemView(
            modifier = Modifier
                .constrainAs(backgroundRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }
        )
    }
}