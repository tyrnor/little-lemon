package com.tyrnor.littlelemon.view

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.tyrnor.littlelemon.components.Header
import com.tyrnor.littlelemon.components.SubHeader
import com.tyrnor.littlelemon.model.PersonalInformationModel
import com.tyrnor.littlelemon.model.data.DataStoreManager
import com.tyrnor.littlelemon.ui.theme.GreenLL
import com.tyrnor.littlelemon.ui.theme.YellowLL
import com.tyrnor.littlelemon.utils.rememberImeState
import com.tyrnor.littlelemon.viewmodel.OnboardingViewModel
import kotlinx.coroutines.launch


@Composable
fun Onboarding(
    navController: NavHostController,
    onboardingViewModel: OnboardingViewModel,
    dataStoreManager: DataStoreManager,
) {
    val dataStoreManager = dataStoreManager
    val imeState = rememberImeState()
    val scrollState = rememberScrollState()
    val focusManager = LocalFocusManager.current

    LaunchedEffect(key1 = imeState.value) {
        if (imeState.value) {
            scrollState.scrollTo(scrollState.maxValue)
        } else {
            focusManager.clearFocus()
            Log.d("Test", "Onboarding: Test")
        }
    }
    Column(Modifier
        .fillMaxSize()
        .verticalScroll(scrollState)
    ) {
        Header(backArrow = false, navController = navController, profileImage = false)
        SubHeader(color = GreenLL, text = "Let's get to know you")
        RegisterForm(imeState, navController, onboardingViewModel, dataStoreManager)
    }

}


@Composable
fun RegisterForm(
    imeState: State<Boolean>,
    navController: NavHostController,
    onboardingViewModel: OnboardingViewModel,
    dataStoreManager: DataStoreManager,
) {
    val firstNameText: String by onboardingViewModel.firstNameText.observeAsState(initial = "")
    val lastNameText: String by onboardingViewModel.lastNameText.observeAsState(initial = "")
    val emailText: String by onboardingViewModel.emailText.observeAsState(initial = "")
    val firstNameError: Boolean by onboardingViewModel.firstNameError.observeAsState(initial = false)
    val lastNameError: Boolean by onboardingViewModel.lastNameError.observeAsState(initial = false)
    val emailError: Boolean by onboardingViewModel.emailError.observeAsState(initial = false)

    val coroutineScope = rememberCoroutineScope()

    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    val configuration = LocalConfiguration.current

    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenHeightDp

    Log.d("TAG", "RegisterForm: $screenHeight $screenWidth")

    val modifier = Modifier
        .height(58.dp)
        .fillMaxWidth()
        .padding(horizontal = 16.dp)

    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
    ) {
        Text(modifier = Modifier.padding(start = 14.dp, top = 55.dp, bottom = 60.dp),
            text = "Personal information",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold)
        OutlinedTextField(
            modifier = modifier,
            singleLine = true,
            value = firstNameText,
            onValueChange = {
                onboardingViewModel.onRegisterChanged(firstNameText = it,
                    lastNameText = lastNameText,
                    emailText = emailText)
            },
            label = { Text(text = "First name") },
            keyboardOptions = if (lastNameText.isBlank() || emailText.isBlank()) {
                KeyboardOptions(imeAction = ImeAction.Next)
            } else {
                KeyboardOptions(imeAction = ImeAction.Done)
            },
            keyboardActions = KeyboardActions(onNext = {
                if (lastNameText.isBlank() || emailText.isBlank()) {
                    if (lastNameText.isNotBlank() && emailText.isBlank()) {
                        focusManager.moveFocus(focusDirection = FocusDirection.Previous)
                    } else {
                        focusManager.moveFocus(focusDirection = FocusDirection.Down)
                    }
                }
            }, onDone = { focusManager.clearFocus() }),
            textStyle = TextStyle(fontSize = 14.sp),
            colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = Color(0xFFd5a04e),
                focusedLabelColor = Color.Black,
                cursorColor = Color.Black),
            trailingIcon = if (firstNameText.isNotBlank()) {
                {
                    Icon(Icons.Default.Clear,
                        contentDescription = "clear text",
                        modifier = Modifier
                            .clickable {
                                onboardingViewModel.onRegisterChanged("",
                                    lastNameText = lastNameText,
                                    emailText = emailText)
                            }
                    )
                }
            } else {
                null
            },
            isError = firstNameError,
            shape = RoundedCornerShape(8.dp)
        )
        if (firstNameError) {
            Text(modifier = Modifier.padding(start = 18.dp),
                text = "This field is required",
                color = Color.Red,
                fontSize = 12.sp)
        }
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            modifier = modifier,
            value = lastNameText,
            singleLine = true,
            onValueChange = {
                onboardingViewModel.onRegisterChanged(firstNameText = firstNameText,
                    lastNameText = it,
                    emailText = emailText)
            },
            label = { Text(text = "Last name") },
            keyboardOptions = if (firstNameText.isBlank() || emailText.isBlank()) {
                KeyboardOptions(imeAction = ImeAction.Next)
            } else {
                KeyboardOptions(imeAction = ImeAction.Done)
            },
            keyboardActions = KeyboardActions(onNext = {
                if (firstNameText.isBlank() || emailText.isBlank()) {
                    if (firstNameText.isBlank() && emailText.isNotBlank()) {
                        focusManager.moveFocus(focusDirection = FocusDirection.Previous)
                    } else {
                        focusManager.moveFocus(focusDirection = FocusDirection.Down)
                    }
                }
            }, onDone = {
                focusManager.clearFocus()
            }),
            textStyle = TextStyle(fontSize = 14.sp),
            colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = Color(0xFFd5a04e),
                focusedLabelColor = Color.Black,
                cursorColor = Color.Black),
            trailingIcon = if (lastNameText.isNotBlank()) {
                {
                    Icon(Icons.Default.Clear,
                        contentDescription = "clear text",
                        modifier = Modifier
                            .clickable {
                                onboardingViewModel.onRegisterChanged(firstNameText = firstNameText,
                                    lastNameText = "",
                                    emailText = emailText)
                            }
                    )
                }
            } else {
                null
            },
            isError = lastNameError,
            shape = RoundedCornerShape(8.dp)
        )
        if (lastNameError) {
            Text(modifier = Modifier.padding(start = 18.dp),
                text = "This field is required",
                color = Color.Red,
                fontSize = 12.sp)
        }
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            modifier = modifier,
            value = emailText,
            singleLine = true,
            onValueChange = {
                onboardingViewModel.onRegisterChanged(firstNameText = firstNameText,
                    lastNameText = lastNameText,
                    emailText = it)
            },
            label = { Text(text = "Email") },
            keyboardOptions = if (firstNameText.isBlank() || lastNameText.isBlank()) {
                KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next)
            } else {
                KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Done)
            },
            keyboardActions = KeyboardActions(onNext = {
                if (firstNameText.isBlank() || lastNameText.isBlank()) {
                    if (firstNameText.isNotBlank() && lastNameText.isBlank()) {
                        focusManager.moveFocus(focusDirection = FocusDirection.Previous)
                    } else {
                        focusManager.moveFocus(focusDirection = FocusDirection.Next)
                    }
                }
            }, onDone = { focusManager.clearFocus() }),
            textStyle = TextStyle(fontSize = 14.sp),
            colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = Color(0xFFd5a04e),
                focusedLabelColor = Color.Black,
                cursorColor = Color.Black),
            trailingIcon = if (emailText.isNotBlank()) {
                {
                    Icon(Icons.Default.Clear,
                        contentDescription = "clear text",
                        modifier = Modifier
                            .clickable {
                                onboardingViewModel.onRegisterChanged(firstNameText = firstNameText,
                                    lastNameText = lastNameText,
                                    emailText = "")
                            }
                    )
                }
            } else {
                null
            },
            isError = emailError,
            shape = RoundedCornerShape(8.dp)
        )

        if (emailError) {
            Text(modifier = Modifier.padding(start = 18.dp),
                text = "This field is required",
                color = Color.Red,
                fontSize = 12.sp)
        }

        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(top = if (imeState.value) {
                0.dp
            } else {
                if (screenHeight < 720.dp) {
                    50.dp
                } else if (screenHeight < 900.dp && screenHeight > 720.dp) {
                    180.dp
                } else {
                    330.dp
                }
            }, start = 16.dp, end = 16.dp, bottom = 8.dp),
            onClick = {
                if (firstNameText.isBlank() || lastNameText.isBlank() || emailText.isBlank()) {
                    Toast.makeText(context,
                        "Registration unsuccessful. Please enter all data",
                        Toast.LENGTH_SHORT).show()
                    onboardingViewModel.onFirstNameError(firstNameText.isBlank())
                    onboardingViewModel.onLastNameError(lastNameText.isBlank())
                    onboardingViewModel.onEmailError(emailText.isBlank())

                } else {
                    if (onboardingViewModel.validEmail(emailText)) {
                        coroutineScope.launch {
                            dataStoreManager.saveToDataStore(
                                PersonalInformationModel(
                                    firstName = firstNameText,
                                    lastName = lastNameText,
                                    email = emailText
                                )
                            )
                            onboardingViewModel.onRegisterChanged("", "", "")
                        }
                        navController.navigate(com.tyrnor.littlelemon.model.navigation.Home.route)
                    } else {
                        Toast.makeText(context,
                            "Invalid email. Introduce a valid one",
                            Toast.LENGTH_SHORT).show()
                    }


                }

            },
            colors = ButtonDefaults.buttonColors(backgroundColor = YellowLL),
            border = BorderStroke(1.dp, Color(0xFFd5a04e))
        ) {
            Text(text = "Register")
        }
    }
}