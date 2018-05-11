package com.randomlettersandnumbers15645151gd65fg16d5fgs46r84tserg51f3d21ger6.EVE_Mining_Calc;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.randomlettersandnumbers15645151gd65fg16d5fgs46r84tserg51f3d21ger6.eveminer", appContext.getPackageName());
    }
}
