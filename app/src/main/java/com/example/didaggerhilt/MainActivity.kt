package com.example.didaggerhilt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject
import javax.inject.Singleton


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    // Field Injection
    @Inject
    lateinit var testClass: TestClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println(testClass.doTest())
        println(testClass.doOtherTest())
    }
}

// We cannot inject with interfact type
class TestClass @Inject constructor(private val testOtherClass: Test){

    fun doTest():String
    {
        return "Testing";
    }

    fun doOtherTest():String{
        return testOtherClass.doOtherTest()
    }


}

class TestOtherClass @Inject constructor():Test{
    override fun doOtherTest(): String
    {
        return "Good Thing"
    }

}

interface Test{
    fun doOtherTest():String
}

