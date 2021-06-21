package com.test.fastshape;

import android.content.Context;
import android.graphics.Color;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

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

        assertEquals("com.github.fastshape", appContext.getPackageName());
    }
    @Test
    public void sd() throws Exception {
        // Context of the app under test.
            System.out.println("============="+Color.alpha(Color.BLACK));
            System.out.println("============="+Color.alpha(0x70000000));
    }
}
