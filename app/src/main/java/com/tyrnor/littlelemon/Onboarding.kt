package com.tyrnor.littlelemon

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Onboarding() {
    val imeState = rememberImeState()
    val scrollState = rememberScrollState()
    val focusManager = LocalFocusManager.current

    LaunchedEffect(key1 = imeState.value){
        if (imeState.value){
            scrollState.scrollTo(scrollState.maxValue)

        } else {
            focusManager.clearFocus()
        }
    }
    Column(Modifier
        .fillMaxSize()
       ) {
        Header()
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF485e57))
                .height(120.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 24.sp,
                text = "Let's get to know you"
            )
        }
        RegisterForm(scrollState, imeState, focusManager)
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewOnboarding() {
    Onboarding()
}

@Composable
fun Header() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .padding(40.dp),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "")
    }
}

@Composable
fun RegisterForm(scrollState: ScrollState, imeState: State<Boolean>, focusManager: FocusManager) {

    //val controller = LocalSoftwareKeyboardController.current

    val firstNameText = remember {
        mutableStateOf(value = "")
    }
    val lastNameText = remember {
        mutableStateOf(value = "")
    }
    val emailText = remember {
        mutableStateOf(value = "")
    }
    val focusManager = LocalFocusManager.current

    val modifier = Modifier
        .height(58.dp)
        .fillMaxWidth()
        .padding(horizontal = 16.dp)

    Column ( verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .verticalScroll(scrollState)
    ){
        Text(modifier = Modifier.padding(start = 14.dp, top = 30.dp, bottom = 30.dp),
            text = "Personal information",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold)
        OutlinedTextField(
            modifier = modifier,
            singleLine = true,
            value = firstNameText.value,
            onValueChange = { firstNameText.value = it },
            label = { Text(text = "First name") },
            keyboardOptions = if (lastNameText.value.isEmpty() || emailText.value.isEmpty()) {
                KeyboardOptions(imeAction = ImeAction.Next)
            } else {
                KeyboardOptions(imeAction = ImeAction.Done)
            },
            keyboardActions = KeyboardActions(onNext = {
                if (lastNameText.value.isEmpty() || emailText.value.isEmpty()) {
                    if (lastNameText.value.isEmpty() && emailText.value.isEmpty()) {
                        focusManager.moveFocus(focusDirection = FocusDirection.Next)
                    } else if (lastNameText.value.isNotEmpty() && emailText.value.isEmpty()) {
                        focusManager.moveFocus(focusDirection = FocusDirection.Previous)
                    } else {
                        focusManager.moveFocus(focusDirection = FocusDirection.Next)
                    }
                }
            }, onDone = { focusManager.clearFocus() }),
            textStyle = TextStyle(fontSize = 14.sp)
        )
        OutlinedTextField(
            modifier = modifier,
            value = lastNameText.value,
            singleLine = true,
            onValueChange = { lastNameText.value = it },
            label = { Text(text = "Last name") },
            keyboardOptions = if (firstNameText.value.isEmpty() || emailText.value.isEmpty()) {
                KeyboardOptions(imeAction = ImeAction.Next)
            } else {
                KeyboardOptions(imeAction = ImeAction.Done)
            },
            keyboardActions = KeyboardActions(onNext = {
                if (firstNameText.value.isEmpty() || emailText.value.isEmpty()) {
                    if (firstNameText.value.isEmpty() && emailText.value.isEmpty()) {
                        focusManager.moveFocus(focusDirection = FocusDirection.Next)
                    } else if (firstNameText.value.isEmpty() && emailText.value.isNotEmpty()) {
                        focusManager.moveFocus(focusDirection = FocusDirection.Previous)
                    } else {
                        focusManager.moveFocus(focusDirection = FocusDirection.Next)
                    }
                }
            }, onDone = { focusManager.clearFocus() }),
            textStyle = TextStyle(fontSize = 14.sp)
        )
        OutlinedTextField(
            modifier = modifier,
            value = emailText.value,
            singleLine = true,
            onValueChange = { emailText.value = it },
            label = { Text(text = "Email") },
            keyboardOptions = if (firstNameText.value.isEmpty() || lastNameText.value.isEmpty()) {
                KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next)
            } else {
                KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Done)
            },
            keyboardActions = KeyboardActions(onNext = {
                if (firstNameText.value.isEmpty() || lastNameText.value.isEmpty()) {
                    if (firstNameText.value.isEmpty() && lastNameText.value.isEmpty()) {
                        focusManager.moveFocus(focusDirection = FocusDirection.Next)
                    } else if (firstNameText.value.isNotEmpty() && lastNameText.value.isEmpty()) {
                        focusManager.moveFocus(focusDirection = FocusDirection.Previous)
                    } else {
                        focusManager.moveFocus(focusDirection = FocusDirection.Next)
                    }
                }
            }, onDone = { focusManager.clearFocus() }),
            textStyle = TextStyle(fontSize = 14.sp)
        )
        Button(modifier= Modifier
            .fillMaxWidth()
            .padding(top = if (imeState.value) {0.dp} else {120.dp}, start = 16.dp, end = 16.dp), onClick = { /*TODO*/ }) {
            Text(text = "Register")
        }
    }
}