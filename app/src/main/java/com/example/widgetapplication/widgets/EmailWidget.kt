import android.util.Patterns
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.TextStyle
import com.example.widgetapplication.ui.theme.PrimaryColor
import com.example.widgetapplication.ui.theme.SecondaryColor

@Composable
fun EmailWidget(email: String, onEmailUpdate: (String) -> Unit) {
    val isEmailEditInProgressOrProperFormat = remember {
        mutableStateOf(true)
    }

    OutlinedTextField(value = email,
        onValueChange = {
            isEmailEditInProgressOrProperFormat.value = true
            onEmailUpdate(it)
        },
        textStyle = TextStyle(color = if(isEmailEditInProgressOrProperFormat.value) PrimaryColor else SecondaryColor ), modifier = Modifier.onFocusChanged {
            isEmailEditInProgressOrProperFormat.value = isEmailValid(email)
        },label = {
            Text(text = "E-mail")
        }, placeholder = {
            Text(text = "Please enter your E-mail address!")
        })
}

private fun isEmailValid(email: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}
