package com.example.profildeveloppeurcarlos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.profildeveloppeurcarlos.ui.theme.ProfilDeveloppeurCarlosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProfilDeveloppeurCarlosTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ProfileScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

// Écran principal : il fournit les données et décide du placement de la fiche.
@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    ProfileCard(
        name = "Carlos Guillermo Gómez",
        role = "Développeur Web & Mobile",
        description = "Tout juste diplômé d'un BUT Informatique, passionné par le " +
            "développement d'applications modernes et innovantes. " +
            "Développeur Front-End chez thecamp depuis septembre 2025.",
        skills = listOf("TypeScript", "React / Next.js", "React Native", "Node.js / Nest.js", "PostgreSQL", "Expo", "Jetpack Compose"),
        contact = "carlos.guillermogomez18@gmail.com",
        isAvailable = true,
        onAvailabilityClick = {
            // Plus tard : afficher le détail des disponibilités
        },
        onContactClick = {
            // Plus tard : ouvrir l'application e-mail
        },
        // Padding EXTERNE : le parent éloigne la fiche des bords de l'écran.
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

// Fiche complète : conteneur Surface + organisation interne du contenu.
@Composable
fun ProfileCard(
    name: String,
    role: String,
    description: String,
    skills: List<String>,
    contact: String,
    isAvailable: Boolean,
    onAvailabilityClick: () -> Unit,
    onContactClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
    ) {
        // Padding INTERNE : la fiche éloigne son contenu de ses propres bords.
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            ProfileHeader(
                name = name,
                role = role,
                modifier = Modifier.padding(bottom = 12.dp)
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            SectionTitle(
                title = "Compétences",
                modifier = Modifier.padding(bottom = 8.dp)
            )
            SkillList(
                skills = skills,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            SectionTitle(
                title = "Contact",
                modifier = Modifier.padding(bottom = 8.dp)
            )
            ContactInfo(
                label = "E-mail",
                value = contact,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            AvailabilityBadge(
                name = name,
                isAvailable = isAvailable,
                onClick = onAvailabilityClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            )
            ContactButton(
                text = "Contacter $name",
                onClick = onContactClick,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun ProfileHeader(
    name: String,
    role: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = name,
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = role,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun SectionTitle(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleSmall,
        modifier = modifier
    )
}

@Composable
fun SkillList(
    skills: List<String>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        for (skill in skills) {
            SkillItem(
                skill = skill,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }
    }
}

@Composable
fun SkillItem(
    skill: String,
    modifier: Modifier = Modifier
) {
    Surface(
        color = MaterialTheme.colorScheme.secondaryContainer,
        shape = MaterialTheme.shapes.small,
        modifier = modifier
    ) {
        Text(
            text = skill,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
        )
    }
}

@Composable
fun ContactInfo(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = "$label : $value",
        style = MaterialTheme.typography.bodyMedium,
        modifier = modifier
    )
}

// Zone cliquable personnalisée : elle a besoin d'un rôle et d'une description
// pour être comprise par un lecteur d'écran (TalkBack).
@Composable
fun AvailabilityBadge(
    name: String,
    isAvailable: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        color = if (isAvailable) {
            MaterialTheme.colorScheme.primaryContainer
        } else {
            MaterialTheme.colorScheme.errorContainer
        },
        shape = MaterialTheme.shapes.small,
        modifier = modifier
            .clickable(
                role = Role.Button,
                onClick = onClick
            )
            .semantics {
                contentDescription = if (isAvailable) {
                    "$name est disponible pour de nouveaux projets. Voir les disponibilités"
                } else {
                    "$name n'est pas disponible actuellement. Voir les disponibilités"
                }
            }
    ) {
        Text(
            text = if (isAvailable) {
                "Disponible pour de nouveaux projets"
            } else {
                "Non disponible actuellement"
            },
            modifier = Modifier.padding(12.dp)
        )
    }
}

// Button gère déjà le rôle « bouton » et lit son texte pour l'accessibilité.
@Composable
fun ContactButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
    ) {
        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfilDeveloppeurCarlosTheme {
        ProfileScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileCardFullStackUnavailablePreview() {
    ProfilDeveloppeurCarlosTheme {
        ProfileCard(
            name = "Thomas Durand",
            role = "Développeur full-stack",
            description = "Cinq ans d'expérience sur des applications web et mobiles, " +
                "actuellement en mission.",
            skills = listOf("TypeScript", "React", "Node.js", "PostgreSQL", "Docker"),
            contact = "thomas.durand@exemple.fr",
            isAvailable = false,
            onAvailabilityClick = {},
            onContactClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
}
