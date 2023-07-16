package com.tyrnor.littlelemon.view

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.tyrnor.littlelemon.components.Header
import com.tyrnor.littlelemon.components.PersonalInformationField
import com.tyrnor.littlelemon.components.SubHeader
import com.tyrnor.littlelemon.model.data.DataStoreManager
import com.tyrnor.littlelemon.ui.theme.YellowLL
import com.tyrnor.littlelemon.viewmodel.ProfileViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun Profile(
    navController: NavHostController,
    profileViewModel: ProfileViewModel,
    dataStoreManager: DataStoreManager,
) {

    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween) {
        Column {
            Header(backArrow = true, navController)
            SubHeader(color = Color.White, text = "")
            PersonalInformation(coroutineScope, profileViewModel, dataStoreManager)
        }
        Button(modifier = Modifier
            .weight(1f, false)
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp), onClick = {
            coroutineScope.launch {
                dataStoreManager.clearDataSotre()
            }
        }, colors = ButtonDefaults.buttonColors(backgroundColor = YellowLL),
            border = BorderStroke(1.dp, Color(0xFFd5a04e))) {
            Text(text = "Log Out")
        }
    }
}

@Composable
fun PersonalInformation(
    coroutineScope: CoroutineScope,
    profileViewModel: ProfileViewModel,
    dataStoreManager: DataStoreManager,
) {

    val firstName: String by profileViewModel.firstName.observeAsState(initial = "")
    val lastName: String by profileViewModel.lastName.observeAsState(initial = "")
    val email: String by profileViewModel.email.observeAsState(initial = "")

    LaunchedEffect(true) {
        coroutineScope.launch {
            dataStoreManager.getFromDataStore().collect {
                profileViewModel.onPersonalInformationChange(
                    firstName = it.firstName,
                    lastName = it.lastName,
                    email = it.email
                )
            }
        }
    }

    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(0.8f)
        .padding(start = 16.dp, end = 16.dp)
    ) {
        Text(modifier = Modifier.padding(bottom = 60.dp),
            text = "Personal information",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold)
        Log.d("TAG", "PersonalInformation: $firstName, $lastName $email")
        Text(text = "First name", fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(5.dp))
        PersonalInformationField(firstName)
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Last name", fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(5.dp))
        PersonalInformationField(lastName)
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Email", fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(5.dp))
        PersonalInformationField(email)
    }
}



