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
import javax.inject.Qualifier
import javax.inject.Singleton


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    // Field Injection
    @Inject
    lateinit var testClass: TestClass

    @Inject
    lateinit var fragmentFactory: MainFragmentFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.fragmentFactory = fragmentFactory
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_container,MainFragment::class.java,null)
            .commit()


//        println(testClass.doTest())
//        println(testClass.doInterface1())
//        println(testClass.doInterface2())
    }
}

// We cannot inject with interfact type
class TestClass @Inject constructor(@Impl1 private val someInterfaceImpl1: SomeInterface,
                                    @Impl2 private val someInterfaceImpl2:SomeInterface
, private val gson: Gson){

    fun doTest():String
    {
        return "Testing";
    }

    fun doInterface1():String{
        return someInterfaceImpl1.doTest()
    }

    fun doInterface2():String{
        return someInterfaceImpl2.doTest()
    }


}

class SomeInterfaceImpl1 @Inject constructor():SomeInterface{
    override fun doTest(): String
    {
        return "Interace 2"
    }
}

class SomeInterfaceImpl2 @Inject constructor():SomeInterface{
    override fun doTest(): String
    {
        return "Interface 1"
    }

}

interface SomeInterface{
    fun doTest():String
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
    @Impl1
    @Singleton
    @Provides
    fun provideSomeInterfaceImpl():SomeInterface {
        return SomeInterfaceImpl1()
    }

    @Impl2
    @Singleton
    @Provides
    fun provideSomeInterfaceImpl2():SomeInterface {
        return SomeInterfaceImpl2()
    }

    @Singleton
    @Provides
    fun provideGsonImpl():Gson{
        return Gson()
    }
}

/**
 * Create Custom Implementation for same type
 *
 */

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Impl1

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Impl2

