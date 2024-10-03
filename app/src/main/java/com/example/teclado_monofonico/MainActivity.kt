package com.example.teclado_monofonico



import android.content.pm.ActivityInfo
import android.media.SoundPool
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.teclado_monofonico.ui.theme.Teclado_monofonicoTheme

const val weightK=1f;
class MainActivity : ComponentActivity() {




    private lateinit var soundPool: SoundPool
    private var soundIds = mutableMapOf<String, Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
requestedOrientation=ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        super.onCreate(savedInstanceState)

        // Inicializar SoundPool para reproducir los sonidos de las notas
        soundPool = SoundPool.Builder().setMaxStreams(1).build()

        // Cargar los sonidos de las notas en formato mp3 desde res/raw
        soundIds["Do"] = soundPool.load(this, R.raw.doo, 1)
        soundIds["Re"] = soundPool.load(this, R.raw.re, 1)
        soundIds["Mi"] = soundPool.load(this, R.raw.mi, 1)
        soundIds["Fa"] = soundPool.load(this, R.raw.fa, 1)
        soundIds["Sol"] = soundPool.load(this, R.raw.sol, 1)
        soundIds["La"] = soundPool.load(this, R.raw.la, 1)
        soundIds["Si"] = soundPool.load(this, R.raw.si, 1)

        soundIds["Do4"] = soundPool.load(this, R.raw.c4, 1)
        soundIds["Re4"] = soundPool.load(this, R.raw.d4, 1)
        soundIds["Mi4"] = soundPool.load(this, R.raw.e4, 1)
        soundIds["Fa4"] = soundPool.load(this, R.raw.f4, 1)
        soundIds["Sol4"] = soundPool.load(this, R.raw.g4, 1)
       soundIds["La5"]=soundPool.load(this,R.raw.a5,1)
        soundIds["Si5"]=soundPool.load(this,R.raw.b5,1)

        setContent {
            Teclado_monofonicoTheme {
                // Teclado Musical en Compose
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    MusicalKeyboard(
                        playNote = { note -> playSound(note) }

                    )
                }
            }
        }
    }

    // Función para reproducir el sonido de la nota seleccionada
    private fun playSound(note: String) {
        val soundId = soundIds[note]
        soundId?.let {
            soundPool.play(it, 1f, 1f, 1, 0, 1f) // Reproduce el sonido
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        soundPool.release() // Libera los recursos del SoundPool
    }
}

@Composable
fun MusicalKeyboard(playNote: (String) -> Unit) {
    val whitenotes = listOf("Do", "Re", "Mi", "Fa", "Sol", "La", "Si")
    val blacknotes= listOf("Do4", "Re4", "Mi4", "Fa4", "Sol4", "La5","Si5")
    Column(modifier = Modifier.fillMaxWidth()) {
        // Fila de teclas blancas (principales)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp) // Fijar la altura de las teclas blancas
        ) {
            whitenotes.forEach { note ->
                Box(
                    modifier = Modifier
                        .weight(1f) // Todas las teclas blancas tienen el mismo ancho
                        .fillMaxHeight()
                        .padding(4.dp)
                        .background(Color.White)
                        .border(1.dp,Color.Black)
                        .clickable { playNote(note) },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = note,
                        fontSize = 24.sp,
                        color = Color.Black
                    )
                }
            }
        }

        // Fila de teclas negras (sostenidos/bemoles)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp) // Las teclas negras son más cortas que las blancas
                .padding(start = 48.dp, end = 48.dp) // Ajustar para centrar las teclas negras
        ) {
            blacknotes.forEach { note ->
                if (note.isNotEmpty()) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .padding(4.dp)
                            .background(Color.Black)
                            .clickable { playNote(note) },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = note,
                            fontSize = 18.sp,
                            color = Color.White
                        )
                    }
                } else {
                    Spacer(modifier = Modifier.weight(1f)) // Espacio donde no hay tecla negra
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMusicalKeyboard() {
    Teclado_monofonicoTheme {
        MusicalKeyboard(playNote = {})
    }
}
@Composable
fun MusicalKeyboard() {
    Row( // Usamos Row para alinear las teclas horizontalmente
        modifier = Modifier
            .fillMaxWidth() // Asegura que ocupe todo el ancho de la pantalla
            .height(120.dp) // Fija la altura del teclado
    ) {
        val whitenotes = listOf("Do", "Re", "Mi", "Fa", "Sol", "La", "Si")
        whitenotes.forEach {whitenotes ->

        }
        val blacknotes= listOf("Do4", "Re4", "Mi4", "Fa4", "Sol4", "La5","Si5")
        blacknotes.forEach{blacknotes ->
        }
    }
    }
