package ie.wit.apexmeals.ui.report

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import ie.wit.apexmeals.firebase.FirebaseDBManager
import ie.wit.apexmeals.models.ApexMealsModel
import timber.log.Timber
import java.lang.Exception

class ReportViewModel : ViewModel() {

    private val donationsList =
        MutableLiveData<List<ApexMealsModel>>()

    val observableDonationsList: LiveData<List<ApexMealsModel>>
        get() = donationsList

    var liveFirebaseUser = MutableLiveData<FirebaseUser>()

    init { load() }

    fun load() {
        try {
            //DonationManager.findAll(liveFirebaseUser.value?.email!!, donationsList)
            FirebaseDBManager.findAll(liveFirebaseUser.value?.uid!!,
                donationsList)
            Timber.i("Report Load Success : ${donationsList.value.toString()}")
        }
        catch (e: Exception) {
            Timber.i("Report Load Error : $e.message")
        }
    }

    fun delete(userid: String, id: String) {
        try {
            //DonationManager.delete(userid,id)
            FirebaseDBManager.delete(userid,id)
            Timber.i("Report Delete Success")
        }
        catch (e: Exception) {
            Timber.i("Report Delete Error : $e.message")
        }
    }
}