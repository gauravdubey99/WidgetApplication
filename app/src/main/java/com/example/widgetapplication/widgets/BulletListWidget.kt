package com.example.widgetapplication.widgets

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.widgetapplication.enums.PasswordCheckList

@Composable
fun BulletListWidget(textList: List<PasswordCheckList>, shouldShowBulletList: Boolean) {
    if (shouldShowBulletList) {
        LazyColumn {
            items(textList) { passwordCheckList ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = android.R.drawable.stat_notify_error),
                        contentDescription = "Error indicator.",
                        modifier = Modifier.width(15.dp).height(15.dp),
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = passwordCheckList.validationText,
                        style = TextStyle(
                            color = getColor(passwordCheckList.isValid),
                            fontSize = 10.sp
                        )
                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
            }
        }
    }
}

private fun getColor(isValid : Boolean): Color {
    return if (isValid) Color.Blue else Color.Red
}

@Preview
@Composable
fun PreviewList() {
    BulletListWidget(textList = listOf(PasswordCheckList.PASSWORD_LENGTH, PasswordCheckList.ONE_UPPER_LOWER_CASE_ONE_NUMBER), false)
}