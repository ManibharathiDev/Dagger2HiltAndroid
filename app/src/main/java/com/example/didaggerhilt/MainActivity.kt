package com.example.didaggerhilt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    @Inject
    lateinit var testClass: TestClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println(testClass.doTest())
        println(testClass.doOtherTest())
    }
}

class TestClass @Inject constructor(testOtherClass: TestOtherClass){

    private var testOtherClass: TestOtherClass

    init {
        this.testOtherClass = testOtherClass
    }
    fun doTest():String
    {
        return "Testing";
    }

    fun doOtherTest():String{
        return testOtherClass.doOtherTest()
    }


}

class TestOtherClass @Inject constructor(){
    fun doOtherTest():String{
        return "Do other testing!";
    }
}

