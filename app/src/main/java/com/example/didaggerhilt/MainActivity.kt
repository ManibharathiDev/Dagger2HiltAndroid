package com.example.didaggerhilt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.FragmentScoped
import dagger.hilt.components.SingletonComponent
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
class TestClass @Inject constructor(private val test: TestInterface
, private val gson: Gson){

    fun doTest():String
    {
        return "Testing";
    }

    fun doOtherTest():String{
        return test.doOtherTest()
    }


}

class TestInterfaceImpl @Inject constructor():TestInterface{
    override fun doOtherTest(): String
    {
        return "Good Thing"
    }

}

interface TestInterface{
    fun doOtherTest():String
}

/*@InstallIn(ActivityComponent::class)
@Module
abstract class MyModule{
    @ActivityScoped
    @Binds
    abstract fun bindSomeDependency(
        someImpl:TestInterfaceImpl
    ):TestInterface

    @ActivityScoped
    @Binds
    abstract fun bindGson(gson: Gson):Gson
}*/

@InstallIn(SingletonComponent::class)
@Module
class MyModule{
    @Singleton
    @Provides
    fun provideSomeInterfaceImpl():TestInterface {
        return TestInterfaceImpl()
    }

    @Singleton
    @Provides
    fun provideGsonImpl():Gson{
        return Gson()
    }
}

