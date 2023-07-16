package com.tyrnor.littlelemon.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PersonalInformationField(text: String){
    OutlinedTextField(modifier = Modifier.fillMaxWidth(),
        value = text,
        onValueChange = {},
        readOnly = true,
        enabled = false,
        shape = RoundedCornerShape(8.dp)
    )
}